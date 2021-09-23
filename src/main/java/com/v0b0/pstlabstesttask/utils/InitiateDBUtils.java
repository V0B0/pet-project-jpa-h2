package com.v0b0.pstlabstesttask.utils;

import com.v0b0.pstlabstesttask.entities.Address;
import com.v0b0.pstlabstesttask.entities.Company;
import com.v0b0.pstlabstesttask.entities.Passport;
import com.v0b0.pstlabstesttask.entities.Person;
import com.v0b0.pstlabstesttask.service.GlobalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitiateDBUtils implements CommandLineRunner {

    private final GlobalService service;

    public InitiateDBUtils(GlobalService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception{
        List<Company> companies = new ArrayList<>();
        List<Person> people = new ArrayList<>();
            for (int i = 0; i<10; i++) {
                Person person = new Person();
                person.setFirstName("FirstName_" + i);
                person.setLastName("LastName_" + i);
                person.setBirthDate(LocalDate.now());

                Passport passport = new Passport();
                passport.setSeries("A_"+i);
                passport.setNumber(String.valueOf((i+1)*1111111));
                passport.setIssueDate(LocalDate.now());
                passport.setOwner(person);
                person.setPassport(passport);

                Address address = new Address();
                address.setCity("City_"+i);
                address.setStreet("Street_"+i);
                address.setBuilding("#"+i);
                person.setPrimaryAddress(address);

                Company company = new Company();
                company.setName("Company_"+i);
                companies.add(company);

                people.add(person);
                company.setWorkers(people);

                service.saveCompany(company);

                service.savePerson(person);
                service.savePassport(passport);
                service.saveAddress(address);
            }
    }
}
