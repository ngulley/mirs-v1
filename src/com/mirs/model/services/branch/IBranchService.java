package com.mirs.model.services.branch;

import com.mirs.model.domain.Branch;
import com.mirs.model.domain.BranchStatus;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.BranchException;

import java.util.Collection;
import java.util.List;

public interface IBranchService extends IService {

    public boolean add(Branch branch) throws BranchException;

    public boolean update(Branch branch) throws BranchException;

    public boolean delete(Integer branchId) throws BranchException;

    public Collection getAll();

    public Branch getById(Integer id);

    public List<Branch> getByStatus(BranchStatus status);

}
