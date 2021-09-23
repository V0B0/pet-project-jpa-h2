package com.v0b0.pstlabstesttask.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Address extends BaseEntity{

    private String city;

    private String street;

    private String building;

    @OneToMany(mappedBy = "primaryAddress", fetch = FetchType.EAGER)
    private List<Person> tenants;

    @Override
    public String toString() {
        return city+", "+street+", "+building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return city.equals(address.city) && street.equals(address.street) && building.equals(address.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, building);
    }
}
