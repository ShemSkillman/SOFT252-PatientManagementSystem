/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagementSystem.View.Admin;

import PatientManagementSystem.Model.Data.DoctorRatingSystem.PatientFeedback;
import PatientManagementSystem.View.Event;
import java.util.ArrayList;

/**
 *
 * @author Shem
 */
public class ViewAdminDoctorRatings extends javax.swing.JFrame {

    /**
     * Creates new form ViewDoctorRatings
     */
    
    public Event onSaveSummary, onSelectNewDoctor;
    
    public ViewAdminDoctorRatings() {
        initComponents();
        onSaveSummary = new Event();
        onSelectNewDoctor = new Event();
    }
    
    public void setDoctorNames(ArrayList<String> names) {
        
        for (String name : names) 
        {
            cboxDoctorName.addItem(name);
        }
    }
    
    public void setAverageFiveStarRating(int rating) {
        txtAverageFiveStarRating.setText(Integer.toString(rating));
    }
    
    public void setSummary(String summary) {
        txtSummary.setText(summary);
    }
    
    public String getSelectedDoctorId() {
        
        if (cboxDoctorName.getItemCount() < 1) return null;
        String name = (String)cboxDoctorName.getSelectedItem();
        return name.substring(0, 5);
    }
    
    public void fillFeedbackTable(ArrayList<PatientFeedback> allFeedback) {
        
        for (int i = 0; i < allFeedback.size(); i++)
        {
            PatientFeedback feedback = allFeedback.get(i);
            
            String patientName = feedback.getPatientAccount().getId() + " " + feedback.getPatientAccount().getUser().getName() + " "
                    + feedback.getPatientAccount().getUser().getSurname();
            
            tblPatientRatings.getModel().setValueAt(patientName, i, 0);
            tblPatientRatings.getModel().setValueAt(feedback.getDoctorFiveStarRating(), i, 1);
            tblPatientRatings.getModel().setValueAt(feedback.getMessage(), i, 2);            
        }
    }
    
    public String getSummary() {
        return txtSummary.getText();
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
        cboxDoctorName = new javax.swing.JComboBox<>();
        lblDoctor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPatientRatings = new javax.swing.JTable();
        btnSaveSummary = new javax.swing.JButton();
        lblFiveStarRating = new javax.swing.JLabel();
        txtAverageFiveStarRating = new javax.swing.JTextField();
        lblSummary = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSummary = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelDoctorRatings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor ratings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        cboxDoctorName.setName(""); // NOI18N
        cboxDoctorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxDoctorNameActionPerformed(evt);
            }
        });

        lblDoctor.setText("Doctor");

        tblPatientRatings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Patient name", "Five star rating", "Feedback message"
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
        jScrollPane1.setViewportView(tblPatientRatings);

        btnSaveSummary.setText("Save summary");
        btnSaveSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSummaryActionPerformed(evt);
            }
        });

        lblFiveStarRating.setText("Five star rating");

        txtAverageFiveStarRating.setEditable(false);
        txtAverageFiveStarRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAverageFiveStarRatingActionPerformed(evt);
            }
        });

        lblSummary.setText("Summary");

        txtSummary.setColumns(20);
        txtSummary.setLineWrap(true);
        txtSummary.setRows(5);
        jScrollPane3.setViewportView(txtSummary);

        javax.swing.GroupLayout panelDoctorRatingsLayout = new javax.swing.GroupLayout(panelDoctorRatings);
        panelDoctorRatings.setLayout(panelDoctorRatingsLayout);
        panelDoctorRatingsLayout.setHorizontalGroup(
            panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoctorRatingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoctorRatingsLayout.createSequentialGroup()
                        .addGroup(panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiveStarRating)
                            .addComponent(lblDoctor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxDoctorName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAverageFiveStarRating)))
                    .addGroup(panelDoctorRatingsLayout.createSequentialGroup()
                        .addComponent(lblSummary)
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane3))
                    .addGroup(panelDoctorRatingsLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoctorRatingsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSaveSummary)))
                .addContainerGap())
        );
        panelDoctorRatingsLayout.setVerticalGroup(
            panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoctorRatingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor)
                    .addComponent(cboxDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiveStarRating)
                    .addComponent(txtAverageFiveStarRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSummary)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaveSummary)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDoctorRatings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDoctorRatings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSummaryActionPerformed
        onSaveSummary.invoke();
    }//GEN-LAST:event_btnSaveSummaryActionPerformed

    private void cboxDoctorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxDoctorNameActionPerformed
        onSelectNewDoctor.invoke();
    }//GEN-LAST:event_cboxDoctorNameActionPerformed

    private void txtAverageFiveStarRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAverageFiveStarRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAverageFiveStarRatingActionPerformed

    
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
            java.util.logging.Logger.getLogger(ViewAdminDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAdminDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAdminDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAdminDoctorRatings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAdminDoctorRatings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveSummary;
    private javax.swing.JComboBox<String> cboxDoctorName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblFiveStarRating;
    private javax.swing.JLabel lblSummary;
    private javax.swing.JPanel panelDoctorRatings;
    private javax.swing.JTable tblPatientRatings;
    private javax.swing.JTextField txtAverageFiveStarRating;
    private javax.swing.JTextArea txtSummary;
    // End of variables declaration//GEN-END:variables
}