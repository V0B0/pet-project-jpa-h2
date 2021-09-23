package com.v0b0.pstlabstesttask.service;

import com.v0b0.pstlabstesttask.entities.Address;
import com.v0b0.pstlabstesttask.entities.Company;
import com.v0b0.pstlabstesttask.entities.Passport;
import com.v0b0.pstlabstesttask.entities.Person;
import com.v0b0.pstlabstesttask.repository.AddressRepository;
import com.v0b0.pstlabstesttask.repository.CompanyRepository;
import com.v0b0.pstlabstesttask.repository.PassportRepository;
import com.v0b0.pstlabstesttask.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GlobalServiceImpl implements GlobalService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final PassportRepository passportRepository;
    private final PersonRepository personRepository;

    public GlobalServiceImpl(CompanyRepository companyRepository, AddressRepository addressRepository,
                             PassportRepository passportRepository, PersonRepository personRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.passportRepository = passportRepository;
        this.personRepository = personRepository;
    }

//    Company
    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public void saveCompany( long personId, Company company){
        if (this.getCompany().stream().noneMatch(company1 -> company1.equals(company))){
            company.setWorkers(Collections.singletonList(this.getPerson(personId)));
        } else {
            long id = this.getCompany().stream().filter(company1 -> company1.equals(company))
                    .findAny().get().getId();
            company.setWorkers(this.getCompany(id).getWorkers());
            company.setId(id);
        }
        this.savePerson(this.getPerson(personId).addCompany(company));
        this.saveCompany(company);
//
//        if (this.getCompany().stream().noneMatch(company1 -> company1.getName().equals(company.getName()))){
//            company.setWorkers(Collections.singletonList(this.getPerson(personId)));
//        } else {
//            long[] idCompany = new long[1];
//            company.setWorkers(this.getPeople().stream().filter(person -> person.getWorkingPlaces()
//                    .stream().anyMatch(company1 -> {
//                        if (company1.getName().equals(company.getName()))
//                            idCompany[0] = company1.getId();
//                        return company1.getName().equals(company.getName());
//                    })).collect(Collectors.toList()));
//            company.setId(idCompany[0]);
//        }
//        this.savePerson(this.getPerson(personId).addCompany(company));
//        this.saveCompany(company);
    }

    @Override
    public Company getCompany(Long id) {
        return companyRepository.getById(id);
    }

    @Override
    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getCompany() {
        return companyRepository.findAll();
    }

    @Override
    public long countCompany() {
        return companyRepository.count();
    }

//    Address
    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddress(Long id) {
        return addressRepository.getById(id);
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    @Override
    public long countAddress() {
        return addressRepository.count();
    }

//    Passport
    @Override
    public Passport savePassport(Passport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public Passport getPassport(Long id) {
        return passportRepository.getById(id);
    }

    @Override
    public void deletePassport(Passport passport) {
        passportRepository.delete(passport);
    }

    @Override
    public void deletePassport(Long id) {
        passportRepository.deleteById(id);
    }

    @Override
    public List<Passport> getPassports() {
        return passportRepository.findAll();
    }

    @Override
    public long countPassports() {
        return passportRepository.count();
    }

    //    Person
    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
        return personRepository.getById(id);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @Override
    public long countPeople() {
        return personRepository.count();
    }

}
