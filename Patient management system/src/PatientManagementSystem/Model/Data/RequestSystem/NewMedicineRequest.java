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
public class NewMedicineRequest implements ICommand {
    
    private final String doctorId;
    private final String medicineName;
    private final ModelPrescriptionSystem modelPrescriptionSystem;

    public NewMedicineRequest(String doctorId, String medicineName, ModelPrescriptionSystem modelPrescriptionSystem) {
        this.doctorId = doctorId;
        this.medicineName = medicineName;
        this.modelPrescriptionSystem = modelPrescriptionSystem;
    }
    
    @Override
    public String getSenderId() {
        return doctorId;
    }
    
    @Override
    public String getShortDescription() {
        return "Doctor request for new medicine";
    }
    
    @Override
    public String getDescription() {
        return "Doctor request for new medicine\nDoctor ID: " + doctorId + 
                "\nNew medicine: " + medicineName;
    }

    public String getMedicineName() {
        return medicineName;
    }
    
    @Override 
    public void execute() {
        modelPrescriptionSystem.createNewMedicine(medicineName);
    }
}
