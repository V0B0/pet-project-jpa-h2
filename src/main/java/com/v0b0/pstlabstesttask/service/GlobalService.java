package com.v0b0.pstlabstesttask.service;

import com.v0b0.pstlabstesttask.entities.*;

import java.util.List;

public interface GlobalService {

//    Car
    Company saveCompany(Company company);
    Company getCompany(Long id);
    void deleteCompany(Company company);
    void deleteCompany(Long id);
//    List<Company> getCars(int pageNumber, int pageSize);
    List<Company> getCompany();
    long countCompany();

//    House
    Address saveAddress(Address address);
    Address getAddress(Long id);
    void deleteAddress(Address address);
    void deleteAddress(Long id);
    //    List<Address> getPeople(int pageNumber, int pageSize);
    List<Address> getAddress();
    long countAddress();

//    Passport
    Passport savePassport(Passport passport);
    Passport getPassport(Long id);
    void deletePassport(Passport passport);
    void deletePassport(Long id);
    //    List<Passport> getPeople(int pageNumber, int pageSize);
    List<Passport> getPassports();
    long countPassports();

//    Person
    Person savePerson(Person person);
    Person getPerson(Long id);
    void deletePerson(Person person);
    void deletePerson(Long id);
//    List<Person> getPeople(int pageNumber, int pageSize);
    List<Person> getPeople();
    long countPeople();
}
