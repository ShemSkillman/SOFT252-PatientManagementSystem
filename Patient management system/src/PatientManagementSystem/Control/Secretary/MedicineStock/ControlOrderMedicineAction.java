/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary.MedicineStock;

import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.View.Secretary.ViewMedicineStock;

/**
 *
 * @author Shem
 */
public class ControlOrderMedicineAction implements IObserver {
    
    private ModelMain modelMain;
    ViewMedicineStock viewMedicineStock;
    
    public ControlOrderMedicineAction(ModelMain modelMain, ViewMedicineStock viewMedicineStock) {
        this.modelMain = modelMain;
        this.viewMedicineStock = viewMedicineStock;
        
        viewMedicineStock.onOrderMedicine.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {        
        String medicine = viewMedicineStock.getSelectedMedicineName();
        int quantity = viewMedicineStock.getOrderQuantity();
        
        modelMain.getModelPrescriptionSystem().restockMedicine(medicine, quantity);
        
        viewMedicineStock.showMessage("Medicine ordered", "Medicine " + medicine + " has been restocked");
    }
}
