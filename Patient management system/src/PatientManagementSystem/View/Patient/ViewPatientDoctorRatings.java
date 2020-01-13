/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.View.Patient;

import PatientManagementSystem.Model.Data.DoctorRatingSystem.DoctorRating;
import PatientManagementSystem.Model.Data.ModelAccountSystem;
import PatientManagementSystem.Model.User.Doctor;
import PatientManagementSystem.View.EventSystem.Event;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shem
 */
public class ViewPatientDoctorRatings extends javax.swing.JFrame {

    /**
     * Creates new form ViewDoctorRatings
     */
    
    public Event onRateDoctor;
    
    public ViewPatientDoctorRatings() {
        initComponents();
        onRateDoctor = new Event();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDoctorRatings = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoctorRatings = new javax.swing.JTable();
        btnRateDoctor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelDoctorRatings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor ratings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        tblDoctorRatings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Doctor name", "Five star rating", "Summary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDoctorRatings);

        btnRateDoctor.setText("Rate doctor");
        btnRateDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRateDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDoctorRatingsLayout = new javax.swing.GroupLayout(panelDoctorRatings);
        panelDoctorRatings.setLayout(panelDoctorRatingsLayout);
        panelDoctorRatingsLayout.setHorizontalGroup(
            panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoctorRatingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoctorRatingsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRateDoctor))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDoctorRatingsLayout.setVerticalGroup(
            panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoctorRatingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRateDoctor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDoctorRatings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDoctorRatings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRateDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRateDoctorActionPerformed
        onRateDoctor.invoke();
    }//GEN-LAST:event_btnRateDoctorActionPerformed

    public void FillRatingsTable(ArrayList<DoctorRating> doctorRatings, ModelAccountSystem accountSystem)
    {
        DefaultTableModel model = (DefaultTableModel)tblDoctorRatings.getModel();
        model.setRowCount(doctorRatings.size());
        tblDoctorRatings.setModel(model);
        
        for (int i = 0; i < doctorRatings.size(); i++)
        {
            DoctorRating rating = doctorRatings.get(i);
            Doctor doctor = (Doctor)accountSystem.getAccount(rating.getDoctorId()).getUser();
            String fullName = doctor.getName() + " " + 
                    doctor.getSurname();
            
            tblDoctorRatings.getModel().setValueAt(fullName, i, 0);
            tblDoctorRatings.getModel().setValueAt(rating.getAverageFiveStarRating(), i, 1);
            tblDoctorRatings.getModel().setValueAt(rating.getFeedbackSummary(), i, 2);            
        }
    }
    
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
            java.util.logging.Logger.getLogger(ViewPatientDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPatientDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPatientDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPatientDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPatientDoctorRatings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRateDoctor;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelDoctorRatings;
    private javax.swing.JTable tblDoctorRatings;
    // End of variables declaration//GEN-END:variables
}
