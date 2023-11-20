package com.mirs.model.services.branch;

import com.mirs.model.domain.*;
import com.mirs.model.services.exception.BranchException;
import com.mirs.model.services.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class BranchServiceImplTest {

    IBranchService branchService;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        ServiceFactory factory = ServiceFactory.getInstance();
        branchService = (IBranchService)factory.getService("IBranchService");
    }

    /**
     * Test method for {@link com.mirs.model.services.branch.BranchServiceImpl#add(Branch, Composite)}.
     */
    @Test
    public final void testAdd1() {
        System.out.println("starting testAdd1()");
        Composite composite = new Composite();
        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        try {
            assertTrue ("Branch is added", branchService.add(branch1, composite));
        } catch (BranchException e) {
            System.out.println("testAdd1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testAdd1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.branch.BranchServiceImpl#add(Branch, Composite)}.
     */
    @Test
    public final void testAdd2() {
        System.out.println("starting testAdd2()");
        Composite composite = new Composite();
        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        try {
            assertTrue ("Branch is added", branchService.add(branch1, composite));
        } catch (BranchException e) {
            System.out.println("testAdd2 FAILED");
            throw new RuntimeException(e);
        }

        try {
            // Try to add an branch that has the same id as an existing branch in the system. An exception should be thrown
            Branch branch2 = new Branch(1, "Music Store # 2", "7082091000",
                    new Address("405 W Roosevelt Rd", "", "Lombard", "IL", "60811"),
                    BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

            branchService.add(branch2, composite);
            System.out.println("testAdd2 FAILED");
            throw new RuntimeException();
        } catch (BranchException e) {
            System.out.println("testAdd2 PASSED: " + e.toString());
        }
    }



    /**
     * Test method for {@link com.mirs.model.services.branch.BranchServiceImpl#add(Branch, Composite)}.
     */
    @Test
    public final void testList1() {
        System.out.println("starting testList1()");
        Composite composite = new Composite();
        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        Branch branch2 = new Branch(2, "Music Store # 2", "7082091000",
                new Address("405 W Roosevelt Rd", "", "Lombard", "IL", "60811"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        try {
            branchService.add(branch1, composite);
            branchService.add(branch2, composite);
            assertTrue ("All branches were got", branchService.list(composite));
        } catch (BranchException e) {
            System.out.println("testGatAll1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testGatAll1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.branch.BranchServiceImpl#update(Branch, Composite)}.
     */
    @Test
    public final void testUpdate1() {
        System.out.println("starting testUpdate1()");
        Composite composite = new Composite();
        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        try {
            branchService.add(branch1, composite);
            Branch branch1b = new Branch(1, "The Music Store # 1", "7082095555",
                    new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                    BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

            assertTrue ("Branch is updated", branchService.update(branch1b, composite));
        } catch (BranchException e) {
            System.out.println("testUpdate1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testUpdate1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.branch.BranchServiceImpl#update(Branch, Composite)}.
     */
    @Test
    public final void testUpdate2() {
        System.out.println("starting testUpdate2()");
        Composite composite = new Composite();
        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        try {
            branchService.add(branch1, composite);

            assertTrue ("Branch is updated", branchService.update(branch1, composite));
        } catch (BranchException e) {
            System.out.println("testUpdate2 FAILED");
            throw new RuntimeException(e);
        }

        try {
            // Try to update an branch that doesn't have the same id as an existing branch in the system. A BranchException should be thrown
            Branch branch1b = new Branch(2, "Music Store # 1", "7082094556",
                    new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                    BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());
            branchService.update(branch1b, composite);
            System.out.println("testUpdate2 FAILED");
            throw new RuntimeException();
        } catch (BranchException e) {
            System.out.println("testUpdate2 PASSED: " + e.toString());
        }


    }

    /**
     * Test method for {@link com.mirs.model.services.branch.BranchServiceImpl#update(Branch, Composite)}.
     */
    @Test
    public final void testDelete1() {
        System.out.println("starting testDelete1()");
        Composite composite = new Composite();
        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.INACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        try {
            branchService.add(branch1, composite);

            assertTrue ("Branch is deleted", branchService.delete(branch1.getId(), composite));
        } catch (BranchException e) {
            System.out.println("testDelete1 FAILED");
            throw new RuntimeException(e);
        }

        System.out.println("testDelete1 PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.services.branch.BranchServiceImpl#update(Branch, Composite)}.
     */
    @Test
    public final void testDelete2() {
        System.out.println("starting testDelete2()");
        Composite composite = new Composite();
        Branch branch1 = new Branch(1, "Music Store # 1", "7082094545",
                new Address("6100 W North Ave", "", "Oak Park", "IL", "60302"),
                BranchStatus.ACTIVE, new AvailableRentals(true, new ArrayList<Instrument>()), new ArrayList<Instrument>());

        try {
            branchService.add(branch1, composite);

        } catch (BranchException e) {
            System.out.println("testDelete2 FAILED");
            throw new RuntimeException(e);
        }

        try {
            // Try to delete an branch that doesn't have the same id as an existing branch in the system. A BranchException should be thrown
            branchService.delete(2, composite);
            System.out.println("testDelete2 FAILED");
            throw new RuntimeException();
        } catch (BranchException e) {
            System.out.println("testDelete2 PASSED: " + e.toString());
        }

    }
}
