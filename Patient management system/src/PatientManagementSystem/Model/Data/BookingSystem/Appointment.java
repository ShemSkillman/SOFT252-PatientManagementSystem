/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.BookingSystem;

import PatientManagementSystem.Model.Data.AccountSystem.Account;
import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.Model.User.Doctor;
import java.util.Calendar;

/**
 *
 * @author Shem
 */
public class Appointment {
    
        private Account patientAccount;
        private Doctor doctorRunningAppointment;
        private String scheduledDateAndTime;
        
        public Appointment(Account patientAccount, Doctor doctorRunningAppointment, String dateAndTime)
        {
            this.patientAccount = patientAccount;
            this.doctorRunningAppointment = doctorRunningAppointment;
            scheduledDateAndTime = dateAndTime;
        }

        public Account getPatientAccount() {
            return patientAccount;
        }

        public Doctor getDoctorRunningAppointment() {
            return doctorRunningAppointment;
        }

        public String getScheduledDateAndTime() {            
            return scheduledDateAndTime;
        }
}
