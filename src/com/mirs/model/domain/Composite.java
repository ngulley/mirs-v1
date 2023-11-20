package com.mirs.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite implements Serializable {
    private static final long serialVersionUID = -334371602256955290L;
    private User user;
    private Instrument instrument;
    private List<Instrument> instrumentList = new ArrayList<Instrument>();
    private Branch branch;
    private List<Branch> branchList = new ArrayList<Branch>();
    private AvailableRentals availableRentals;

    public Composite() { }

    public Composite(User user, Instrument instrument, List<Instrument> instrumentList, Branch branch, List<Branch> branchList, AvailableRentals availableRentals) {
        this.user = user;
        this.instrument = instrument;
        this.instrumentList = instrumentList;
        this.branch = branch;
        this.branchList = branchList;
        this.availableRentals = availableRentals;
    }

    public User getUser() {
        return user;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public List<Instrument> getInstrumentList() { return instrumentList; }

    public Branch getBranch() { return branch; }

    public List<Branch> getBranchList() { return branchList; }

    public AvailableRentals getAvailableRentals() {
        return availableRentals;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setInstrumentList(List<Instrument> instrumentList) { this.instrumentList = instrumentList; }

    public void setBranch(Branch branch) { this.branch = branch; }

    public void setBranchList(List<Branch> branchList) { this.branchList = branchList; }

    public void setAvailableRentals(AvailableRentals availableRentals) {
        this.availableRentals = availableRentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composite that = (Composite) o;
        return Objects.equals(user, that.user) && Objects.equals(instrument, that.instrument) && Objects.equals(instrumentList, that.instrumentList) && Objects.equals(branch, that.branch) && Objects.equals(branchList, that.branchList) && Objects.equals(availableRentals, that.availableRentals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, instrument, instrumentList, branch, branchList, availableRentals);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "user=" + user +
                ", instrument=" + instrument +
                ", instrumentList=" + instrumentList +
                ", branch=" + branch +
                ", branchList=" + branchList +
                ", availableRentals=" + availableRentals +
                '}';
    }

    public boolean validate() {
        if (user == null || instrument == null || instrumentList == null || branch == null || branchList == null || availableRentals == null) {
            return false;
        }
        return true;
    }

}
