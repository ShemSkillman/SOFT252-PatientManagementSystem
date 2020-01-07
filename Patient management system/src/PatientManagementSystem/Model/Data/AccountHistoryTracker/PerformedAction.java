/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.AccountHistoryTracker;

import java.util.Calendar;

/**
 *
 * @author Shem
 */
public class PerformedAction {
    
    private final Calendar timeStamp;
    private final String actionDescription;

    public PerformedAction(String actionDescription) {
        timeStamp = Calendar.getInstance();
        this.actionDescription = actionDescription;
    }

    public Calendar getTimeStamp() {
        return timeStamp;
    }

    public String getActionDescription() {
        return actionDescription;
    }
    
    
}
