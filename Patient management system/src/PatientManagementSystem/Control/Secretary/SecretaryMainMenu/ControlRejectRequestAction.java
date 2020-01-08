/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary.SecretaryMainMenu;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Secretary.ViewSecretaryMainMenu;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlRejectRequestAction implements IObserver {
    
    private final ModelMain modelMain;
    private final ViewSecretaryMainMenu viewSecretaryMainMenu;
    
    public ControlRejectRequestAction(ModelMain modelMain, ViewSecretaryMainMenu viewSecretaryMainMenu){
        this.modelMain = modelMain;
        
        this.viewSecretaryMainMenu = viewSecretaryMainMenu;
        viewSecretaryMainMenu.onRejectRequest.addObserver(this);
    }
    
    @Override
    public void update() {
        
        ArrayList<ICommand> requests = modelMain.getModelPatientRequestSystem().getAllRequests();
        
        int selectedIndex = viewSecretaryMainMenu.getSelectedRequestId();
        
        if (selectedIndex < 0) return;
        
        ICommand requestToReject = requests.get(selectedIndex);
        
        modelMain.getModelPatientRequestSystem().rejectRequest(requestToReject);
        
        
        viewSecretaryMainMenu.clearDescriptionText();        
    }
}
