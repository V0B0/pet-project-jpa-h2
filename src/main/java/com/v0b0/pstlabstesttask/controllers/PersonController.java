package com.v0b0.pstlabstesttask.controllers;

import com.v0b0.pstlabstesttask.entities.Address;
import com.v0b0.pstlabstesttask.entities.Company;
import com.v0b0.pstlabstesttask.entities.Passport;
import com.v0b0.pstlabstesttask.entities.Person;
import com.v0b0.pstlabstesttask.service.GlobalServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PersonController {

    private final GlobalServiceImpl service;

    public PersonController(GlobalServiceImpl service) {
        this.service = service;
    }

    @ModelAttribute("person")
    public Person loadPerson(Map<String, Object> model) {
        Person person = new Person();
        person.setPassport(new Passport());
        person.setPrimaryAddress(new Address());
        return person;
    }

    @GetMapping("/person/new")
    public String newPersonForm(){
        System.out.println("newPersonForm()");
        return "person/createOrUpdatePerson";
    }

    @PostMapping("person/new")
    public String newPerson(Person person){
        System.out.println("newPerson()");
        person.getPassport().setOwner(person);
        service.savePerson(person);
        return "redirect:/";
    }

    @GetMapping("/person/{id}/edit")
    public String editPersonForm(@PathVariable long id, Model model){
        System.out.println("editPersonForm()");
        model.addAttribute("person", service.getPerson(id));
//        model.addAttribute("addresses", service.getAddress());
        return "person/createOrUpdatePerson";
    }

    @PostMapping("person/{id}/edit")
    public String editPerson(@PathVariable("id") long id, Person person){
        person.setId(id);
        person.getPassport().setOwner(person);
        long[]addressId=new long[1];
        person.getPrimaryAddress().setTenants(service.getPeople().stream().filter(person1 -> {
            if (person1.getPrimaryAddress().equals(person.getPrimaryAddress()))
                addressId[0]=person1.getPrimaryAddress().getId();
            return person1.getPrimaryAddress().equals(person.getPrimaryAddress());})
                .collect(Collectors.toList()));
        person.getPrimaryAddress().setId(addressId[0]);
        service.savePassport(person.getPassport());
        service.saveAddress(person.getPrimaryAddress());
        service.savePerson(person);
        System.out.println(service.getPerson(id)+" after add in db");
        return "redirect:/";
    }

    @GetMapping("/person/{id}")
    public String personDetails(@PathVariable("id") long id, Model model){
        System.out.println("personInfo()");
        System.out.println(service.getPerson(id));
        model.addAttribute("person", service.getPerson(id));
        return "person/personDetails";
    }

    @GetMapping("/person/{id}/delete")
    public String deletePerson(@PathVariable("id") long id) {
        Person person = service.getPerson(id);
        service.deletePerson(person);
        service.deletePassport(person.getPassport());
//        service.deleteAddress(person.getPrimaryAddress());
        return "redirect:/";
    }

    @GetMapping("/person/{id}/company")
    public String addCompanyForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("company", new Company());
        return "company/createOrUpdateCompany";
    }

    @PostMapping("/person/{id}/company")
    public String addCompany(@PathVariable("id") long id, Company company) {
        service.saveCompany(id, company);
        return "redirect:/person/"+id;
    }
}
