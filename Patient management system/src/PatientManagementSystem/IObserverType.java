/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem;

/**
 *
 * @author Shem
 */
public interface IObserverType<T> {
    public abstract void update(T t);
}
