/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountHistoryTracker.PerformedAction;
import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.Secretary;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Shem
 */
public class ModelAccountHistoryTrackerTest {
    
    ModelAccountHistoryTracker modelAccountHistoryTracker;
    String loggedInAccountId; 
    
    public ModelAccountHistoryTrackerTest() {
    }
    
    
    
    @BeforeEach
    public void setUp() {
        modelAccountHistoryTracker = new ModelAccountHistoryTracker();
        ModelAccountSystem accountSystem = new ModelAccountSystem(modelAccountHistoryTracker);
        modelAccountHistoryTracker.setModelAccountSystem(accountSystem);
        
        Secretary secretary = new Secretary("", "", "");
        Account account = accountSystem.CreateAccount(secretary, "123");
        loggedInAccountId = account.getId();
        
        accountSystem.logIn(account.getId(), account.getPassword());
    }
    
    @Test
    public void testRecordAction() {
        
        System.out.println("Testing record action");
        
        ArrayList<String> testText = new ArrayList();
        
        testText.add("TESTING TESTING TESTING");
        testText.add("TESTING TESTING");
        testText.add("tESTING??");
        
        for (int i = 0; i < testText.size(); i++) 
        {
            modelAccountHistoryTracker.recordAction(testText.get(i));
        }
        
        ArrayList<PerformedAction> actions = modelAccountHistoryTracker.getAccountHistory(loggedInAccountId);
        
        for (int i = 0; i < actions.size(); i++) 
        {
            String decription = actions.get(i).getActionDescription();
            
            assertEquals(testText.get(i), decription);
        }
    }

    @Test
    public void testRemoveHistoryForAccount() {
        
        ArrayList<String> testText = new ArrayList();
        
        testText.add("1");
        testText.add("2");
        testText.add("3");
        
        for (int i = 0; i < testText.size(); i++) 
        {
            modelAccountHistoryTracker.recordAction(testText.get(i));
        }
        
        modelAccountHistoryTracker.removeHistoryForAccount(loggedInAccountId);
        
        ArrayList<PerformedAction> actions = modelAccountHistoryTracker.getAccountHistory(loggedInAccountId);
        
        assertNull(actions);
    }

    
}
