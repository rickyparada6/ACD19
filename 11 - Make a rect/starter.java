import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class starter extends JPanel 
{
	static JFrame frame;
	
	String joe;
	Rectangle2D.Double rect;
	
	public static void main(String[] args) {
		frame = new JFrame("App");
		frame.add(new starter());
		frame.setSize(600,600);
		frame.setVisible(true);
	}
	
	public starter()
	{
		Color a = new Color(0,255,0);
		setBackground(a);
		
		rect = new Rectangle2D.Double(0,0,200,100);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLUE);
		g2.fill(rect);
		//g2.draw(rect);
	}

}

