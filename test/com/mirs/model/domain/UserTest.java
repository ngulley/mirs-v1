package com.mirs.model.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user1, user2;
    private Address address1, address2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        address1 = new Address("100 W Circle Ave", "Apt 100", "Nashville", "TN", "43400");
        address2 = new Address("100 W Circle Ave", "Apt 100", "Nashville", "TN", "43400");
        user1 = new User(1,"tswift@gmail.com", "Pa55word!", "Taylor", "Swift", "8126559090", address1, Role.CUSTOMER);
        user2 = new User(2,"tswift@gmail.com", "Pa55word!", "Taylor", "Swift", "8126559090", address2, Role.CUSTOMER);

    }

    /**
     * Test method for {@link com.mirs.model.domain.User#validate()}.
     */
    @Test
    public final void testValidateUser() {
        System.out.println("starting testValidateUser()");
        // user1.validate should assert to True since all variables
        // being passed to create a new User are all valid.
        assertTrue ("User validates", user1.validate());
        System.out.println("testValidateUser PASSED");
    }

    /**
     * Test method for {@link com.mirs.model.domain.User#equals(java.lang.Object)}.
     */
    @Test
    public final void testEqualsUser() {
        System.out.println("starting testEqualsUser()");
        // this should assert to true since the contents of
        // user1 and user2 class variables are identical.
        assertTrue ("user1 equals user2", user1.equals(user2));
        System.out.println("testEqualsUser PASSED");

    }
}