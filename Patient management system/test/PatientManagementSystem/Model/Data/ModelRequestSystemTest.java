/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.RequestSystem.AccountRequest;
import PatientManagementSystem.Model.Data.RequestSystem.AppointmentRequest;
import PatientManagementSystem.Model.Data.RequestSystem.NewMedicineRequest;
import PatientManagementSystem.Model.Data.RequestSystem.PrescriptionRequest;
import PatientManagementSystem.Model.Data.RequestSystem.TerminationRequest;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.Model.User.Gender;
import PatientManagementSystem.Model.User.Patient;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Shem
 */
public class ModelRequestSystemTest {
    
    ModelRequestSystem modelRequestSystem;
    
    
    ModelMain modelMain;
    
    
    public ModelRequestSystemTest() {
        
    }
    
    @BeforeEach
    public void setUp() {
        modelMain = new ModelMain();
    }

    @Test
    public void testRequestAccount() {
        
        System.out.println("Testing request account");
        
        Patient patient = new Patient("Shem", "Skillman", "14 Forestreet, Kingsbridge", 20, Gender.Male);
        
        modelMain.getModelRequestSystem().requestAccount(patient, "123");
        
        ArrayList<ICommand> requests = modelMain.getModelRequestSystem().getAllRequests();
        
        AccountRequest accountRequest = (AccountRequest)requests.get(0);
        
        assertEquals(accountRequest.getPassword(), "123");
        assertEquals(accountRequest.getPatientDetails().getName(), "Shem");
        assertEquals(accountRequest.getPatientDetails().getSurname(), "Skillman");
        assertEquals(accountRequest.getPatientDetails().getAddress(), "14 Forestreet, Kingsbridge");
        assertEquals(accountRequest.getPatientDetails().getAge(), 20);
        assertEquals(accountRequest.getPatientDetails().getGender(), Gender.Male);
    }

    @Test
    public void testRequestAppointmentWithDoctor() {
        
        System.out.println("Testing request appointment");
        
        Patient patient = new Patient("", "", "", 0, Gender.Male);
        Doctor doctor = new Doctor("", "", "");
        
        Account patientAccount = modelMain.getModelAccountSystem().CreateAccount(patient, "123");
        Account doctorAccount = modelMain.getModelAccountSystem().CreateAccount(doctor, "123");
        
        modelMain.getModelRequestSystem().requestAppointmentWithDoctor(patientAccount, "13/09/2019", doctorAccount);
        
        ArrayList<ICommand> requests = modelMain.getModelRequestSystem().getAllRequests();
        
        AppointmentRequest appointmentRequest = (AppointmentRequest)requests.get(0);
        
        assertEquals(appointmentRequest.getDateAndTime(), "13/09/2019");
        assertEquals(appointmentRequest.getDoctorId(), doctorAccount.getId());
        assertEquals(appointmentRequest.getPatientId(), patientAccount.getId());
    }

    @Test
    public void testRequestAccountTermination() {
        
        System.out.println("Testing request account termination");
        
        Patient patient = new Patient("", "", "", 0, Gender.Male);
        
        Account patientAccount = modelMain.getModelAccountSystem().CreateAccount(patient, "123");
        
        modelMain.getModelRequestSystem().requestAccountTermination(patientAccount, "dunno");
        
        ArrayList<ICommand> requests = modelMain.getModelRequestSystem().getAllRequests();
        
        TerminationRequest terminationRequest = (TerminationRequest)requests.get(0);
        
        assertEquals(terminationRequest.getPatientId(), patientAccount.getId());
        assertEquals(terminationRequest.getReason(), "dunno");
    }

    @Test
    public void testRequestNewMedicine() {
        
        System.out.println("Testing request new medicine");
        
        modelMain.getModelRequestSystem().requestNewMedicine("D0001", "calpol");
        
        ArrayList<ICommand> requests = modelMain.getModelRequestSystem().getAllRequests();
        
        NewMedicineRequest newMedicineRequest = (NewMedicineRequest)requests.get(0);
        
        assertEquals(newMedicineRequest.getSenderId(), "D0001");
        assertEquals(newMedicineRequest.getMedicineName(), "calpol");
    }

    @Test
    public void testRequestPatientPrescription() {
        
        System.out.println("Testing request patient prescription");
        
        modelMain.getModelRequestSystem().requestPatientPrescription("aspirin", 5, "1 pill an hour", "P0001", "D0405");
        
        ArrayList<ICommand> requests = modelMain.getModelRequestSystem().getAllRequests();
        
        PrescriptionRequest prescriptionRequest = (PrescriptionRequest)requests.get(0);
        
        assertEquals(prescriptionRequest.getDoctorId(), "D0405");
        assertEquals(prescriptionRequest.getPatientId(), "P0001");
        assertEquals(prescriptionRequest.getDosage(), "1 pill an hour");
        assertEquals(prescriptionRequest.getMedicineName(), "aspirin");
        assertEquals(prescriptionRequest.getQuantity(), 5);        
    }
    
}
