/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Komponen;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author Aries
 */
public class login extends JPanel{
    private Image image;
    public login()
    {
     image = new ImageIcon(getClass().getResource("/gambar/loginn.jpg")).getImage();
    }
    @Override
protected void paintComponent(Graphics grphcs)
    {
      super.paintComponent(grphcs);
      Graphics2D gd = (Graphics2D) grphcs.create();
      gd.drawImage(image, 0, 0, getWidth(),getHeight(),null);
    }
}
