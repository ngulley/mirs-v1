package com.mirs.model.services.user;

import com.mirs.model.domain.Address;
import com.mirs.model.domain.Role;
import com.mirs.model.domain.User;
import com.mirs.model.services.exception.UserException;

import java.util.*;

public class UserServiceImpl implements IUserService {
    /**
     * Temporary persistence mechanism
     */
    private Map<Integer, User> users = new HashMap<Integer, User>();

    {
        Address address1 = new Address("200 S Austin Blv", "Apt B", "Chicago", "IL", "60302");
        Address address2 = new Address("500 E Madison Ave", "Apt 1500", "Atlanta", "GA", "12400");
        Address address3 = new Address("100 W Circle Ave", "Apt 100", "New York", "NY", "12500");
        Address address4 = new Address("500 E Madison Ave", "Apt 1500", "New York", "NY", "12400");
        Address address5 = new Address("6300 W 18th St", "", "Chicago", "IL", "60345");
        User user1 = new User(1, "ngulley@gmail.com", "Pa55word!", "Nathan", "Gulley", "4562349000", address1, Role.EMPLOYEE);
        User user2 = new User(2, "rgranier@gmail.com", "Pa55word!", "Randall", "Granier", "3430234932", address2, Role.EMPLOYEE);
        User user3 = new User(3, "cparker@gmail.com", "Pa55word!", "Charlie", "Parker", "8126559090", address3, Role.CUSTOMER);
        User user4 = new User(4, "jcoltrane@yahoo.com", "Pa55word!", "John", "Coltrane", "5651945656", address4, Role.CUSTOMER);
        User user5 = new User(5, "mdavis@aol.com", "Pa55word!", "Mile", "Davis", "3129784545", address5, Role.CUSTOMER);


        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);
        users.put(user4.getId(), user4);
        users.put(user5.getId(), user5);

    }

    public List<User> list() throws UserException {
        boolean isListed = false;
        Collection<User> c = this.users.values();
        List <User> userList = new ArrayList<User>();

        userList.addAll(c);

        return userList;
    }


    public List<User> list(Role role) throws UserException {
        boolean isListed = false;
        Collection<User> c = this.users.values();
        List <User> userList = new ArrayList<User>();

        for (User u : c) {
            if (u.getRole() == role) {
                userList.add(u);
            }
        }

        return userList;
    }
}
