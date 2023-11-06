package com.mirs.model.business;

import com.mirs.model.domain.*;
import com.mirs.model.services.exception.InstrumentException;
import com.mirs.model.services.factory.ServiceFactory;
import com.mirs.model.services.instrument.IInstrumentService;

import java.util.Collection;
import java.util.List;

public class InstrumentManager {
    public static void main(String[] args) {
        System.out.println("Musical Instrument Rental System version 1.0");

        ServiceFactory factory = new ServiceFactory();
        IInstrumentService instrumentService = factory.getInstrumentService();
        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        Instrument instrument2 = new Instrument(2, "Classical Guitar", InstrumentType.STRING, "ABC123", "200000", InstrumentStatus.AVAILABLE);
        Instrument instrument3 = new Instrument(3, "Saxophone", InstrumentType.BRASS, "DEF456", "300000", InstrumentStatus.RENTED);
        Instrument instrument4 = new Instrument(4, "Bongo Drum", InstrumentType.PERCUSSION, "GHI789", "400000", InstrumentStatus.BROKEN);


        try {
            System.out.println("Add instruments to system...");
            instrumentService.add(instrument1);
            instrumentService.add(instrument2);
            instrumentService.add(instrument3);
            instrumentService.add(instrument4);

            System.out.println("Get all instruments from system...");
            Collection allInstruments = instrumentService.getAll();
            for (Object i: allInstruments) {
                System.out.println(i.toString());
            }

            System.out.println("Get available instruments from system...");
            List<Instrument> availableInstruments = instrumentService.getByStatus(InstrumentStatus.AVAILABLE);
            for (Instrument i: availableInstruments) {
                System.out.println(i.toString());
            }

            System.out.println("Update instrument in system...");
            Instrument instrument3a = new Instrument(3, "Saxophone", InstrumentType.BRASS, "DEF456", "300000", InstrumentStatus.AVAILABLE);
            instrumentService.update(instrument3a);

            System.out.println("Delete instrument from system...");
            instrumentService.delete(instrument3a.getId());

            System.out.println("Get all instruments from system...");
            allInstruments = instrumentService.getAll();
            for (Object i: allInstruments) {
                System.out.println(i.toString());
            }


        } catch (InstrumentException ie) {
            System.out.println(ie);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
