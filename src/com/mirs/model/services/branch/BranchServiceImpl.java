package com.mirs.model.services.branch;

import com.mirs.model.domain.*;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.BranchException;
import com.mirs.model.services.exception.InstrumentException;

import java.util.*;

public class BranchServiceImpl implements IBranchService {
    /**
     * Temporary persistence mechanism
     */
    private Map<Integer, Branch> branches = new HashMap<Integer, Branch>();

    public boolean list(Composite composite) throws BranchException {
        boolean isListed = false;
        Collection c = this.branches.values();
        List <Branch> branchtList = new ArrayList<Branch>();

        for (Object o : c) {
            branchtList.add((Branch)o);
        }

        composite.setBranchList(branchtList);
        isListed = true;
        return isListed;
    }

    public boolean add(Branch branch, Composite composite) throws BranchException {
        if (this.branches.get(branch.getId()) != null) {
            throw new BranchException("Unable to add because a branch with the same branch id already exists in the system!");
        }
        this.branches.put(branch.getId(), branch);
        this.list(composite);
        System.out.println("Branch [" + branch.getId() + " : " + branch.getName() + "] was successfully added to system!");
        return true;
    }

    public boolean update(Branch branch, Composite composite) throws BranchException {
        if (this.branches.get(branch.getId()) == null) {
            throw new BranchException("Unable to update because a branch with branch id " + branch.getId() + " doesn't exists in the system!");
        }
        this.branches.put(branch.getId(), branch);
        this.list(composite);
        System.out.println("Branch was successfully updated in system!");
        return true;
    }

    public boolean delete(Integer branchId, Composite composite) throws BranchException {
        Branch i = this.branches.get(branchId);
        if (i == null) {
            throw new BranchException("Unable to delete because a branch with branch id " + branchId + " doesn't exist in the system!");
        } else if (i.getStatus() == BranchStatus.ACTIVE) {
            throw new BranchException("Unable to delete because branch " + branchId + " is currently in ACTIVE status. Only branches " +
                    "with a status of INACTIVE can be deleted.!");
        } else if (i.getStatus() == BranchStatus.INACTIVE) {
            this.branches.remove(branchId);
            this.list(composite);
            System.out.println("Branch was successfully deleted from the system!");
            return true;
        } else {
            return false;
        }
    }

    public boolean getById(Integer id, Composite composite) {
        Branch b = this.branches.get(id);
        if (b != null) {
            composite.setBranch(b);
            return true;
        }
        return false;
    }

    public boolean getByStatus(BranchStatus status, Composite composite) {
        List<Branch> results = new ArrayList<Branch>();
        for (Branch b : this.branches.values()) {
            if (b.getStatus().equals(status)) {
                results.add(b);
            }
        }
        composite.setBranchList(results);
        return !results.isEmpty();
    }
}
