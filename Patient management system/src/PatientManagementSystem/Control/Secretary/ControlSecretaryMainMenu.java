/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Control.Secretary;

import PatientManagementSystem.Control.IObserver;
import PatientManagementSystem.Control.Secretary.SecretaryMainMenu.*;
import PatientManagementSystem.Model.ICommand;
import PatientManagementSystem.Model.ModelMain;
import PatientManagementSystem.View.Secretary.ViewSecretaryMainMenu;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ControlSecretaryMainMenu implements IObserver {
    
    ModelMain modelMain;
    
    // Stores reference to window to hide/unhide
    private final ViewSecretaryMainMenu viewSecretaryMainMenu;  
    
    private final ControlApproveRequestAction controlApproveRequestAction;
    private final ControlRejectRequestAction controlRejectRequestAction;
    
    ArrayList<ICommand> requests;
    
    // Creates window and hooks up control classes to main model to send requests
    public ControlSecretaryMainMenu(ModelMain modelMain){
        viewSecretaryMainMenu = new ViewSecretaryMainMenu();      
        controlApproveRequestAction = new ControlApproveRequestAction(modelMain, viewSecretaryMainMenu);
        controlRejectRequestAction = new ControlRejectRequestAction(modelMain, viewSecretaryMainMenu);
        viewSecretaryMainMenu.onSelectIndex.addObserver(this);     
        modelMain.getModelPatientRequestSystem().onUpdateRequests.addObserver(this);
        
        this.modelMain = modelMain;
        updateRequests();
        
        viewSecretaryMainMenu.setVisible(true);
    }
    
    public void setVisible(boolean isVisible){
        viewSecretaryMainMenu.setVisible(isVisible);
    }
    
    @Override
    public void update() {
        updateRequests();      
    }
    
    private void updateRequests() {
        int selectedIndex = viewSecretaryMainMenu.getSelectedRequestId();
        
        requests = modelMain.getModelPatientRequestSystem().getAllRequests();
        
        if (requests.size() < 1)
        {
            viewSecretaryMainMenu.enableButtons(false);
        }
        else
        {
            viewSecretaryMainMenu.enableButtons(true);
        }
        
        ArrayList<String> requestDesc = new ArrayList<String>();
        
        for (int i = 0; i < requests.size(); i++)
        {
            ICommand request = requests.get(i);
            String shortDesc = request.getShortDescription();
            
            requestDesc.add(shortDesc);
        }
        
        viewSecretaryMainMenu.fillRequestList(requestDesc);          
        
        if (selectedIndex < 0 || selectedIndex >= requests.size()) return;
        
        ICommand selected = requests.get(selectedIndex);
        viewSecretaryMainMenu.setRequestDescription(selected.getDescription());
    }
}
