/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.PrescriptionSystem.Medicine;
import PatientManagementSystem.Model.Data.PrescriptionSystem.Prescription;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Shem
 */
public class ModelPrescriptionSystemTest {
    
    ModelPrescriptionSystem modelPrescriptionSystem;
    
    public ModelPrescriptionSystemTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
        modelPrescriptionSystem = new ModelPrescriptionSystem();
    }

    @Test
    public void testGivePrescription() {
        
        System.out.println("Testing give prescription");
        
        modelPrescriptionSystem.givePrescription("P0291", "aspirin", 10, "One pill per day");
        modelPrescriptionSystem.givePrescription("P0001", "diazapam", 53, "3 pills per day");
        
        ArrayList<Prescription> prescriptions = modelPrescriptionSystem.getPrescriptionsForPatient("P0291");
        
        assertEquals(prescriptions.get(0).getDosage(), "One pill per day");
        assertEquals(prescriptions.get(0).getMedicineName(), "aspirin");
        assertEquals(prescriptions.get(0).getMedicineQuantity(), 10);
        
        prescriptions = modelPrescriptionSystem.getPrescriptionsForPatient("P0001");
        
        assertEquals(prescriptions.get(1).getDosage(), "3 pills per day");
        assertEquals(prescriptions.get(1).getMedicineName(), "diazapam");
        assertEquals(prescriptions.get(1).getMedicineQuantity(), 53);        
        
    }

    @Test
    public void testCreateNewMedicine() {
        
        System.out.println("Testing create new medicine");
        
        modelPrescriptionSystem.createNewMedicine("calpol");
        modelPrescriptionSystem.createNewMedicine("ibeprofen");
        modelPrescriptionSystem.createNewMedicine("strepsils");
        
        ArrayList<Medicine> medicines = modelPrescriptionSystem.getMedecineStock();
        
        assertEquals(medicines.get(0).getName(), "calpol");
        assertEquals(medicines.get(1).getName(), "ibeprofen");
        assertEquals(medicines.get(2).getName(), "strepsils");
    }

    @Test
    public void testRestockMedicine() {
        
        System.out.println("Testing restock medicine");
        
        modelPrescriptionSystem.createNewMedicine("calpol");
        modelPrescriptionSystem.createNewMedicine("ibeprofen");
        modelPrescriptionSystem.createNewMedicine("strepsils");
        
        modelPrescriptionSystem.restockMedicine("calpol", 50);
        modelPrescriptionSystem.restockMedicine("ibeprofen", 13);
        modelPrescriptionSystem.restockMedicine("calpol", 13);
        modelPrescriptionSystem.restockMedicine("strepsils", 197);
        modelPrescriptionSystem.restockMedicine("ibeprofen", 27);
        modelPrescriptionSystem.restockMedicine("ibeprofen", 10);
        modelPrescriptionSystem.restockMedicine("strepsils", 3);
        
        ArrayList<Medicine> medicines = modelPrescriptionSystem.getMedecineStock();
        
        assertEquals(medicines.get(0).getQuantity(), 63);
        assertEquals(medicines.get(1).getQuantity(), 50);
        assertEquals(medicines.get(2).getQuantity(), 200);
    }

    @Test
    public void testConsumeMedicine() {
        
        System.out.println("Testing consume medicine");
        
        modelPrescriptionSystem.createNewMedicine("calpol");
        modelPrescriptionSystem.createNewMedicine("ibeprofen");
        modelPrescriptionSystem.createNewMedicine("strepsils");
        
        modelPrescriptionSystem.restockMedicine("calpol", 50);
        modelPrescriptionSystem.restockMedicine("ibeprofen", 13);
        modelPrescriptionSystem.restockMedicine("strepsils", 197);
        
        modelPrescriptionSystem.consumeMedicine("calpol", 0);
        modelPrescriptionSystem.consumeMedicine("calpol", 1);
        modelPrescriptionSystem.consumeMedicine("calpol", 10);
        modelPrescriptionSystem.consumeMedicine("calpol", 3);
        modelPrescriptionSystem.consumeMedicine("ibeprofen", 13);
        modelPrescriptionSystem.consumeMedicine("strepsils", 196);
        
        ArrayList<Medicine> medicines = modelPrescriptionSystem.getMedecineStock();
        
        assertEquals(medicines.get(0).getQuantity(), 36);
        assertEquals(medicines.get(1).getQuantity(), 0);
        assertEquals(medicines.get(2).getQuantity(), 1);
    }
    
}
