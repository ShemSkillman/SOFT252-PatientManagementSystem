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
    
    private final String dateAndTime;
    private final String actionDescription;

    public PerformedAction(String actionDescription) {
        
        Calendar timeStamp = Calendar.getInstance();
        int year = timeStamp.get(Calendar.YEAR);
        int month =  timeStamp.get(Calendar.MONTH) + 1;
        int day = timeStamp.get(Calendar.DAY_OF_MONTH);
        int hour = timeStamp.get(Calendar.HOUR_OF_DAY);
        int minute = timeStamp.get(Calendar.MINUTE);

        dateAndTime = day + "/" + (month + 1) + "/" + year + " " + 
                hour + ":" + minute;
        
        this.actionDescription = actionDescription;
    }
    
    public PerformedAction(String actionDescription, String dateAndTime) {
        
        this.dateAndTime = dateAndTime;
        this.actionDescription = actionDescription;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getActionDescription() {
        return actionDescription;
    }
    
    
}
