/*
 * Copyright 2018, Team 26 CMPUT 301. University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under the terms and coditions fo the Code of Student Behaviour at the University of Alberta.
 */

package com.cmput301w18t26.taskit.ModelTests;

import android.test.ActivityInstrumentationTestCase2;

import com.cmput301w18t26.taskit.BidList;
import com.cmput301w18t26.taskit.TaskList;
import com.cmput301w18t26.taskit.User;
import com.cmput301w18t26.taskit.UserActivity;

import java.util.Date;

/**
 * Created by kevingordon on 2018-02-26.
 */

public class UserTest extends ActivityInstrumentationTestCase2 {
    public UserTest() {
        super(UserActivity.class);
    }

    // test setting and getting the name of the User (string)
    public void testSetGetName() {
        User user = new User();
        String name = "AliceBob";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    // test the setting and getting of the User email
    public void testSetGetEmail() {
        User user = new User();
        String email = "AliceBob@charlie.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    //test the setting and getting of the phone number field (int)
    public void testSetGetPhone() {
        User user = new User();
        int phone = 1234567890;
        user.setPhone(phone);
        assertEquals(phone, user.getPhone());
    }

    // test the setting and getting of user's username (string)
    public void testSetGetUserName() {
        User user = new User();
        String username = "AliceBob";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    // test setting and getting the user's rank
    public void testSetGetRanks() {
        User user = new User();
        int[] ranks = {1,2,3,4,5};
        user.setRanks(ranks);
        assertEquals(ranks, user.getRanks());
    }

    // a more complete testing of getRanks method
    public void testGetRank() {
        User user = new User();
        int[] ranks1 = {1,2,3,4,5};
        user.setRanks(ranks1);
        assertEquals(3.0, user.getRank());
        int[] ranks2 = {1};
        user.setRanks(ranks2);
        assertEquals(1.0, user.getRank());
        int[] ranks3 = {1,2};
        user.setRanks(ranks3);
        assertEquals(1.5, user.getRank());
        int[] ranks4 = {};
        user.setRanks(ranks4);
        assertEquals(-1.0, user.getRank());
    }

    // test setting and getting the date timestamp from User object
    public void testSetGetDate() {
        User user = new User();
        Date date = new Date();
        user.setDate(date);
        assertEquals(date, user.getDate());
    }
}
