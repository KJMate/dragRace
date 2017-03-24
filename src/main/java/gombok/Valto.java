package gombok;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Crema
 */
public class Valto extends JButton implements MouseListener {

    private int szelesseg = 145;
    private int magassag = 147;
    private Image gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/ShiftUpAlap.png")).getImage();

    public Valto() {
        this.setSize(szelesseg, magassag);
        this.setVisible(true);
        this.setEnabled(true);
        this.setBackground(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gombKep, 0, 0, gombKep.getWidth(null), gombKep.getHeight(null), null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //
    }

    @Override
    public void mousePressed(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/ShiftUpNyomva.png")).getImage();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/ShiftUpAlap.png")).getImage();
    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {
        gombKep = new ImageIcon(this.getClass().getResource("/palyaKepek/ShiftUpAlap.png")).getImage();
    }

}
