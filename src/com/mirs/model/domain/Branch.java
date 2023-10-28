package com.mirs.model.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Branch implements Serializable {
    private static final long serialVersionUID = 6688116669172186190L;
    private String name;
    private String phone;
    private Address address;
    private AvailableRentals availableRentals;
    private List<Instrument> instrumentList;

    public Branch(String name, String phone, Address address, AvailableRentals availableRentals, List<Instrument> instrumentList) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.availableRentals = availableRentals;
        this.instrumentList = instrumentList;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public AvailableRentals getAvailableRentals() {
        return availableRentals;
    }

    public List<Instrument> getInstrumentList() {
        return instrumentList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAvailableRentals(AvailableRentals availableRentals) {
        this.availableRentals = availableRentals;
    }

    public void setInstrumentList(List<Instrument> instrumentList) {
        this.instrumentList = instrumentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(name, branch.name) && Objects.equals(phone, branch.phone) && Objects.equals(address, branch.address) && Objects.equals(availableRentals, branch.availableRentals) && Objects.equals(instrumentList, branch.instrumentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, address, availableRentals, instrumentList);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", availableRentals=" + availableRentals +
                ", instrumentList=" + instrumentList +
                '}';
    }

    public boolean validate() {
        if (name == null || phone == null || address == null || availableRentals == null || instrumentList == null) {
            return false;
        }
        return true;
    }
}
