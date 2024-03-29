/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package felulet;

import alaposztalyok.Global;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import vezerlo.Vezerlo;

/**
 *
 * @author kunec
 */
public class SugoPanel extends javax.swing.JPanel {

    private Vezerlo vezerlo;
    private Image kep = new ImageIcon(this.getClass().getResource("/kepek/sugo.jpg")).getImage();
    private String szoveg;

    public SugoPanel() {
        initComponents();
        this.setVisible(false);
        this.setSize(Global.KEZDO_FELULET_SZELESSEG, Global.KEZDO_FELULET_MAGASSAG);
        inditGomb1.addActionListener(inditGomb1);
        inditGomb1.addMouseListener(inditGomb1);
        exitGomb1.addActionListener(exitGomb1);
        exitGomb1.addMouseListener(exitGomb1);
    }

    public void setVezerlo(Vezerlo vezerlo) {
        this.vezerlo = vezerlo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(kep, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inditGomb1 = new gombok.InditGomb();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSugo = new javax.swing.JTextArea();
        exitGomb1 = new gombok.ExitGomb();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        inditGomb1.setText("inditGomb1");

        txtSugo.setEditable(false);
        txtSugo.setColumns(20);
        txtSugo.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtSugo.setLineWrap(true);
        txtSugo.setRows(5);
        jScrollPane2.setViewportView(txtSugo);

        exitGomb1.setText("exitGomb1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inditGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inditGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        szoveg = "1. A játék használatához be kell jelentkezni \n"
                + "\n"
                + "2. Belépés után a saját garázsodba kerülsz, ahol az\n"
                + "autóidat"
                + "tárolhatod. Ha új játékos vagy akkor előszőr a boltba kell "
                + "ellátogatnod, ahol tudsz venni autót. Új\n"
                + "játékosként "
                + "15000$ kezdő összeggel indítod a karrieredet A játék során "
                + "versenyezhetsz más autók ellen. Verseny\nalatt sok múlik "
                + "azon, hogy miként váltasz. Győzelem\nesetén nyersz egy bizonyos összeget, "
                + "valamint tapaszta -lati pontot (xp). Minél magasabb a szinted annál jobb"
                + " és gyorsabb autókat vehetsz magadnak.\n"
                + "\n"
                + "3. A futam a play gombra kattintva fog indulni. \n"
                + "\n"
                + "4. Ne felejts el menteni.";
        txtSugo.setText(szoveg);
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gombok.ExitGomb exitGomb1;
    private gombok.InditGomb inditGomb1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtSugo;
    // End of variables declaration//GEN-END:variables
}
