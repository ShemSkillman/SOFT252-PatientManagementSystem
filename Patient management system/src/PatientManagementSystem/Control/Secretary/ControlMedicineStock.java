/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary;

import PatientManagementSystem.Control.Secretary.MedicineStock.ControlOrderMedicineAction;
import PatientManagementSystem.Model.Data.PrescriptionSystem.Medicine;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.View.Secretary.ViewMedicineStock;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlMedicineStock implements IObserver {
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewMedicineStock viewMedicineStock;  
    private final ControlOrderMedicineAction controlOrderMedicineAction;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlMedicineStock(ModelMain modelMain) {
        
        this.modelMain = modelMain;
        viewMedicineStock = new ViewMedicineStock();  
        controlOrderMedicineAction = new ControlOrderMedicineAction(modelMain, viewMedicineStock);
        modelMain.getModelPrescriptionSystem().onUpdateMedicineStock.addObserver(this);
        
        refresh();
        
        viewMedicineStock.setVisible(true);
    }
    
    private void refresh() {
        
        ArrayList<Medicine> medicines = modelMain.getModelPrescriptionSystem().getMedecineStock();
        
        viewMedicineStock.fillMedicineStockTable(medicines);
    }
    
    @Override
    public void update() {
        refresh();
    }
    
    public void setVisible(boolean isVisible){
        
        if (isVisible) refresh();
        
        viewMedicineStock.setVisible(isVisible);
    }
}
