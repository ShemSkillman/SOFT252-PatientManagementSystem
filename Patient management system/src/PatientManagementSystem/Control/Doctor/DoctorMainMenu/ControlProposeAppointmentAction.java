/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Doctor.DoctorMainMenu;

import PatientManagementSystem.View.EventSystem.IObserver;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Doctor.ViewDoctorMainMenu;

/**
 *
 * @author Shem
 */
public class ControlProposeAppointmentAction implements IObserver   {
    
    private ModelMain modelMain;
    private ViewDoctorMainMenu viewDoctorMainMenu;
    
    public ControlProposeAppointmentAction(ViewDoctorMainMenu viewDoctorMainMenu, ModelMain modelMain) {
        
        this.modelMain = modelMain;
        this.viewDoctorMainMenu = viewDoctorMainMenu;
        
        viewDoctorMainMenu.onProposeAppointment.addObserver(this);
    }
    
    @Override
    public void update() {        
        modelMain.doctorProposeAppointment();
    }
}
