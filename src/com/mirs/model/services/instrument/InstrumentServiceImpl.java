package com.mirs.model.services.instrument;

import com.mirs.model.domain.Composite;
import com.mirs.model.domain.Instrument;
import com.mirs.model.domain.InstrumentStatus;
import com.mirs.model.domain.InstrumentType;
import com.mirs.model.services.exception.InstrumentException;

import java.util.*;

public class InstrumentServiceImpl implements IInstrumentService {

    public InstrumentServiceImpl() { }

    /**
     * Temporary persistence mechanism
     */
    private Map<Integer, Instrument> instruments = new HashMap<Integer, Instrument>();

    {
        Instrument instrument1 = new Instrument(1, "Trumpet", InstrumentType.BRASS, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        Instrument instrument2 = new Instrument(2, "Acoustic Guitar", InstrumentType.STRING, "FR100", "98675", InstrumentStatus.AVAILABLE);
        Instrument instrument3 = new Instrument(3, "Drum Set", InstrumentType.PERCUSSION, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        Instrument instrument4 = new Instrument(4, "Xylophone", InstrumentType.KEYBOARD, "V100", "85665", InstrumentStatus.RESERVED);
        Instrument instrument5 = new Instrument(5, "Soprano Flute", InstrumentType.WOODWIND, "BV100", "65675", InstrumentStatus.BROKEN);

        instruments.put(1, instrument1);
        instruments.put(2, instrument2);
        instruments.put(3, instrument3);
        instruments.put(4, instrument4);
        instruments.put(5, instrument5);
    }

    public boolean list(Composite composite) throws InstrumentException {
        boolean isListed = false;
        Collection c = this.instruments.values();
        List <Instrument> instrumentList = new ArrayList<Instrument>();

        for (Object o : c) {
            instrumentList.add((Instrument)o);
        }

        composite.setInstrumentList(instrumentList);
        isListed = true;
        return isListed;
    }

    public boolean add(Instrument instrument, Composite composite) throws InstrumentException {
        if (this.instruments.get(instrument.getId()) != null) {
            throw new InstrumentException("Unable to add because an instrument id " + instrument.getId() + " already exists in the system!");
        }
        this.instruments.put(instrument.getId(), instrument);
        this.list(composite);
        System.out.println("Instrument [" + instrument.getId() + " : " + instrument.getName() + "] was successfully added to system!");
        return true;
    }

    public boolean update(Instrument instrument, Composite composite) throws InstrumentException {
        if (this.instruments.get(instrument.getId()) == null) {
            throw new InstrumentException("Unable to update because instrument id " + instrument.getId() + " doesn't exists in the system!");
        }
        this.instruments.put(instrument.getId(), instrument);
        this.list(composite);
        System.out.println("Instrument id " + instrument.getId() + " was successfully updated in system!");
        return true;
    }

    public boolean delete(Integer instrumentId, Composite composite) throws InstrumentException {
        Instrument i = this.instruments.get(instrumentId);
        if (i == null) {
            throw new InstrumentException("Unable to delete because instrument id " + instrumentId + " doesn't exists in the system!");
        } else if (i.getStatus() == InstrumentStatus.RESERVED) {
            throw new InstrumentException("Unable to delete because instrument " + instrumentId + " is currently in RESERVED status. Only instruments " +
                    "with a status of AVAILABLE or BROKEN can be deleted.!");
        }
        this.instruments.remove(instrumentId);
        this.list(composite);
        System.out.println("Instrument was successfully deleted from the system!");
        return true;
    }

    public boolean getById(Integer id, Composite composite) {
        Instrument i = this.instruments.get(id);
        if (i != null) {
            composite.setInstrument(i);
            return true;
        }
        return false;
    }

    public boolean getByType(InstrumentType type, Composite composite) {
        List<Instrument> results = new ArrayList<Instrument>();
        for (Instrument i : this.instruments.values()) {
            if (i.getType().equals(type)) {
                results.add(i);
            }
        }

        composite.setInstrumentList(results);

        return !results.isEmpty();
    }

    public boolean getByStatus(InstrumentStatus status, Composite composite) {
        List<Instrument> results = new ArrayList<Instrument>();
        for (Instrument i : this.instruments.values()) {
            if (i.getStatus().equals(status)) {
                results.add(i);
            }
        }
        composite.setInstrumentList(results);
        return !results.isEmpty();
    }

    /** OLD
    public List<Instrument> list() throws InstrumentException {
        Collection c = this.instruments.values();
        List <Instrument> instrumentList = new ArrayList<Instrument>();

        for (Object o : c) {
            instrumentList.add((Instrument)o);
        }

        return instrumentList;
    }

    public boolean add(Instrument instrument) throws InstrumentException {
        if (this.getById(instrument.getId()) != null) {
            throw new InstrumentException("Unable to add because an instrument id " + instrument.getId() + " already exists in the system!");
        }
        this.instruments.put(instrument.getId(), instrument);
        System.out.println("Instrument [" + instrument.getId() + " : " + instrument.getName() + "] was successfully added to system!");
        return true;
    }

    public boolean update(Instrument instrument) throws InstrumentException {
        if (this.getById(instrument.getId()) == null) {
            throw new InstrumentException("Unable to update because instrument id " + instrument.getId() + " doesn't exists in the system!");
        }
        this.instruments.put(instrument.getId(), instrument);
        System.out.println("Instrument id " + instrument.getId() + " was successfully updated in system!");
        return true;
    }

    public boolean delete(Integer instrumentId) throws InstrumentException {
        Instrument i = this.getById(instrumentId);
        if (i == null) {
            throw new InstrumentException("Unable to delete because instrument id " + instrumentId + " doesn't exists in the system!");
        } else if (i.getStatus() == InstrumentStatus.RESERVED) {
            throw new InstrumentException("Unable to delete because instrument " + instrumentId + " is currently in RESERVED status. Only instruments " +
                    "with a status of AVAILABLE or BROKEN can be deleted.!");
        }
        this.instruments.remove(instrumentId);
        System.out.println("Instrument was successfully deleted from the system!");
        return true;
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
    */

}
