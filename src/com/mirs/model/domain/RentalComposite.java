package com.mirs.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class RentalComposite implements Serializable {
    private static final long serialVersionUID = -334371602256955290L;
    private User customer;
    private AvailableRentals availableRentals;
    private Instrument rentedInstrument;

    public RentalComposite(User customer, AvailableRentals availableRentals, Instrument rentedInstrument) {
        this.customer = customer;
        this.availableRentals = availableRentals;
        this.rentedInstrument = rentedInstrument;
    }

    public User getCustomer() {
        return customer;
    }

    public AvailableRentals getAvailableRentals() {
        return availableRentals;
    }

    public Instrument getRentedInstrument() {
        return rentedInstrument;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setAvailableRentals(AvailableRentals availableRentals) {
        this.availableRentals = availableRentals;
    }

    public void setRentedInstrument(Instrument rentedInstrument) {
        this.rentedInstrument = rentedInstrument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalComposite that = (RentalComposite) o;
        return Objects.equals(customer, that.customer) && Objects.equals(availableRentals, that.availableRentals) && Objects.equals(rentedInstrument, that.rentedInstrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, availableRentals, rentedInstrument);
    }

    @Override
    public String toString() {
        return "RentalComposite{" +
                "customer=" + customer +
                ", availableRentals=" + availableRentals +
                ", rentedInstrument=" + rentedInstrument +
                '}';
    }

    public boolean validate() {
        if (customer == null || availableRentals == null || rentedInstrument == null) {
            return false;
        }
        return true;
    }
}
