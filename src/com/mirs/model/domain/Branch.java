package com.mirs.model.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Branch implements Serializable {
    private static final long serialVersionUID = 6688116669172186190L;
    private Integer id;
    private String name;
    private String phone;
    private Address address;
    private BranchStatus status;
    private AvailableRentals availableRentals;
    private List<Instrument> instrumentList;

    public Branch(Integer id, String name, String phone, Address address, BranchStatus status, AvailableRentals availableRentals, List<Instrument> instrumentList) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.availableRentals = availableRentals;
        this.instrumentList = instrumentList;
    }

    public Integer getId() { return id; }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public BranchStatus getStatus() {return status; }

    public AvailableRentals getAvailableRentals() {
        return availableRentals;
    }

    public List<Instrument> getInstrumentList() {
        return instrumentList;
    }

    public void setId(Integer id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setStatus(BranchStatus status) { this.status = status; }

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
        return Objects.equals(id, branch.id) && Objects.equals(name, branch.name) && Objects.equals(phone, branch.phone) && Objects.equals(address, branch.address) && Objects.equals(status, branch.status) && Objects.equals(availableRentals, branch.availableRentals) && Objects.equals(instrumentList, branch.instrumentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, address, status, availableRentals, instrumentList);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address + '\'' +
                ", status=" + status +
                ", availableRentals=" + availableRentals +
                ", instrumentList=" + instrumentList +
                '}';
    }

    public boolean validate() {
        if (id == null || name == null || phone == null || address == null || status == null || availableRentals == null || instrumentList == null) {
            return false;
        }
        return true;
    }
}
