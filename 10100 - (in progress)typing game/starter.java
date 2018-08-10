import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;

public class starter extends JPanel implements Runnable, KeyListener
{
	static JFrame frame;
	
	//behind the scenes
	Rectangle2D.Double rect;
	int startPos=50;
	int xCoord=startPos;
	int counter=0;
	double spacing;
	FontMetrics metrics;
	ArrayList<String> letters = new ArrayList<String>();
	ArrayList<Integer> widths = new ArrayList<Integer>();
	
	//user part
	String phrase = "Hello my name is Bob and I go to Crescenta Valley High School!";
	String answer="";
	int mistakes=0;
	int cl=0;
	
	
	
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
		
		rect = new Rectangle2D.Double(xCoord,100,50,50);
		
		frame.addKeyListener(this);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		String name = "JOE";
		g.drawString(name,(int)rect.getX()+10,(int)rect.getY()+30);
		
		String coords = "("+(int)rect.getX()+","+(int)rect.getY()+")";
		g.drawString(coords,(int)rect.getX(),(int)rect.getY()-10);
	
		//start
		metrics = g.getFontMetrics(g.getFont());
		if(counter==0)
		{
			spacing = startPos+metrics.stringWidth(phrase.substring(0,1));
		}
		
		if(rect.getX()==spacing&&counter<phrase.length())
		{
			letters.add(phrase.substring(counter,counter+1));
			int width = metrics.stringWidth(phrase.substring(counter,counter+1));
			widths.add(width);
			counter++;
			spacing+=width;
		}
		
		int sum=0;
		for(int i=0;i<letters.size();i++)
		{
			g.drawString(letters.get(i),startPos+sum,(int)rect.getY()+15);
			sum+=widths.get(i);
		}
		//end
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLUE);
		g2.fill(rect);
		
		g.setColor(Color.BLACK);
		g.drawString("Start: "+answer,50,200);
		g.setColor(Color.RED);
		g.drawString("Mistakes: "+mistakes,50,225);
		
		g.setColor(Color.BLACK);
		Font myFont = new Font("Serif", Font.BOLD, 20);
		g.setFont(myFont);
		g.drawString("Current Letter: " + phrase.substring(cl,cl+1),200,50);
		
		if(answer.equals(phrase))
		{
			g.drawString("YOU WIN!",200,300);
		}
	}
	
	
	public void run()
	{
		while(!answer.equals(phrase))
		{
			xCoord+=1;
			rect.setFrame(xCoord,100,50,50);
			repaint();
		
			try { Thread.sleep(50); }
			catch (InterruptedException e) { }
		}
	}
	
	public void keyTyped(KeyEvent e)
	{
		char cc = e.getKeyChar();
		String c = Character.toString(cc);
		
		if(cl<phrase.length())
		{
			if(c.equals(phrase.substring(cl,cl+1)))
			{
				answer+=c;
				if(cl<phrase.length()-1)
				{
					cl++;
				}
				repaint();
			}
			else if(!c.equals(phrase.substring(cl,cl+1)))
			{
				mistakes++;
			}
		}
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
}

