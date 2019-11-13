package com.demo.core.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PERSON")
public class Person extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 2)
    private String firstName;

    @Column(name = "LAST_NAME")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 2)
    private String lastName;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", insertable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    })
    private Address address;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
