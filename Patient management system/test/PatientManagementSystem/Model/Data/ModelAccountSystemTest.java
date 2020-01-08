/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.Administrator;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Secretary;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shem
 */
public class ModelAccountSystemTest {
    
    public ModelAccountSystemTest() {
    }
    
    ModelAccountSystem modelAccountSystem;
    ArrayList<Account> accounts;
    
    @BeforeEach
    public void setUp() {
        
        modelAccountSystem = new ModelAccountSystem(new ModelAccountHistoryTracker());
        accounts = new ArrayList<Account>();
        
        Secretary secretary = new Secretary("", "", "");
        Account account1 = new Account(secretary, "", "");
        
        Administrator admin = new Administrator("", "", "");
        Account account2 = new Account(admin, "", "");
        
        Doctor doctor = new Doctor("", "", "");
        Account account3 = new Account(doctor, "", "");
        
        Doctor doctor2 = new Doctor("", "", "");
        Account account4 = new Account(doctor2, "", "");
        
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
    }    
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testGetAccountsOfTypeRoleTest() {
        
        System.out.println("Testing creating account");
        
        Secretary secretary = new Secretary("Maggy", "Wood", "Magwood Drive");
        
        Account newAccount = modelAccountSystem.CreateAccount(secretary, "12341");
        
        assertEquals("Maggy", newAccount.getUser().getName());
        assertEquals("Wood", newAccount.getUser().getSurname());
        assertEquals("Magwood Drive", newAccount.getUser().getAddress());
        assertEquals("12341", newAccount.getPassword());
    }

    @Test
    public void testCreateAccountTest() {
        fail();
    }

    @Test
    public void testRemoveAccountTest() {
        fail();
    }

    @Test
    public void testLogInTest() {
        fail();
    }

    @Test
    public void testLogOutTest() {
        fail();
    }
    
}
