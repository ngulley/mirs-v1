package com.mirs;

import com.mirs.model.domain.Address;
import com.mirs.model.domain.Role;
import com.mirs.model.domain.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Musical Instrument Rental System version 1.0");

        Address address = new Address("100 W Circle Ave", "Apt 100", "Nashville", "TN", "43400");
        User user = new User("tswift@gmail.com", "Pa55word!", "Taylor", "Swift", "8126559090", address, Role.CUSTOMER);

        System.out.println(user.toString());
    }
}