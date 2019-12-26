/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.Booking;

import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.Model.User.Doctor;
import java.util.Calendar;

/**
 *
 * @author Shem
 */
public class Appointment {
    
        private Patient patientToSee;
        private Doctor doctorRunningAppointment;
        private Calendar scheduledDateAndTime;
        
        public Appointment(Patient patientToSee, Doctor doctorRunningAppointment, int year, int month, int day, int hour, int minute)
        {
            this.patientToSee = patientToSee;
            this.doctorRunningAppointment = doctorRunningAppointment;
            scheduledDateAndTime = Calendar.getInstance();
            scheduledDateAndTime.set(year, month, day, hour, minute);
        }

        public Patient getPatientToSee() {
            return patientToSee;
        }

        public Doctor getDoctorRunningAppointment() {
            return doctorRunningAppointment;
        }

        public Calendar getScheduledDateAndTime() {
            return scheduledDateAndTime;
        }
}
