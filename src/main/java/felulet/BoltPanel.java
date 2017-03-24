/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package felulet;

import alaposztalyok.AutoT;
import alaposztalyok.Global;
import alaposztalyok.Jatekos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import vezerlo.Vezerlo;

/**
 *
 * @author kunec
 */
public class BoltPanel extends javax.swing.JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -4804999296661418221L;
    private Vezerlo vezerlo;
    private List<AutoT> autok = new ArrayList<>();
    private int autoIndexe = 0;
    private Jatekos jatekos;
    private Image kep = new ImageIcon(this.getClass().getResource("/kepek/shop2.png")).getImage();
    private Image paletta = new ImageIcon(this.getClass().getResource("/kepek/palette.png")).getImage();
    private Image szinPaletta = new ImageIcon(this.getClass().getResource("/kepek/szin_paletta.jpg")).getImage();

    String szinKod;

    public BoltPanel() {
        initComponents();
        this.setSize(Global.JATEK_FELULET_SZELESSEG, Global.JATEK_FELULET_MAGASSAG);
        this.setVisible(false);
        kovetkezoGomb1.addMouseListener(kovetkezoGomb1);
        elozoGomb1.addMouseListener(elozoGomb1);
        visszaGomb1.addMouseListener(visszaGomb1);
        megveszGomb1.addMouseListener(megveszGomb1);
    }

    public void setVezerlo(Vezerlo vezerlo) {
        this.vezerlo = vezerlo;
    }

    /**
     * kirajzolja a hátteret a panelen
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int palettakx = 540;
        int palettaky = 160;
        g.drawImage(kep, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(paletta, palettakx, palettaky, paletta.getWidth(null), paletta.getHeight(null), this);
        int szinPalettakx = 550;
        int szinPalettaky = 420;
        g.drawImage(szinPaletta, szinPalettakx, szinPalettaky, 266, 100, this);
        rajzol(g);
        logotRajzol(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        visszaGomb1 = new gombok.VisszaGomb();
        kovetkezoGomb1 = new gombok.KovetkezoGomb();
        elozoGomb1 = new gombok.ElozoGomb();
        lblJatekosAdatai = new javax.swing.JLabel();
        lblAr = new javax.swing.JLabel();
        lblMarka = new javax.swing.JLabel();
        lblTipus = new javax.swing.JLabel();
        lblLoero = new javax.swing.JLabel();
        megveszGomb1 = new gombok.MegveszGomb();
        lblSzint = new javax.swing.JLabel();
        lblTomeg = new javax.swing.JLabel();
        btnPiros = new javax.swing.JButton();
        btnZold = new javax.swing.JButton();
        btnKek = new javax.swing.JButton();
        btnFekete = new javax.swing.JButton();
        btnSarga = new javax.swing.JButton();
        btnSzurke = new javax.swing.JButton();
        btnSzurke1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 255));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BOLT");

        visszaGomb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visszaGomb1ActionPerformed(evt);
            }
        });

        kovetkezoGomb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kovetkezoGomb1ActionPerformed(evt);
            }
        });

        elozoGomb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elozoGomb1ActionPerformed(evt);
            }
        });

        lblJatekosAdatai.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblJatekosAdatai.setForeground(new java.awt.Color(255, 255, 255));
        lblJatekosAdatai.setText("hgmhgmhgm");

        lblAr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAr.setForeground(new java.awt.Color(255, 255, 255));
        lblAr.setText("ár");

        lblMarka.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMarka.setForeground(new java.awt.Color(255, 255, 255));
        lblMarka.setText("márka");

        lblTipus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTipus.setForeground(new java.awt.Color(255, 255, 255));
        lblTipus.setText("típus");

        lblLoero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLoero.setForeground(new java.awt.Color(255, 255, 255));
        lblLoero.setText("lóerő");

        megveszGomb1.setText("megveszGomb1");
        megveszGomb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                megveszGomb1ActionPerformed(evt);
            }
        });

        lblSzint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSzint.setForeground(new java.awt.Color(255, 255, 255));
        lblSzint.setText("szint");

        lblTomeg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTomeg.setForeground(new java.awt.Color(255, 255, 255));
        lblTomeg.setText("súly");

        btnPiros.setBackground(new java.awt.Color(255, 0, 0));
        btnPiros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPirosActionPerformed(evt);
            }
        });

        btnZold.setBackground(new java.awt.Color(0, 255, 0));
        btnZold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoldActionPerformed(evt);
            }
        });

        btnKek.setBackground(new java.awt.Color(0, 0, 204));
        btnKek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKekActionPerformed(evt);
            }
        });

        btnFekete.setBackground(new java.awt.Color(0, 0, 0));
        btnFekete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeketeActionPerformed(evt);
            }
        });

        btnSarga.setBackground(new java.awt.Color(255, 255, 0));
        btnSarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSargaActionPerformed(evt);
            }
        });

        btnSzurke.setBackground(new java.awt.Color(153, 153, 153));
        btnSzurke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSzurkeActionPerformed(evt);
            }
        });

        btnSzurke1.setBackground(new java.awt.Color(153, 153, 153));
        btnSzurke1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSzurke1.setText("Eredeti");
        btnSzurke1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSzurke1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJatekosAdatai, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                        .addComponent(visszaGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(megveszGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAr, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(elozoGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kovetkezoGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPiros, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnZold, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnKek, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSarga, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnFekete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSzurke, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSzurke1)
                            .addComponent(lblLoero, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSzint, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTomeg, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblJatekosAdatai, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(visszaGomb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(megveszGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(lblMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(elozoGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSzint, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTomeg, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(kovetkezoGomb1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnKek, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSarga, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFekete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSzurke, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPiros, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnZold, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSzurke1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void visszaGomb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visszaGomb1ActionPerformed
        vezerlo.boltbolKilep(jatekos);
    }//GEN-LAST:event_visszaGomb1ActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        try {
            autoAdatKiiras();
            szinKod = String.valueOf(autok.get(autoIndexe).getSzinKod());
            autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
        } catch (Exception ex) {
            Logger.getLogger(BoltPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formAncestorAdded

    private void elozoGomb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elozoGomb1ActionPerformed
        autoIndexe--;
        szinKod = String.valueOf(autok.get(autoIndexe).getSzinKod());
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
        autoAdatKiiras();
        kovetkezoGomb1.setVisible(true);
        if (autoIndexe < 1) {
            elozoGomb1.setVisible(false);
        }
    }//GEN-LAST:event_elozoGomb1ActionPerformed

    private void kovetkezoGomb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kovetkezoGomb1ActionPerformed
        autoIndexe++;
        szinKod = String.valueOf(autok.get(autoIndexe).getSzinKod());
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
        autoAdatKiiras();
        elozoGomb1.setVisible(true);
        if (autoIndexe == autok.size() - 1) {
            kovetkezoGomb1.setVisible(false);
        }
    }//GEN-LAST:event_kovetkezoGomb1ActionPerformed

    private void megveszGomb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_megveszGomb1ActionPerformed
        /* kocsiMintazatString,  kocsiKepDetailString,  kocsiFekString,  kocsiKerekString,  ultetes,
             hatsoLokharitoTavolsag,  kardanTengelyTavolsag,  szint,  meghajtas,  kobcenti)*/
        jatekos.autotVesz(new AutoT(autok.get(autoIndexe).getMarka(),
                autok.get(autoIndexe).getTipus(), autok.get(autoIndexe).getLogoString(),
                szinKod, autok.get(autoIndexe).getVetelAr(),
                autok.get(autoIndexe).getSebessegfokozatok(),
                autok.get(autoIndexe).getLoero(), autok.get(autoIndexe).getTomeg(),
                autok.get(autoIndexe).getMaximumFordulatszam(), autok.get(autoIndexe).getKocsiMintazatString(),
                autok.get(autoIndexe).getKocsiKepDetailString(),
                autok.get(autoIndexe).getKocsiFekString(), autok.get(autoIndexe).getKocsiKerekString(),
                autok.get(autoIndexe).getUltetes(), autok.get(autoIndexe).getHatsoLokharitoTavolsag(),
                autok.get(autoIndexe).getKardanTengelyTavolsag(), autok.get(autoIndexe).getSzint(),
                autok.get(autoIndexe).getMeghajtas(), autok.get(autoIndexe).getKobcenti()));
        adatKiir(jatekos);
    }//GEN-LAST:event_megveszGomb1ActionPerformed

    private void btnPirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPirosActionPerformed
        szinKod = String.valueOf("#FF0000");
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
    }//GEN-LAST:event_btnPirosActionPerformed

    private void btnZoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoldActionPerformed
        szinKod = String.valueOf("#33FF33");
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
    }//GEN-LAST:event_btnZoldActionPerformed

    private void btnKekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKekActionPerformed
        szinKod = String.valueOf("#0000FF");
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
    }//GEN-LAST:event_btnKekActionPerformed

    private void btnFeketeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeketeActionPerformed
        szinKod = String.valueOf("#000000");
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
    }//GEN-LAST:event_btnFeketeActionPerformed

    private void btnSargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSargaActionPerformed
        szinKod = String.valueOf("#FFFF00");
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
    }//GEN-LAST:event_btnSargaActionPerformed

    private void btnSzurkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSzurkeActionPerformed
        szinKod = String.valueOf("#A0A0A0");
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
    }//GEN-LAST:event_btnSzurkeActionPerformed

    private void btnSzurke1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSzurke1ActionPerformed
        szinKod = String.valueOf(autok.get(autoIndexe).getSzinKod());
        autok.get(autoIndexe).setMegadottSzin(Color.decode(szinKod));
    }//GEN-LAST:event_btnSzurke1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFekete;
    private javax.swing.JButton btnKek;
    private javax.swing.JButton btnPiros;
    private javax.swing.JButton btnSarga;
    private javax.swing.JButton btnSzurke;
    private javax.swing.JButton btnSzurke1;
    private javax.swing.JButton btnZold;
    private gombok.ElozoGomb elozoGomb1;
    private javax.swing.JLabel jLabel1;
    private gombok.KovetkezoGomb kovetkezoGomb1;
    private javax.swing.JLabel lblAr;
    private javax.swing.JLabel lblJatekosAdatai;
    private javax.swing.JLabel lblLoero;
    private javax.swing.JLabel lblMarka;
    private javax.swing.JLabel lblSzint;
    private javax.swing.JLabel lblTipus;
    private javax.swing.JLabel lblTomeg;
    private gombok.MegveszGomb megveszGomb1;
    private gombok.VisszaGomb visszaGomb1;
    // End of variables declaration//GEN-END:variables

    /**
     * kirajzolja az aktuális autót
     *
     * @param g
     */
    private void rajzol(Graphics g) {
        int ky = 400;
        int kx = this.getWidth() / 4 + autok.get(autoIndexe).getKepSzelesseg() / 2 + 60;

        for (int i = 0; i < autok.size(); i++) {

            autok.get(autoIndexe).beallit(kx, ky,
                    autok.get(autoIndexe).getKocsiMintazatString(),
                    autok.get(autoIndexe).getKocsiKepDetailString(),
                    autok.get(autoIndexe).getKocsiFekString(),
                    autok.get(autoIndexe).getKocsiKerekString(),
                    /*autok.get(autoIndexe).getKocsiMintazat().getWidth(null),
                    autok.get(autoIndexe).getKocsiMintazat().getHeight(null),*/
                    autok.get(autoIndexe).getUltetes(),
                    autok.get(autoIndexe).getHatsoLokharitoTavolsag(),
                    autok.get(autoIndexe).getKardanTengelyTavolsag(), 1);

            autok.get(autoIndexe).rajzol(g);
        }
        repaint();
    }

    /**
     * kiírja a játékos adatiat
     *
     * @param jatekos
     */
    public void adatKiir(Jatekos jatekos) {
        lblJatekosAdatai.setText(jatekos.getNev() + "     Egyenlege: " + jatekos.getPenz() + " $");
        this.jatekos = jatekos;
    }

    /**
     * az autó adatai írja ki
     */
    private void autoAdatKiiras() {
        lblMarka.setText("" + autok.get(autoIndexe).getMarka());
        lblTipus.setText("Típus: " + autok.get(autoIndexe).getTipus());
        lblAr.setText("Vételár: " + autok.get(autoIndexe).getVetelAr() + " $");
        lblLoero.setText("Lóerő: " + autok.get(autoIndexe).getLoero());
        lblSzint.setText("Level: " + autok.get(autoIndexe).getSzint());
        lblTomeg.setText("Tömeg: " + autok.get(autoIndexe).getTomeg() + " kg");
    }

    /**
     * beolvasott autókból új példányt hoz létre
     *
     * @param autokt
     */
    public void beolvasottAutokAtadasa(List<AutoT> autokt) {
        this.autok = autokt;
    }

    /**
     * kirajzolja az aktuális autó logoját
     *
     * @param g
     */
    private void logotRajzol(Graphics g) {
        int kx;
        int ky;
        if (!autok.isEmpty()) {
            for (int i = 0; i < autok.size(); i++) {
                autok.get(autoIndexe).logoBeallitas(autok.get(autoIndexe).getLogoString());
                if (autok.get(autoIndexe).getLogo().getHeight(null) != autok.get(autoIndexe).getLogo().getWidth(null)) {
                    kx = 207;
                    ky = 157;
                } else {
                    kx = 220;
                    ky = 147;
                }
                g.drawImage(autok.get(autoIndexe).getLogo(), kx, ky, autok.get(autoIndexe).getLogo().getWidth(null), autok.get(autoIndexe).getLogo().getHeight(null), null);
            }
        }
    }

}
