package Objects;

//**************************************************************************************************************************************//
//                            This Program Only Serves As My Guider For Placing The Backgrounds.                                        //
//                                                                                                                                      //
//**************************************************************************************************************************************//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rectangles extends JPanel {
	int red = 0;
	int green = 0;
	int blue = 0;

    public Rectangles() {
    }
    public  void paintComponent(Graphics g){
    	Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(red, green, blue));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    public void colorChanger(int r, int g, int b){
    	red = r;
    	green = g;
    	blue = b;
    	repaint();
    }
}