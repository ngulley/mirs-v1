package com.mirs.model.business;

import com.mirs.model.business.exception.ServiceLoadException;
import com.mirs.model.domain.*;
import com.mirs.model.services.branch.IBranchService;
import com.mirs.model.services.exception.InstrumentException;
import com.mirs.model.services.factory.ServiceFactory;
import com.mirs.model.services.instrument.IInstrumentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Musical Instrument Rental System version 1.0");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IBranchService branchService = null;
        IInstrumentService instrumentService = null;

        try {
            branchService = (IBranchService)serviceFactory.getService("IBranchService");
            instrumentService = (IInstrumentService)serviceFactory.getService("IInstrumentService");
        } catch (ServiceLoadException e) {
            throw new RuntimeException(e);
        }

        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        Branch branch2 = new Branch(2, "Music Store # 2", "7082091000",
                new Address("405 W Roosevelt Rd", "", "Lombard", "IL", "60811"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        Instrument instrument2 = new Instrument(2, "Classical Guitar", InstrumentType.STRING, "ABC123", "200000", InstrumentStatus.AVAILABLE);
        Instrument instrument3 = new Instrument(3, "Saxophone", InstrumentType.BRASS, "DEF456", "300000", InstrumentStatus.RENTED);
        Instrument instrument4 = new Instrument(4, "Bongo Drum", InstrumentType.PERCUSSION, "GHI789", "400000", InstrumentStatus.BROKEN);


        try {
            System.out.println("Add branches to system...");
            branchService.add(branch1);
            branchService.add(branch2);
            System.out.println();

            System.out.println("Get all branches from system...");
            Collection allBranches = branchService.getAll();
            for (Object i: allBranches) {
                System.out.println(i.toString());
            }
            System.out.println();

            System.out.println("Get ACTIVE branches from system...");
            List<Branch> activeBranches = branchService.getByStatus(BranchStatus.ACTIVE);
            for (Branch i: activeBranches) {
                System.out.println(i.toString());
            }
            System.out.println();

            System.out.println("Update branch in system...");
            Branch branch2a = new Branch(2, "Music Store # 2", "7082091010",
                    new Address("405 W Roosevelt Rd", "", "Lombard", "IL", "60811"),
                    BranchStatus.INACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

            branchService.update(branch2a);
            System.out.println();

            System.out.println("Delete branch from system...");
            branchService.delete(branch2a.getId());
            System.out.println();

            System.out.println("Get all branches from system...");
            allBranches = branchService.getAll();
            for (Object i: allBranches) {
                System.out.println(i.toString());
            }
            System.out.println();



            System.out.println("Add instruments to system...");
            instrumentService.add(instrument1);
            instrumentService.add(instrument2);
            instrumentService.add(instrument3);
            instrumentService.add(instrument4);
            System.out.println();

            System.out.println("Get all instruments from system...");
            Collection allInstruments = instrumentService.getAll();
            for (Object i: allInstruments) {
                System.out.println(i.toString());
            }
            System.out.println();

            System.out.println("Get available instruments from system...");
            List<Instrument> availableInstruments = instrumentService.getByStatus(InstrumentStatus.AVAILABLE);
            for (Instrument i: availableInstruments) {
                System.out.println(i.toString());
            }
            System.out.println();

            System.out.println("Update instrument in system...");
            Instrument instrument3a = new Instrument(3, "Saxophone", InstrumentType.BRASS, "DEF456", "300000", InstrumentStatus.AVAILABLE);
            instrumentService.update(instrument3a);
            System.out.println();

            System.out.println("Delete instrument from system...");
            instrumentService.delete(instrument3a.getId());
            System.out.println();

            System.out.println("Get all instruments from system...");
            allInstruments = instrumentService.getAll();
            for (Object i: allInstruments) {
                System.out.println(i.toString());
            }
            System.out.println();


        } catch (InstrumentException ie) {
            System.out.println(ie);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
