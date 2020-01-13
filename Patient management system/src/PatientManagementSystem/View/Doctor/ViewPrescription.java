/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.View.Doctor;

import PatientManagementSystem.Model.Data.PrescriptionSystem.Prescription;
import PatientManagementSystem.View.EventSystem.Event;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shem
 */
public class ViewPrescription extends javax.swing.JFrame {

    public Event onOrderNewMedicine, onRequestMedicine, onSelectPatient;
    
    /**
     * Creates new form ViewPrescription
     */
    public ViewPrescription() {
        initComponents();
        
        onOrderNewMedicine = new Event();
        onRequestMedicine = new Event();
        onSelectPatient = new Event();
    }
    
    public void setPatients(ArrayList<String> patientNames)
    {
        for (var patientName : patientNames)
        {
            cboxPatients.addItem(patientName);
        }
    }
    
    public String getSelectedPatientId() {
        
        String patientName = (String)cboxPatients.getSelectedItem();
        
        return patientName.substring(0, 5);
    }
    
    public String getSelectedPrescriptionMedicine() {
        
        return (String)cboxMedicines.getSelectedItem();
    }
    
    public void setMedicines(ArrayList<String> medicines) {
        
        cboxMedicines.removeAllItems();
        
        for (var medicine : medicines)
        {
            cboxMedicines.addItem(medicine);
        }
    }
    
    public int getPrescriptionMedicineQuantity() {
        
        return (int)spinMedicineQuantity.getValue();
    }
    
    public String getPrescriptionDosage() {
        
        return txtDosage.getText();
    } 
    
    public void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void fillPrescriptionsTable(ArrayList<Prescription> prescriptions)
    {
        DefaultTableModel model = (DefaultTableModel)tblPrescriptions.getModel();
        model.setRowCount(prescriptions.size());
        tblPrescriptions.setModel(model);
        
        for (int i = 0; i < prescriptions.size(); i++)
        {
            Prescription prescription = prescriptions.get(i);
            
            
            tblPrescriptions.getModel().setValueAt(prescription.getDateGiven(), i, 0);
            tblPrescriptions.getModel().setValueAt(prescription.getMedicineName(), i, 1);
            tblPrescriptions.getModel().setValueAt(prescription.getMedicineQuantity(), i, 2);
            tblPrescriptions.getModel().setValueAt(prescription.getDosage(), i, 3);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPatient = new javax.swing.JLabel();
        cboxPatients = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrescriptions = new javax.swing.JTable();
        lblPatientPrescriptions = new javax.swing.JLabel();
        lblNewPrescription = new javax.swing.JLabel();
        lblMedicine = new javax.swing.JLabel();
        cboxMedicines = new javax.swing.JComboBox<>();
        lblQuantity = new javax.swing.JLabel();
        spinMedicineQuantity = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDosage = new javax.swing.JTextArea();
        lblDosage = new javax.swing.JLabel();
        btnRequestPrescription = new javax.swing.JButton();
        btnOrderNewMedicine = new javax.swing.JButton();
        lblNote = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prescriptions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        lblPatient.setText("Patient");

        cboxPatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPatientsActionPerformed(evt);
            }
        });

        tblPrescriptions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date and Time", "Medicine", "Quantity", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrescriptions.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane1.setViewportView(tblPrescriptions);

        lblPatientPrescriptions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientPrescriptions.setText("Patient prescriptions");

        lblNewPrescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNewPrescription.setText("New prescription");

        lblMedicine.setText("Medicine");

        cboxMedicines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxMedicinesActionPerformed(evt);
            }
        });

        lblQuantity.setText("Quantity");

        txtDosage.setColumns(20);
        txtDosage.setRows(5);
        jScrollPane2.setViewportView(txtDosage);

        lblDosage.setText("Dosage");

        btnRequestPrescription.setText("Request prescription");
        btnRequestPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestPrescriptionActionPerformed(evt);
            }
        });

        btnOrderNewMedicine.setText("Order new medicine");
        btnOrderNewMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderNewMedicineActionPerformed(evt);
            }
        });

        lblNote.setText("*prescription must be reviewed and accepted by a secretary to make sure medicine is in stock");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPatient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboxPatients, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMedicine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboxMedicines, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOrderNewMedicine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRequestPrescription))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDosage)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNote)
                                .addGap(0, 37, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPatientPrescriptions)
                            .addComponent(lblNewPrescription)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblQuantity)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinMedicineQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatient)
                    .addComponent(cboxPatients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPatientPrescriptions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNewPrescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicine)
                    .addComponent(cboxMedicines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(spinMedicineQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDosage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNote)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRequestPrescription)
                    .addComponent(btnOrderNewMedicine))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrderNewMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderNewMedicineActionPerformed
        onOrderNewMedicine.invoke();
    }//GEN-LAST:event_btnOrderNewMedicineActionPerformed

    private void btnRequestPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestPrescriptionActionPerformed
        onRequestMedicine.invoke();
    }//GEN-LAST:event_btnRequestPrescriptionActionPerformed

    private void cboxPatientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPatientsActionPerformed
        onSelectPatient.invoke();
    }//GEN-LAST:event_cboxPatientsActionPerformed

    private void cboxMedicinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxMedicinesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxMedicinesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPrescription().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOrderNewMedicine;
    private javax.swing.JButton btnRequestPrescription;
    private javax.swing.JComboBox<String> cboxMedicines;
    private javax.swing.JComboBox<String> cboxPatients;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDosage;
    private javax.swing.JLabel lblMedicine;
    private javax.swing.JLabel lblNewPrescription;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblPatientPrescriptions;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JSpinner spinMedicineQuantity;
    private javax.swing.JTable tblPrescriptions;
    private javax.swing.JTextArea txtDosage;
    // End of variables declaration//GEN-END:variables
}
