package com.mirs.model.services.instrument;

import com.mirs.model.domain.*;
import com.mirs.model.services.exception.InstrumentException;
import com.mirs.model.services.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class InstrumentServiceImplTest {

    IInstrumentService instrumentService;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        ServiceFactory factory = ServiceFactory.getInstance();
        instrumentService = (IInstrumentService)factory.getService("IInstrumentService");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#add(Instrument)}.
     */
    @Test
    public final void testAdd1() {
        System.out.println("starting testAdd1()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            assertTrue ("Instrument is added", instrumentService.add(instrument1));
        } catch (InstrumentException e) {
            System.out.println("testAdd1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testAdd1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#add(Instrument)}.
     */
    @Test
    public final void testAdd2() {
        System.out.println("starting testAdd2()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            assertTrue ("Instrument is added", instrumentService.add(instrument1));
        } catch (InstrumentException e) {
            System.out.println("testAdd2 FAILED");
            throw new RuntimeException(e);
        }

        try {
            // Try to add an instrument that has the same id as an existing instrument in the system. An InstrumentException should be thrown
            Instrument instrument2 = new Instrument(1, "Base Guitar", InstrumentType.STRING, "ABC789", "200000", InstrumentStatus.AVAILABLE);
            instrumentService.add(instrument2);
            System.out.println("testAdd2 FAILED");
            throw new RuntimeException();
        } catch (InstrumentException e) {
            System.out.println("testAdd2 PASSED: " + e.toString());
        }

    }



    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#add(Instrument)}.
     */
    @Test
    public final void testGetAll1() {
        System.out.println("starting testGatAll1()");
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
            System.out.println("testGatAll1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testGatAll1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#update(Instrument)}.
     */
    @Test
    public final void testUpdate1() {
        System.out.println("starting testUpdate1()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            instrumentService.add(instrument1);
            Instrument instrument1b = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.RENTED);

            assertTrue ("Instrument is updated", instrumentService.update(instrument1b));
        } catch (InstrumentException e) {
            System.out.println("testUpdate1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testUpdate1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#update(Instrument)}.
     */
    @Test
    public final void testUpdate2() {
        System.out.println("starting testUpdate2()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            instrumentService.add(instrument1);
            Instrument instrument1b = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.RENTED);

            assertTrue ("Instrument is updated", instrumentService.update(instrument1b));
        } catch (InstrumentException e) {
            System.out.println("testUpdate2 FAILED");
            throw new RuntimeException(e);
        }

        try {
            // Try to update an instrument that doesn't have the same id as an existing instrument in the system. An exception should be thrown
            Instrument instrument2 = new Instrument(2, "Base Guitar", InstrumentType.STRING, "ABC789", "200000", InstrumentStatus.AVAILABLE);
            instrumentService.update(instrument2);
            System.out.println("testUpdate2 FAILED");
            throw new RuntimeException();
        } catch (InstrumentException e) {
            System.out.println("testUpdate2 PASSED: " + e.toString());
        }


    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#update(Instrument)}.
     */
    @Test
    public final void testDelete1() {
        System.out.println("starting testDelete1()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            instrumentService.add(instrument1);

            assertTrue ("Instrument is deleted", instrumentService.delete(instrument1.getId()));
        } catch (InstrumentException e) {
            System.out.println("testDelete1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testDelete1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.instrument.InstrumentServiceImpl#update(Instrument)}.
     */
    @Test
    public final void testDelete2() {
        System.out.println("starting testDelete2()");
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);

        try {
            instrumentService.add(instrument1);

            assertTrue ("Instrument is deleted", instrumentService.delete(instrument1.getId()));
        } catch (InstrumentException e) {
            System.out.println("testDelete2 FAILED");
            throw new RuntimeException(e);
        }

        try {
            // Try to delete an instrument that doesn't have the same id as an existing instrument in the system. An InstrumentException should be thrown
            instrumentService.delete(2);
            System.out.println("testDelete2 FAILED");
            throw new RuntimeException();
        } catch (InstrumentException e) {
            System.out.println("testDelete2 PASSED: " + e.toString());
        }

    }
}
