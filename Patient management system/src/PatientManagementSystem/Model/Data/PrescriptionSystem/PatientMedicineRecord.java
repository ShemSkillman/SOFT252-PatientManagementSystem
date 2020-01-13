/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PrescriptionSystem;

import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class PatientMedicineRecord {    
    
    private final String patientId;
    ArrayList<Prescription> prescriptions = new ArrayList();
    
    public PatientMedicineRecord(String patientId , Prescription prescription) {
        this.patientId = patientId;
        prescriptions.add(prescription);
    }
    
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    } 

    public String getPatientId() {
        return patientId;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }   
    
}
