package com.mirs.model.services.branch;

import com.mirs.model.domain.*;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.BranchException;

import java.util.*;

public class BranchServiceImpl implements IBranchService {
    /**
     * Temporary persistence mechanism
     */
    private Map<Integer, Branch> branches = new HashMap<Integer, Branch>();

    public boolean add(Branch branch) throws BranchException {
        if (this.getById(branch.getId()) != null) {
            throw new BranchException("Unable to add because a branch with the same branch id already exists in the system!");
        }
        this.branches.put(branch.getId(), branch);
        System.out.println("Branch [" + branch.getId() + " : " + branch.getName() + "] was successfully added to system!");
        return true;
    }

    public boolean update(Branch branch) throws BranchException {
        if (this.getById(branch.getId()) == null) {
            throw new BranchException("Unable to update because a branch with branch id " + branch.getId() + " doesn't exists in the system!");
        }
        this.branches.put(branch.getId(), branch);
        System.out.println("Branch was successfully updated in system!");
        return true;
    }

    public boolean delete(Integer branchId) throws BranchException {
        Branch i = this.getById(branchId);
        if (i == null) {
            throw new BranchException("Unable to delete because a branch with branch id " + branchId + " doesn't exist in the system!");
        } else if (i.getStatus() == BranchStatus.ACTIVE) {
            throw new BranchException("Unable to delete because branch " + branchId + " is currently in ACTIVE status. Only branches " +
                    "with a status of INACTIVE can be deleted.!");
        } else if (i.getStatus() == BranchStatus.INACTIVE) {
            this.branches.remove(branchId);
            System.out.println("Branch was successfully deleted from the system!");
            return true;
        } else {
            return false;
        }
    }

    public Collection getAll() {
        return this.branches.values();
    }

    public Branch getById(Integer id) {
        return this.branches.get(id);
    }

    public List<Branch> getByStatus(BranchStatus status) {
        List<Branch> results = new ArrayList<Branch>();
        for (Branch i : this.branches.values()) {
            if (i.getStatus().equals(status)) {
                results.add(i);
            }
        }
        return results;
    }
}
