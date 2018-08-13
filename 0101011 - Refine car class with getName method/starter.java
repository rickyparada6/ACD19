import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;

public class starter extends JPanel implements Runnable
{
	//private variables
	static JFrame frame;
	
	int CAR_WIDTH=60;
	Car[] cars;
	Road[] roads;
	
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
		
		roads = new Road[10];
		makeRoads();
		cars = new Car[100];
		makeCars();
		countNames(cars);
		
		Thread thread = new Thread(this);
		//calls the run function
		thread.start();
	}
	
	public void makeRoads()
	{
		for(int i=0; i<roads.length; i++)
			roads[i] = new Road(i*60,600,50);
	}
	//makes cars on a random road evenly spaced out
	public void makeCars()
	{
		String[] names = new String[]{"BOB","SUE","JOE"};
		Color[] colors = new Color[]{Color.BLUE,Color.RED,Color.GREEN};
		
		for(int i=0; i<cars.length; i++)
		{
			String name = names[(int)(Math.random()*names.length)];
			Color color = colors[(int)(Math.random()*colors.length)];
			cars[i] = new Car(-i*CAR_WIDTH,roads[(int)(Math.random()*roads.length)].getY()-10,name,color,2);
		}
	}
	public void countNames(Car[] vehicles)
	{
		String[] allNames = new String[vehicles.length];
		for(int i=0; i<vehicles.length; i++)
			allNames[i] = vehicles[i].getName();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> duplicates = new ArrayList<Integer>();
		for(int i=0; i<allNames.length; i++)
		{
			boolean isOriginal = true;
			int duplicateIndex = -1;
			for(int j=0; j<names.size(); j++)
			{
				if(allNames[i].equals(names.get(j)))
				{
					isOriginal = false;
					duplicateIndex = j;
				}
			}
			if(isOriginal)
			{
				names.add(allNames[i]);
				duplicates.add(1);
			}
			else
				duplicates.set(duplicateIndex, duplicates.get(duplicateIndex)+1);
		}
		for(int i=0; i<names.size(); i++)
			System.out.println("There are "+duplicates.get(i)+" cars named "+names.get(i));
	}
	//called by repaint
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//important to paint roads first so that the cars are not hidden
		for(int i=0; i<roads.length; i++)
			roads[i].paint(g);
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
					cars[i].setLocation(-90*CAR_WIDTH,roads[(int)(Math.random()*roads.length)].getY()-10);
				}
				cars[i].drive();
			}
			repaint();
		
			try { Thread.sleep(15); }
			catch (InterruptedException e) { }
		}
	}
}