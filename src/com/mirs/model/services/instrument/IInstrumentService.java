package com.mirs.model.services.instrument;

import com.mirs.model.domain.Composite;
import com.mirs.model.domain.Instrument;
import com.mirs.model.domain.InstrumentStatus;
import com.mirs.model.domain.InstrumentType;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.InstrumentException;

import java.util.List;

public interface IInstrumentService extends IService {
    public final String NAME = "IInstrumentService";

    public boolean list(Composite composite) throws InstrumentException;

    public boolean add(Instrument instrument, Composite composite) throws InstrumentException;

    public boolean update(Instrument instrument, Composite composite) throws InstrumentException;

    public boolean delete(Integer instrumentId, Composite composite) throws InstrumentException;

    public boolean getById(Integer id, Composite composite);

    public boolean getByType(InstrumentType type, Composite composite);

    public boolean getByStatus(InstrumentStatus status, Composite composite);


    /** OLD
    public boolean add(Instrument instrument) throws InstrumentException;

    public boolean update(Instrument instrument) throws InstrumentException;

    public boolean delete(Integer instrumentId) throws InstrumentException;

    public List<Instrument> list() throws InstrumentException;

    public Instrument getById(Integer id);

    public List<Instrument> getByType(InstrumentType type);

    public List<Instrument> getByStatus(InstrumentStatus status);
     */

}
