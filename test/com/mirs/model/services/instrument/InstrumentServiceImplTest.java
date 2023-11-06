package com.mirs.model.services.instrument;

import com.mirs.model.domain.*;
import com.mirs.model.services.exception.InstrumentException;
import com.mirs.model.services.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InstrumentServiceImplTest {

    IInstrumentService instrumentService;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        ServiceFactory factory = new ServiceFactory();
        instrumentService = factory.getInstrumentService();
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        Instrument instrument2 = new Instrument(2, "Classical Guitar", InstrumentType.STRING, "ABC123", "200000", InstrumentStatus.AVAILABLE);
        Instrument instrument3 = new Instrument(3, "Saxophone", InstrumentType.BRASS, "DEF456", "300000", InstrumentStatus.RENTED);
        Instrument instrument4 = new Instrument(4, "Bongo Drum", InstrumentType.PERCUSSION, "GHI789", "400000", InstrumentStatus.BROKEN);


    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#add(Instrument)}.
     */
    @Test
    public final void testAdd() {
        System.out.println("starting testAdd()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            assertTrue ("Instrument is added", instrumentService.add(instrument1));
        } catch (InstrumentException e) {
            System.out.println("testAdd FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testAdd PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#add(Instrument)}.
     */
    @Test
    public final void testGetAll() {
        System.out.println("starting testGatAll()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        Instrument instrument2 = new Instrument(2, "Classical Guitar", InstrumentType.STRING, "ABC123", "200000", InstrumentStatus.AVAILABLE);
        Instrument instrument3 = new Instrument(3, "Saxophone", InstrumentType.BRASS, "DEF456", "300000", InstrumentStatus.RENTED);
        Instrument instrument4 = new Instrument(4, "Bongo Drum", InstrumentType.PERCUSSION, "GHI789", "400000", InstrumentStatus.BROKEN);

        try {
            instrumentService.add(instrument1);
            instrumentService.add(instrument2);
            instrumentService.add(instrument3);
            instrumentService.add(instrument4);


            assertTrue ("All instruments were got", instrumentService.getAll().size() == 4);
        } catch (InstrumentException e) {
            System.out.println("testGatAll FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testGatAll PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#update(Instrument)}.
     */
    @Test
    public final void testUpdate() {
        System.out.println("starting testUpdate()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            instrumentService.add(instrument1);
            Instrument instrument1b = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.RENTED);

            assertTrue ("Instrument is updated", instrumentService.update(instrument1b));
        } catch (InstrumentException e) {
            System.out.println("testUpdate FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testUpdate PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#update(Instrument)}.
     */
    @Test
    public final void testDelete() {
        System.out.println("starting testDelete()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            instrumentService.add(instrument1);

            assertTrue ("Instrument is deleted", instrumentService.delete(instrument1.getId()));
        } catch (InstrumentException e) {
            System.out.println("testDelete FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testDelete PASSED");
    }
}
