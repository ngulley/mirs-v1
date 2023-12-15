package com.mirs.model.services.reservation;

import com.mirs.model.domain.Reservation;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.ReservationException;

import java.util.List;

public interface IReservationService extends IService {
    public final String NAME = "IReservationService";

    public List<Reservation> list() throws ReservationException;

    public boolean add(Reservation reservation) throws ReservationException;

    public boolean update(Reservation reservation) throws ReservationException;

    public boolean delete(Integer rentalId) throws ReservationException;

    public Reservation getById(Integer id);

}
