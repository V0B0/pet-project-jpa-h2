package com.v0b0.pstlabstesttask.entities;

/*
 * Машина может принадлежать лишь одному Владельцу @ManyToOne
 * Машина может стоять на любой парковке @OneToMany
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Company extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Nullable
    @ManyToMany(mappedBy = "workingPlaces")
    private List<Person> workers;

    @Override
    public String toString() {
        if (workers!=null && !workers.isEmpty()) {
            StringBuilder sb = new StringBuilder("Company{name='" + name + "', workers=[");
            workers.forEach(person -> sb.append(person.getFirstName()).append(' ')
                    .append(person.getLastName()).append(','));
            return sb.toString();
        } else {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", workers=[bubble]"+
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
