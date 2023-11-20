package com.mirs.model.services.login;

import com.mirs.model.domain.Composite;
import com.mirs.model.domain.User;
import com.mirs.model.services.exception.LoginException;

public class LoginServiceImpl implements ILoginService {
    /**
     * Authenticate a user into our application
     * @param composite
     * 							contains user information to be authenticated
     * @throws LoginException
     * 							If the User object in the composite is null or
     *   				        If the file that contains the customer information is not found or
     *   					    If an unexpected exception is encountered while accessing the file.
     * @return boolean
     * 					true - if authenticated
     * 				    false - if failed to authenticate
     * */
    public boolean authenticateUser(Composite composite) throws LoginException {
        System.out.println ("Entering method LoginServiceImpl::authenticateUser");

        return true;
    }

    public boolean logoutUser(Composite composite) throws LoginException {
        System.out.println ("Entering method LoginServiceImpl::logoutUser");

        return true;
    }

}
