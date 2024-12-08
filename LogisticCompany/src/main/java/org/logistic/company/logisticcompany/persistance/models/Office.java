package org.logistic.company.logisticcompany.persistance.models;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;
    @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
    private String address;
    @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
    private String city;

    @OneToMany(mappedBy = "office")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    @Column(name = "working-hours", nullable = false, length = Integer.MAX_VALUE)
    private String workingHours;


    public int getId() {
        return id;
    }


}
