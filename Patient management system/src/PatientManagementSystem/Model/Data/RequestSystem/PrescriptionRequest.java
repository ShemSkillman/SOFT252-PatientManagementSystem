/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.RequestSystem;

import PatientManagementSystem.Model.Data.ModelPrescriptionSystem;
import PatientManagementSystem.Model.ICommand;

/**
 *
 * @author Shem
 */
public class PrescriptionRequest implements ICommand {
    
    private final String medicineName;
    private final int quantity;
    private final String dosage;
    private final String doctorId;
    private final String patientId;
    private final ModelPrescriptionSystem modelPrescriptionSystem;

    public PrescriptionRequest(String medicineName, int quantity, String dosage, 
            String doctorId, String patientId, 
            ModelPrescriptionSystem modelPrescriptionSystem) {
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dosage = dosage;
        this.modelPrescriptionSystem = modelPrescriptionSystem;
    }
    
    @Override 
    public String getSenderId() {
        return doctorId;
    }
    
    @Override
    public String getShortDescription() {
        return "Doctor requesting prescription for patient";
    }
    
    @Override
    public String getDescription() {
        return "Doctor requesting prescription for patient\nDoctor ID: " + doctorId + "\nPatient ID: " + patientId + 
                "\nMedicine " + medicineName + "\nQuantity: " + quantity + "\nDosage: " + dosage;
    }
    
    @Override
    public void execute() {
        modelPrescriptionSystem.givePrescription(patientId, medicineName, quantity, dosage);
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }
    
    
}
