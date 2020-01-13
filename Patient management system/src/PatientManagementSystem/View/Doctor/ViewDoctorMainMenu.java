/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.View.Doctor;

import PatientManagementSystem.Model.Data.BookingSystem.Appointment;
import PatientManagementSystem.Model.Data.ModelAccountSystem;
import PatientManagementSystem.Model.User.Patient;
import PatientManagementSystem.View.EventSystem.Event;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shem
 */
public class ViewDoctorMainMenu extends javax.swing.JFrame {

    public Event onClickAppointment, onSaveNotes, onLogOut, onGivePrescription,
            onInspectPatientHistory, onProposeAppointment;
    
    /**
     * Creates new form DoctorMainMenu
     */
    public ViewDoctorMainMenu() {
        initComponents();
        onClickAppointment = new Event();
        onSaveNotes = new Event();
        onLogOut = new Event();
        onGivePrescription = new Event();
        onInspectPatientHistory = new Event();
        onProposeAppointment = new Event();
    }
    
    public void fillAppointmentsTable(ArrayList<Appointment> appointments, ModelAccountSystem accountSystem) {
        
        DefaultTableModel model = (DefaultTableModel)tblAppointments.getModel();
        model.setRowCount(appointments.size());
        tblAppointments.setModel(model);
        
        for(int i = 0; i < appointments.size(); i++) 
        {
            Appointment appointment = appointments.get(i);
            String dateAndTime = appointment.getScheduledDateAndTime();
            
            String patientId = appointment.getPatientId();
            Patient patient = (Patient)accountSystem.getAccount(patientId).getUser();
            String patientName = patientId + " " + patient.getName() + " "
                    + patient.getSurname();
            
            tblAppointments.getModel().setValueAt(dateAndTime, i, 0);
            tblAppointments.getModel().setValueAt(patientName, i, 1);
        }
    }
    
    public void setSelectedAppointment(String selectedAppointment) {
        txtSelectedAppointment.setText(selectedAppointment);
    }
    
    public int getSelectedAppointmentIndex() {
        return tblAppointments.getSelectedRow();
    }
    
    public String getNotes() {
        return txtNotes.getText();
    }
    
    public void setNotes(String text) {
        txtNotes.setText(text);
    }
    
    public void setWelcomeMessage(String text) {
        lblWelcomeMessage.setText(text);
    }
    
    public void EnableButtons(boolean isEnabled) {
        btnGivePrescription.setEnabled(isEnabled);
        btnSaveNotes.setEnabled(isEnabled);
        btnInspectPatientHistory.setEnabled(isEnabled);
        btnProposeAppointment.setEnabled(isEnabled);
    }
    
    public void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        lblScheduledAppointments = new javax.swing.JLabel();
        lblAppointment = new javax.swing.JLabel();
        txtSelectedAppointment = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        lblNotes = new javax.swing.JLabel();
        btnSaveNotes = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnGivePrescription = new javax.swing.JButton();
        btnInspectPatientHistory = new javax.swing.JButton();
        btnProposeAppointment = new javax.swing.JButton();
        lblWelcomeMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Main Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Date and Time", "Patient"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAppointments);

        lblScheduledAppointments.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblScheduledAppointments.setText("Scheduled appointments");

        lblAppointment.setText("Appointment");

        txtSelectedAppointment.setEditable(false);

        txtNotes.setColumns(20);
        txtNotes.setLineWrap(true);
        txtNotes.setRows(5);
        jScrollPane2.setViewportView(txtNotes);

        lblNotes.setText("Notes");

        btnSaveNotes.setText("Save notes");
        btnSaveNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNotesActionPerformed(evt);
            }
        });

        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnGivePrescription.setText("Give prescription");
        btnGivePrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGivePrescriptionActionPerformed(evt);
            }
        });

        btnInspectPatientHistory.setText("Inspect patient history");
        btnInspectPatientHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInspectPatientHistoryActionPerformed(evt);
            }
        });

        btnProposeAppointment.setText("Propose appointment");
        btnProposeAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProposeAppointmentActionPerformed(evt);
            }
        });

        lblWelcomeMessage.setText("Hi <name>,");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogOut))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAppointment)
                            .addComponent(lblNotes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGivePrescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnInspectPatientHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnProposeAppointment))
                            .addComponent(txtSelectedAppointment)
                            .addComponent(jScrollPane2)
                            .addComponent(btnSaveNotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblScheduledAppointments)
                            .addComponent(lblWelcomeMessage))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcomeMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(lblScheduledAppointments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAppointment)
                    .addComponent(txtSelectedAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaveNotes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGivePrescription)
                    .addComponent(btnInspectPatientHistory)
                    .addComponent(btnProposeAppointment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogOut)
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

    private void tblAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppointmentsMouseClicked
        onClickAppointment.invoke();
    }//GEN-LAST:event_tblAppointmentsMouseClicked

    private void btnSaveNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNotesActionPerformed
        onSaveNotes.invoke();
    }//GEN-LAST:event_btnSaveNotesActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        onLogOut.invoke();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnGivePrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGivePrescriptionActionPerformed
        onGivePrescription.invoke();
    }//GEN-LAST:event_btnGivePrescriptionActionPerformed

    private void btnInspectPatientHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInspectPatientHistoryActionPerformed
        onInspectPatientHistory.invoke();
    }//GEN-LAST:event_btnInspectPatientHistoryActionPerformed

    private void btnProposeAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProposeAppointmentActionPerformed
        onProposeAppointment.invoke();
    }//GEN-LAST:event_btnProposeAppointmentActionPerformed

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
            java.util.logging.Logger.getLogger(ViewDoctorMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDoctorMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDoctorMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDoctorMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDoctorMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGivePrescription;
    private javax.swing.JButton btnInspectPatientHistory;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnProposeAppointment;
    private javax.swing.JButton btnSaveNotes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAppointment;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JLabel lblScheduledAppointments;
    private javax.swing.JLabel lblWelcomeMessage;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtSelectedAppointment;
    // End of variables declaration//GEN-END:variables
}
