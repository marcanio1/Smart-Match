package com.smartMatch;

//import org.junit.jupiter.api.Test;
import com.smartMatch.user.User;
import com.smartMatch.user.Verify;
import org.springframework.boot.test.context.SpringBootTest;


import com.smartMatch.matches.Matches;
import com.smartMatch.matches.MatchController;
import com.smartMatch.matches.MatchRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Application Test for the Match class
 *
 *
 * @author Rishabh bansal
 */
public class TestingForMatchClass {
    @InjectMocks
    MatchController userController;

    @Autowired
    @Mock
    MatchRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllMatchesTest() {
        List<Matches> userList = new ArrayList<Matches>();

        Matches rishabh = new Matches("rbansal@iastate.edu", "Eric, Jayant, Suraj", 1);

        Matches Eric = new Matches("emarchino@iastate.edu", "Rishabh, Jayant, Suraj",2);

        Matches Jayant = new Matches("jayant@iastate.edu", "Eric, Rishabh, Suraj",3);

        Matches Suraj = new Matches("suraj@iastate.edu", "Eric, Jayant, Rishabh",4);


        addToUserList(userList, rishabh, Eric, Jayant, Suraj);

        saveAllTheUsers(rishabh, Eric, Jayant, Suraj);

        when(userRepository.findAll()).thenReturn(userList);

        List<Matches> theUsers = userRepository.findAll();

        assertEquals(4, theUsers.size());

        verify(userRepository, times(1)).findAll();
//		System.out.println(userRepository.findAll().contains(rishabh));
    }


    @Test
    public void getMatchesByEmailTest(){
        List<Matches> userList = new ArrayList<Matches>();
        Matches rishabh = new Matches("rbansal@iastate.edu", "Eric, Jayant", 2);
        userList.add(rishabh);
        when(userRepository.findAll()).thenReturn(userList);
        userController.addNewMatch(rishabh);
        //List<User> theUsers = userRepository.findAll();
        assertEquals("Eric, Jayant", userController.getMatchesByEmailIdS("rbansal@iastate.edu"));

    }

    /**
     * New For demo 3
     */
    @Test
    public void appendNewMatchTest(){
        List<Matches> userList = new ArrayList<Matches>();
        Matches rishabh = new Matches("rbansal@iastate.edu","Riya, geeta" ,30);
        Matches rohan = new Matches("rbansal@iastate.edu","Lisha" ,30);
        userList.add(rishabh);
        when(userRepository.findAll()).thenReturn(userList);
        userController.addNewMatch(rishabh);
        userController.appendNewMatch(rohan);
        //List<User> theUsers = userRepository.findAll();
        assertEquals("Riya, geeta,Lisha", userController.getMatchesByEmailIdS("rbansal@iastate.edu"));
    }

    /**
     * New For demo 3
     */
    @Test
    public void returnTheMatchesTest(){
        List<Matches> userList = new ArrayList<Matches>();
        Matches rishabh = new Matches("rbansal@iastate.edu","Riya, geeta" ,30);
        userList.add(rishabh);
        when(userRepository.findAll()).thenReturn(userList);
        userController.addNewMatch(rishabh);
        //List<User> theUsers = userRepository.findAll();
        assertEquals("Riya, geeta", userController.getMatchesByEmailIdS("rbansal@iastate.edu"));
    }
    private void saveAllTheUsers(Matches rishabh, Matches Eric, Matches Jayant, Matches Suraj) { // Do not need userController as a parameter because it is a global variable.
        userController.addNewMatch(rishabh);
        userController.addNewMatch(Eric);
        userController.addNewMatch(Jayant);
        userController.addNewMatch(Suraj);
    }

    private void addToUserList(List<Matches> userList, Matches rishabh, Matches Eric, Matches Jayant, Matches Suraj) {
        userList.add(rishabh);
        userList.add(Eric);
        userList.add(Jayant);
        userList.add(Suraj);
    }

}
