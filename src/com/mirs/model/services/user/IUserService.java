package com.mirs.model.services.user;

import com.mirs.model.domain.Role;
import com.mirs.model.domain.User;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.UserException;

import java.util.List;

public interface IUserService extends IService {
    public final String NAME = "IUserService";

    List<User> list() throws UserException;
    List<User> list(Role role) throws UserException;
}
