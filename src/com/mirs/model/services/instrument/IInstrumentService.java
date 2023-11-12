package com.mirs.model.services.instrument;

import com.mirs.model.domain.Instrument;
import com.mirs.model.domain.InstrumentStatus;
import com.mirs.model.domain.InstrumentType;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.InstrumentException;

import java.util.Collection;
import java.util.List;

public interface IInstrumentService extends IService {

    public boolean add(Instrument instrument) throws InstrumentException;

    public boolean update(Instrument instrument) throws InstrumentException;

    public boolean delete(Integer instrumentId) throws InstrumentException;

    public Collection getAll();

    public Instrument getById(Integer id);

    public List<Instrument> getByType(InstrumentType type);

    public List<Instrument> getByStatus(InstrumentStatus status);

}
