package com.mirs.model.services.reservation;

import com.mirs.model.domain.Reservation;
import com.mirs.model.services.exception.ReservationException;

import java.util.*;

public class ReservationServiceImpl implements IReservationService {
    /**
     * Temporary persistence mechanism
     */
    private Map<Integer, Reservation> reservations = new HashMap<Integer, Reservation>();

    public List<Reservation> list() throws ReservationException {
        boolean isListed = false;
        Collection<Reservation> c = this.reservations.values();
        List <Reservation> reservationList = new ArrayList<Reservation>();

        reservationList.addAll(c);

        return reservationList;
    }

    public boolean add(Reservation reservation) throws ReservationException {
        if (this.reservations.get(reservation.getId()) != null) {
            throw new ReservationException("Unable to add because a reservation with the same reservation id already exists in the system!");
        }
        this.reservations.put(reservation.getId(), reservation);
        System.out.println("Reservation [" + reservation.getId() + "] was successfully added to system!");
        return true;
    }

    public boolean update(Reservation reservation) throws ReservationException {
        if (this.reservations.get(reservation.getId()) == null) {
            throw new ReservationException("Unable to update because a reservation with reservation id " + reservation.getId() + " doesn't exists in the system!");
        }
        this.reservations.put(reservation.getId(), reservation);
        System.out.println("Reservation was successfully updated in system!");
        return true;
    }

    public boolean delete(Integer reservationId) throws ReservationException {
        Reservation i = this.reservations.get(reservationId);
        if (i == null) {
            throw new ReservationException("Unable to delete because a reservation with reservation id " + reservationId + " doesn't exist in the system!");
        } else {
            this.reservations.remove(reservationId);
            System.out.println("Reservation was successfully deleted from the system!");
            return true;
        }
    }

    public Reservation getById(Integer id) {
        return this.reservations.get(id);
    }
}
