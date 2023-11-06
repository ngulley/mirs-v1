package com.mirs.model.services.instrument;

import com.mirs.model.domain.Instrument;
import com.mirs.model.domain.InstrumentStatus;
import com.mirs.model.domain.InstrumentType;
import com.mirs.model.services.exception.InstrumentException;

import java.util.*;

public class InstrumentServiceImpl implements IInstrumentService {

    /**
     * Temporary persistence mechanism
     */
    private Map<Integer, Instrument> instruments = new HashMap<Integer, Instrument>();

    public boolean add(Instrument instrument) throws InstrumentException {
        if (this.getById(instrument.getId()) != null) {
            throw new InstrumentException("An instrument with the same instrument id already exists!");
        }
        this.instruments.put(instrument.getId(), instrument);
        System.out.println("Instrument [" + instrument.getId() + " : " + instrument.getName() + "] was successfully added to system!");
        return true;
    }

    public boolean update(Instrument instrument) throws InstrumentException {
        if (this.getById(instrument.getId()) == null) {
            throw new InstrumentException("An instrument with instrument id " + instrument.getId() + " doesn't exists!");
        }
        this.instruments.put(instrument.getId(), instrument);
        System.out.println("Instrument was successfully updated in system!");
        return true;
    }

    public boolean delete(Integer instrumentId) throws InstrumentException {
        Instrument i = this.getById(instrumentId);
        if (i == null) {
            throw new InstrumentException("An instrument with instrument id " + instrumentId + " doesn't exists!");
        } else if (i.getStatus() == InstrumentStatus.RENTED) {
            throw new InstrumentException("Instrument " + instrumentId + " is currently in RENTED status. Only instruments " +
                    "with a status of AVAILABLE and BROKEN can be deleted.!");
        }
        this.instruments.remove(instrumentId);
        System.out.println("Instrument was successfully deleted from system!");
        return true;
    }

    public Collection getAll() {
        return this.instruments.values();
    }

    public Instrument getById(Integer id) {
        return this.instruments.get(id);
    }

    public List<Instrument> getByType(InstrumentType type) {
        List<Instrument> results = new ArrayList<Instrument>();
        for (Instrument i : this.instruments.values()) {
            if (i.getType().equals(type)) {
                results.add(i);
            }
        }

        return results;
    }

    public List<Instrument> getByStatus(InstrumentStatus status) {
        List<Instrument> results = new ArrayList<Instrument>();
        for (Instrument i : this.instruments.values()) {
            if (i.getStatus().equals(status)) {
                results.add(i);
            }
        }
        return results;
    }


}
