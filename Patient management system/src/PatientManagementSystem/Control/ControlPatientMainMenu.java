/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control;

import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Patient.ViewPatientMainMenu;

/**
 *
 * @author Shem
 */
public class ControlPatientMainMenu {
    
    private final ViewPatientMainMenu viewPatientMainMenu;
    private final ModelMain modelMain;
    

    public ControlPatientMainMenu(ModelMain modelMain) {
        
        viewPatientMainMenu = new ViewPatientMainMenu();
        this.modelMain = modelMain;
        
        viewPatientMainMenu.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewPatientMainMenu.setVisible(isVisible);
    }
}
