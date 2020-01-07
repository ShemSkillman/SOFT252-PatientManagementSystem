/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Admin.AdminMainMenu;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Admin.ViewAdminMainMenu;

/**
 *
 * @author Shem
 */
public class ControlViewDoctorRatingsAction implements IObserver {
    
    private ModelMain modelMain;
    
    public ControlViewDoctorRatingsAction(ModelMain modelMain, ViewAdminMainMenu viewAdminMainMenu) {
        this.modelMain = modelMain;
        
        viewAdminMainMenu.onViewDoctorRatings.addObserver(this);
    }    
    
    // Gets inputs from text components on the form
    // Sends details to model to create the accout
    @Override
    public void update() {
        modelMain.adminViewDoctorRatings();
    }
}
