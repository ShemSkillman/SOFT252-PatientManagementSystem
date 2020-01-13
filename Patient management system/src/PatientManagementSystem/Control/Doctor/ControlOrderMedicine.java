/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor;

import PatientManagementSystem.Control.Doctor.OrderMedicine.ControlSendMedicineRequestAction;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewOrderMedicine;

/**
 *
 * @author Shem
 */
public class ControlOrderMedicine {
    
    private final ViewOrderMedicine viewOrderMedicine;
    
    private final ControlSendMedicineRequestAction controlSendMedicineRequestAction;
    
    public ControlOrderMedicine(ModelMain modelMain){        
        
        viewOrderMedicine = new ViewOrderMedicine();
        controlSendMedicineRequestAction = new ControlSendMedicineRequestAction(viewOrderMedicine, modelMain);
        
        viewOrderMedicine.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewOrderMedicine.setVisible(isVisible);
    }
}
