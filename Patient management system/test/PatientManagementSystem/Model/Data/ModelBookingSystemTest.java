/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.ModelMain;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Shem
 */
public class ModelBookingSystemTest {
    
    ModelMain modelMain;
    ModelBookingSystem bookingSystem;
    
    public ModelBookingSystemTest() {
    }
    
    @BeforeEach
    public void setUp() {
        modelMain = new ModelMain();
        ModelAccountHistoryTracker accountHistoryTracker = new ModelAccountHistoryTracker();
        ModelAccountSystem accountSystem = new ModelAccountSystem(accountHistoryTracker);
        accountHistoryTracker.setModelAccountSystem(accountSystem);
        
        bookingSystem = new ModelBookingSystem(accountHistoryTracker, accountSystem);
        
        bookingSystem.bookAppointment("P0054", "D0592", "13/06/2019");
        bookingSystem.bookAppointment("P0009", "D0125", "06/09/2019");
        bookingSystem.bookAppointment("P0432", "D0910", "13/02/2020");
        
    }

    @Test
    public void testBookAppointment() {
        
        System.out.println("Testing booking appointments");
        
        ArrayList<Appointment> appointments = bookingSystem.getAllAppointments();
        
        Appointment appointment1 = appointments.get(0);
        Appointment appointment2 = appointments.get(1);
        Appointment appointment3 = appointments.get(2);
        
        assertEquals(appointment1.getPatientId(), "P0054");
        assertEquals(appointment1.getDoctorId(), "D0592");
        assertEquals(appointment1.getScheduledDateAndTime(), "13/06/2019");
        
        assertEquals(appointment2.getPatientId(), "P0009");
        assertEquals(appointment2.getDoctorId(), "D0125");
        assertEquals(appointment2.getScheduledDateAndTime(), "06/09/2019");
        
        assertEquals(appointment3.getPatientId(), "P0432");
        assertEquals(appointment3.getDoctorId(), "D0910");
        assertEquals(appointment3.getScheduledDateAndTime(), "13/02/2020");
        
    }
    
}
