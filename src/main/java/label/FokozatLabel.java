/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package label;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author kunec
 */
public class FokozatLabel extends JLabel {

    /**
     *
     */
    private static final long serialVersionUID = 8483258005560493640L;
    private int szelesseg = 60;
    private int magassag = 60;
    private Border LineBorder;

    public FokozatLabel() {
        this.setSize(szelesseg, magassag);
        this.setVisible(true);
        this.setEnabled(true);
        this.setBorder(LineBorder);
        this.setFont(new Font("Tahoma", Font.BOLD, 30));
    }

}
