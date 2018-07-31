import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class starter extends JPanel implements Runnable
{
	//private variables
	static JFrame frame;
	
	int CAR_WIDTH=60;
	Car[] cars;

	public static void main(String[] args)
	{
		frame = new JFrame("App");
		frame.add(new starter());
		frame.setSize(600,600);
		frame.setVisible(true);
	}
	//creates all objects
	public starter()
	{	
		Color a = new Color(0,255,0);
		setBackground(a);
		
		cars = new Car[100];
		makeCars();
		
		Thread thread = new Thread(this);
		//calls the run function
		thread.start();
	}
	public void makeCars()
	{
		String[] names = new String[]{"BOB","SUE","JOE"};
		Color[] colors = new Color[]{Color.BLUE,Color.RED,Color.YELLOW};
		
		for(int i=0; i<cars.length; i++)
		{
			String name = names[(int)(Math.random()*names.length)];
			Color color = colors[(int)(Math.random()*colors.length)];
			cars[i] = new Car(-i*CAR_WIDTH,(int)(Math.random()*500),name,color,2);
		}
	}
	
	//called by repaint
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(int i=0; i<cars.length; i++)
			cars[i].paint(g);
	}
	
	public void run()
	{
		while(true)
		{
			for(int i=0;i<cars.length;i++)
			{
				//recycles cars if they go off screen and makes sure no car overlap
				if(cars[i].getX() >= 600)
				{
					cars[i].setLocation(-90*CAR_WIDTH,(int)(Math.random()*500));
				}
				cars[i].drive();
			}
			repaint();
		
			try { Thread.sleep(15); }
			catch (InterruptedException e) { }
		}
	}
}