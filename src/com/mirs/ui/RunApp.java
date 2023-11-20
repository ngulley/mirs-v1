package com.mirs.ui;

import com.mirs.model.business.manager.InstrumentRentalManager;
import com.mirs.model.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunApp {
    public static void main(String[] args) {
        System.out.println("Musical Instrument Rental System version 1.0");

        StringBuilder message = new StringBuilder();
        InstrumentRentalManager manager = InstrumentRentalManager.getInstance();

        Address address1 = new Address("100 W Circle Ave", "Apt 100", "Nashville", "TN", "43400");

        User user1 = new User("tswift@gmail.com", "Pa55word!", "Taylor", "Swift", "8126559090", address1, Role.CUSTOMER);


        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        Branch branch2 = new Branch(2, "Music Store # 2", "7082091000",
                new Address("405 W Roosevelt Rd", "", "Lombard", "IL", "60811"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        Branch branch3 = new Branch(3, "Music Store # 3", "7738412323",
                new Address("100 W Madison Ave", "", "Chicago", "IL", "60623"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        Instrument instrument2 = new Instrument(2, "Classical Guitar", InstrumentType.STRING, "ABC123", "200000", InstrumentStatus.AVAILABLE);
        Instrument instrument3 = new Instrument(3, "Saxophone", InstrumentType.BRASS, "DEF456", "300000", InstrumentStatus.RENTED);
        Instrument instrument4 = new Instrument(4, "Bongo Drum", InstrumentType.PERCUSSION, "GHI789", "400000", InstrumentStatus.BROKEN);

        Composite composite = new Composite();
        composite.setUser(user1);
        // composite.setBranchList(Arrays.asList(branch1, branch2));
        // composite.setInstrumentList(Arrays.asList(instrument1, instrument2, instrument3, instrument4));

        // TEST # 1
        composite.setBranch(branch1);
        boolean isAdded = manager.performAction("ADD_BRANCH", composite);
        if (isAdded) {
            message.append("\nSUCCESS:  RunApp:: - Branch added. ");
        } else {
            message.append("\nFAIL:  RunApp:: - Branch not added. ");
        }

        List<Branch> branches = composite.getBranchList();
        for (Branch branch : branches) {
            message.append(branch.toString());
        }

        // TEST # 2
        composite.setInstrument(instrument1);
        isAdded = manager.performAction("ADD_INSTRUMENT", composite);
        if (isAdded) {
            message.append("\nSUCCESS:  RunApp:: - Instrument added.");
        } else {
            message.append("\nFAIL:  RunApp:: - Instrument not added.");
        }

        List<Instrument> instruments = composite.getInstrumentList();
        for (Instrument instrument : instruments) {
            message.append(instrument.toString());
        }

        System.out.println(message);
    }
}
