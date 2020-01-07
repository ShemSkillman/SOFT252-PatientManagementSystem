/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.Data.NotificationSystem.AccountNotifier;
import PatientManagementSystem.Model.Data.NotificationSystem.Notification;
import PatientManagementSystem.Model.ModelMain;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ModelNotificationSystem {
    
    private final ModelAccountSystem modelAccountSystem;

    public ModelNotificationSystem(ModelAccountSystem modelAccountSystem) {
        this.modelAccountSystem = modelAccountSystem;
    }    
    
    ArrayList<AccountNotifier> accountNotifiers = new ArrayList<AccountNotifier>();
    
    public void addNotificationForAccount(Account forAccount, String message)
    {
        AccountNotifier notifier = lookForAccountNotifier(forAccount);
        
        Account loggedInAccount = modelAccountSystem.getLoggedInAccount();
        Notification notification = new Notification(message, loggedInAccount);
        
        if (notifier == null) notifier = new AccountNotifier(forAccount, notification);
        else
        {
            notifier.addNotification(notification);
        }        
    }
    
    private AccountNotifier lookForAccountNotifier(Account accountToLookFor)
    {
        for (AccountNotifier notifier : accountNotifiers)
        {
            if (notifier.getAccount() == accountToLookFor)
            {
                return notifier;
            }
        }
        
        return null;
    }
    
    public ArrayList<Notification> getNotificationsForAccount(Account account) {
        
        AccountNotifier notifier = lookForAccountNotifier(account);
        
        if (notifier == null) return new ArrayList<Notification>();
        
        return notifier.getNotifications();
    }
}


