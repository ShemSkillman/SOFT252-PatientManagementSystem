/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.NotificationSystem;

import PatientManagementSystem.Model.Data.AccountSystem.Account;

/**
 *
 * @author Shem
 */
public class Notification {
    private final String message;
    private final Account fromAccount;

    public Notification(String message, Account fromAccount) {
        this.message = message;
        this.fromAccount = fromAccount;
    }

    public String getMessage() {
        return message;
    }

    public Account getFromAccount() {
        return fromAccount;
    }   
    
}
