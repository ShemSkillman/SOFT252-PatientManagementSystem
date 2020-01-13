/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.Data.PrescriptionSystem;

import java.util.Calendar;

/**
 *
 * @author Shem
 */
public class Prescription {
    
    private Medicine medicine;
    private String dosage;
    private String dateGiven;

    public Prescription(String medicineName, int quantity, String dosage) {
        
        this.medicine = new Medicine(medicineName, quantity);
        this.dosage = dosage;
        
        Calendar timeStamp = Calendar.getInstance();
        int year = timeStamp.get(Calendar.YEAR);
        int month =  timeStamp.get(Calendar.MONTH) + 1;
        int day = timeStamp.get(Calendar.DAY_OF_MONTH);
        int hour = timeStamp.get(Calendar.HOUR_OF_DAY);
        int minute = timeStamp.get(Calendar.MINUTE);

        dateGiven = day + "/" + (month + 1) + "/" + year + " " + 
                hour + ":" + minute;
    }
    
    public Prescription(String medicineName, int quantity, String dosage, String dateGiven) {
        
        this.medicine = new Medicine(medicineName, quantity);
        this.dosage = dosage;
        this.dateGiven = dateGiven;
    }

    public String getMedicineName() {
        return medicine.getName();
    }
    
    public int getMedicineQuantity() {
        return medicine.getQuantity();
    }

    public String getDosage() {
        return dosage;
    }
    
    public String getDateGiven() {
        return dateGiven;
    }
}
