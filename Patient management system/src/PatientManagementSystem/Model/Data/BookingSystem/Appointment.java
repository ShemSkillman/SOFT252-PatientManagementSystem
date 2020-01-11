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
    
        private String patientId;
        private String doctorId;
        private String scheduledDateAndTime;
        private String doctorNotes;
        
        public Appointment(String patientId, String doctorId, String dateAndTime, String doctorNotes)
        {
            this.patientId = patientId;
            this.doctorId = doctorId;
            scheduledDateAndTime = dateAndTime;
            this.doctorNotes = doctorNotes;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public String getScheduledDateAndTime() {            
            return scheduledDateAndTime;
        }

        public String getDoctorNotes() {
            return doctorNotes;
        }       

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }
        
        
        
}
