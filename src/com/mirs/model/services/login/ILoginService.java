package com.mirs.model.services.login;

import com.mirs.model.domain.RentalComposite;

import com.mirs.model.domain.User;
import com.mirs.model.services.IService;
import com.mirs.model.services.exception.LoginException;

public interface ILoginService extends IService {
    public final String NAME = "ILoginService";
    /**
     * Authenticate customer into our application
     * @param rentalComposite
     * 							contains customer information to be authenticated
     * @throws LoginException
     * 							If the Customer object in the composite is null or
     *   				        If the file that contains the customer information is not found or
     *   					    If an unexpected exception is encountered while accessing the file.
     * @return boolean
     * 					true - if authenticated
     * 				    false - if failed to authenticate
     * */
    public boolean authenticateCustomer(RentalComposite rentalComposite) throws LoginException;

    public boolean authenticateAdmin(User user) throws LoginException;
}
