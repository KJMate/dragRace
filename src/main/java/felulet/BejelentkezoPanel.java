/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package felulet;

import alaposztalyok.Global;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import vezerlo.Vezerlo;

/**
 *
 * @author kunec
 */
public class BejelentkezoPanel extends javax.swing.JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -5827806856181376412L;
    private Vezerlo vezerlo;
    private Image kep = new ImageIcon(this.getClass().getResource("/kepek/lambo.jpg")).getImage();

    public BejelentkezoPanel() {
        initComponents();
        this.setSize(Global.JATEK_FELULET_SZELESSEG, Global.JATEK_FELULET_MAGASSAG);
        bejelentkezoGomb1.addMouseListener(bejelentkezoGomb1);
    }

    /**
     * kirajzolja a hátteret a panelen
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(kep, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void setVezerlo(Vezerlo vezerlo) {
        this.vezerlo = vezerlo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNev = new javax.swing.JTextField();
        bejelentkezoGomb1 = new gombok.BejelentkezoGomb();

        setBackground(new java.awt.Color(153, 153, 255));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Felhasználói név");

        txtNev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNevKeyPressed(evt);
            }
        });

        bejelentkezoGomb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bejelentkezoGomb1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNev)
                    .addComponent(bejelentkezoGomb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(525, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(367, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtNev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bejelentkezoGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        vezerlo.beolvas();
    }//GEN-LAST:event_formAncestorAdded

    private void bejelentkezoGomb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bejelentkezoGomb1ActionPerformed
        if (!txtNev.getText().equals("")) {
            vezerlo.bejelentkezes(txtNev.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Kérem írja be a nevét", "Hiba", 1);
        }
    }//GEN-LAST:event_bejelentkezoGomb1ActionPerformed

    private void txtNevKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNevKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !txtNev.getText().equals("")) {
            vezerlo.bejelentkezes(txtNev.getText());
        }
    }//GEN-LAST:event_txtNevKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gombok.BejelentkezoGomb bejelentkezoGomb1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtNev;
    // End of variables declaration//GEN-END:variables
}
