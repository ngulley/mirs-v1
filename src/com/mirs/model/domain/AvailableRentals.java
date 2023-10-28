package com.mirs.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvailableRentals  implements Serializable {

    private static final long serialVersionUID = -753517717949767546L;
    private boolean available;
    private List<Instrument> availableRentalsList = new ArrayList<Instrument>(1);

    public AvailableRentals(boolean available, List<Instrument> availableRentalsList) {
        this.available = available;
        this.availableRentalsList = availableRentalsList;
    }

    public void addRental(Instrument instrument) {
        this.availableRentalsList.add(instrument);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Instrument> getAvailableRentalsList() {
        return availableRentalsList;
    }

    public void setAvailableRentalsList(List<Instrument> availableRentalsList) {
        this.availableRentalsList = availableRentalsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableRentals that = (AvailableRentals) o;
        return available == that.available && Objects.equals(availableRentalsList, that.availableRentalsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(available, availableRentalsList);
    }

    @Override
    public String toString() {
        return "AvailableRentals{" +
                "available=" + available +
                ", availableRentalsList=" + availableRentalsList +
                '}';
    }

    public boolean validate() {
        if (availableRentalsList == null) {
            return false;
        }
        return true;
    }
}
