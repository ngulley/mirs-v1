package com.mirs.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite implements Serializable {
    private static final long serialVersionUID = -334371602256955290L;
    private User user;
    private List<User> userList;
    private Instrument instrument;
    private List<Instrument> instrumentList = new ArrayList<Instrument>();
    private Branch branch;
    private List<Branch> branchList = new ArrayList<Branch>();
    private Reservation reservation;
    private List<Reservation> reservationList = new ArrayList<Reservation>();

    public Composite() { }

    public Composite(User user, List<User> userList, Instrument instrument, List<Instrument> instrumentList, Branch branch, List<Branch> branchList, Reservation reservation, List<Reservation> reservationList) {
        this.user = user;
        this.userList = userList;
        this.instrument = instrument;
        this.instrumentList = instrumentList;
        this.branch = branch;
        this.branchList = branchList;
        this.reservation = reservation;
        this.reservationList = reservationList;
    }

    public User getUser() {
        return user;
    }

    public List<User> getUserList() { return userList; }

    public Instrument getInstrument() {
        return instrument;
    }

    public List<Instrument> getInstrumentList() { return instrumentList; }

    public Branch getBranch() { return branch; }

    public List<Branch> getBranchList() { return branchList; }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserList(List<User> userList) { this.userList = userList; }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setInstrumentList(List<Instrument> instrumentList) { this.instrumentList = instrumentList; }

    public void setBranch(Branch branch) { this.branch = branch; }

    public void setBranchList(List<Branch> branchList) { this.branchList = branchList; }

    public Reservation getReservation() { return reservation; }

    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public List<Reservation> getReservationList() { return reservationList; }

    public void setReservationList(List<Reservation> reservationList) { this.reservationList = reservationList; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composite that = (Composite) o;
        return Objects.equals(user, that.user) && Objects.equals(userList, that.userList) && Objects.equals(instrument, that.instrument) && Objects.equals(instrumentList, that.instrumentList) && Objects.equals(branch, that.branch) && Objects.equals(branchList, that.branchList) && Objects.equals(reservation, that.reservation) && Objects.equals(reservationList, that.reservationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, userList, instrument, instrumentList, branch, branchList, reservation, reservationList);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "user=" + user +
                ", userList=" + userList +
                ", instrument=" + instrument +
                ", instrumentList=" + instrumentList +
                ", branch=" + branch +
                ", branchList=" + branchList +
                ", reservation=" + reservation +
                ", reservationList=" + reservationList +
                '}';
    }

    public boolean validate() {
        if (user == null || userList == null || instrument == null || instrumentList == null || branch == null || branchList == null || reservation == null || reservationList == null) {
            return false;
        }
        return true;
    }

}
