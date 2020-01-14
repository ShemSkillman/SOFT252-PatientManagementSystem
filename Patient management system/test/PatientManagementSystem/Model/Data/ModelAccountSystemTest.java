/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.Administrator;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Gender;
import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.Model.User.Role;
import PatientManagementSystem.Model.User.Secretary;
import java.util.ArrayList;
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
    
    @BeforeEach
    public void setUp() {        
        modelAccountSystem = new ModelAccountSystem(new ModelAccountHistoryTracker());        
    }    
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @Test
    public void testGetAccountsOfTypeRoleTest() {
        
        System.out.println("Testing getting accounts of type role");
        
        ArrayList<Account> doctorAccounts = modelAccountSystem.getAccountsOfTypeRole(Role.Doctor);
        
        for (int i = 0; i < doctorAccounts.size(); i++) {
            
            Account doctorAccount = doctorAccounts.get(i);            
            assertEquals(Role.Doctor, doctorAccount.getUser().getRole());
        }
        
    }

    @Test
    public void testCreateAccountTest() {
        System.out.println("Testing creating account");
        
        Secretary secretary = new Secretary("Maggy", "Wood", "Magwood Drive");
        
        Account newAccount = modelAccountSystem.CreateAccount(secretary, "12341");
        
        assertEquals("Maggy", newAccount.getUser().getName());
        assertEquals("Wood", newAccount.getUser().getSurname());
        assertEquals("Magwood Drive", newAccount.getUser().getAddress());
        assertEquals("12341", newAccount.getPassword());
    }

    @Test
    public void testRemoveAccountTest() {
                
        System.out.println("Testing removing accounts");
        
        Secretary secretary = new Secretary("", "", "");        
        Administrator admin = new Administrator("", "", "");        
        Doctor doctor = new Doctor("", "", "");
        
        Account accountOne = modelAccountSystem.CreateAccount(secretary, "123");
        Account accountTwo = modelAccountSystem.CreateAccount(admin, "123");
        Account accountThree = modelAccountSystem.CreateAccount(doctor, "123");
        
        String accountOneId = accountOne.getId();
        String accountTwoId = accountTwo.getId();
        String accountThreeId = accountThree.getId();
                
        modelAccountSystem.RemoveAccount(accountTwo);
        
        assertNull(modelAccountSystem.getAccount(accountTwoId));
        assertNotNull(modelAccountSystem.getAccount(accountOneId));
        assertNotNull(modelAccountSystem.getAccount(accountThreeId));
        
        modelAccountSystem.RemoveAccount(accountThree);
        
        assertNull(modelAccountSystem.getAccount(accountThreeId));
        assertNotNull(modelAccountSystem.getAccount(accountOneId));
        
        modelAccountSystem.RemoveAccount(accountOne);
        
        assertNull(modelAccountSystem.getAccount(accountOneId));
    }

    @Test
    public void testLogInTest() {
        
        System.out.println("Testing logging in to account");
        
        modelAccountSystem.logOut();
        
        Patient patient = new Patient("Shem", "Skillman", "14 Forestreet", 20, Gender.Male);
        Account patientAccount = modelAccountSystem.CreateAccount(patient, "123");
        
        modelAccountSystem.logIn(patientAccount.getId(), "123");
        
        Account loggedInAccount = modelAccountSystem.getLoggedInAccount();
        
        assertEquals(loggedInAccount.getId(), patientAccount.getId());
    }

    @Test
    public void testLogOutTest() {
        
        System.out.println("Testing logging out of account");
        
        modelAccountSystem.logOut();
        
        Account loggedInAccount = modelAccountSystem.getLoggedInAccount();
        
        assertNull(loggedInAccount);
    }
    
}
