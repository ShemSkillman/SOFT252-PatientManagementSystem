/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary.SecretaryMainMenu;

import PatientManagementSystem.IObserver;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Secretary.ViewSecretaryMainMenu;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlApproveRequestAction implements IObserver {    

    private final ModelMain modelMain;
    private final ViewSecretaryMainMenu viewControlSecretaryMainMenu;
    
    public ControlApproveRequestAction(ModelMain modelMain, ViewSecretaryMainMenu viewControlSecretaryMainMenu){
        this.modelMain = modelMain;
        
        this.viewControlSecretaryMainMenu = viewControlSecretaryMainMenu;
        viewControlSecretaryMainMenu.onApproveRequest.addObserver(this);
    }
    
    @Override
    public void update() {
        
        ArrayList<ICommand> requests = modelMain.getModelPatientRequestSystem().getAllRequests();
        
        int selectedIndex = viewControlSecretaryMainMenu.getSelectedRequestId();
        
        if (selectedIndex < 0) return;
        
        ICommand requestToApprove = requests.get(selectedIndex);
        
        modelMain.getModelPatientRequestSystem().approveRequest(requestToApprove);      
        
        viewControlSecretaryMainMenu.clearDescriptionText();
    }
}
