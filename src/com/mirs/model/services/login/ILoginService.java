package com.mirs.model.services.login;

import com.mirs.model.domain.Composite;

import com.mirs.model.domain.User;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.LoginException;

public interface ILoginService extends IService {
    public final String NAME = "ILoginService";

    /**
     * Authenticate a user into our application
     * @param composite
     * 							contains user information to be authenticated
     * @throws LoginException
     * 							If the User object in the composite is null or
     *   				        If the file that contains the user information is not found or
     *   					    If an unexpected exception is encountered while accessing the file.
     * @return boolean
     * 					true - if authenticated
     * 				    false - if failed to authenticate
     * */
    public boolean authenticateUser(Composite composite) throws LoginException;

    public boolean logoutUser(Composite composite) throws LoginException;

}
