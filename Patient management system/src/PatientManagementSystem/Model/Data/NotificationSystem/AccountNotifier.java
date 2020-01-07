/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.NotificationSystem;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class AccountNotifier {
    
    private Account account;
    private ArrayList<Notification> notifications = new ArrayList<Notification>();

    public AccountNotifier(Account account, Notification notification) {
        this.account = account;
        notifications.add(notification);
    }
    
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public Account getAccount() {
        return account;
    }    
    
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }
}
