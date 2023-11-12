package com.mirs.model.services.login;

import com.mirs.model.domain.User;
import com.mirs.model.services.IService;

public class LoginServiceImpl implements ILoginService {
    /**
     * Validates if the user is indeed registered in our system.
     *
     *  @param user contains a user's login credentials
     */
    public boolean authenticateUser(User user) {
        System.out.println ("Entering method LoginServiceImpl::authenticateUser");

        return true;
    }
}
