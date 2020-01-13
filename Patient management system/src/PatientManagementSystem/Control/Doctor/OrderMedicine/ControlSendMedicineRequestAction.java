/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor.OrderMedicine;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewOrderMedicine;
import PatientManagementSystem.View.EventSystem.IObserver;

/**
 *
 * @author Shem
 */
public class ControlSendMedicineRequestAction implements IObserver {
    
    private final ModelMain modelMain;
    private final ViewOrderMedicine viewOrderMedicine;
    
    public ControlSendMedicineRequestAction(ViewOrderMedicine viewOrderMedicine, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewOrderMedicine = viewOrderMedicine;
        
        viewOrderMedicine.onSendNewMedicineRequest.addObserver(this);
    }
    
    @Override
    public void update() {        
        
        Account loggedInAccount = modelMain.getModelAccountSystem().getLoggedInAccount();
        String doctorId = loggedInAccount.getId();
        
        modelMain.getModelRequestSystem().requestNewMedicine(doctorId, viewOrderMedicine.getMedicineName());       
        
        viewOrderMedicine.showMessage("New medicine request sent", "Request for new medicine " + viewOrderMedicine.getMedicineName()
        + " has been sent and will be reviewed shortly.");
    }
}
