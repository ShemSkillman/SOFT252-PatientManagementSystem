/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.Model.User;

/**
 *
 * @author Shem
 */
public class Patient extends User {
    
    private int age;
    private Gender gender;
    
    public Patient(String name, String surname, String address, 
            int age, Gender gender)
    {
        super(name, surname, address, Role.Patient);
        this.age = age;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }   
}
