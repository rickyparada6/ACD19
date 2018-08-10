public class starter {

        public static void main(String args[])
        {
			System.out.print("Please enter your favorite word: ");
			EasyReader in;
			in = new EasyReader();
			String w = in.readWord();
			int SCALE_FACTOR = 10;
			int wLen = w.length();
			System.out.println(wLen);
			int counter = 0;
			int td;
			int d = 1;
			int xStart = 5;
			int yStart = 10;
			Rectangle box = new Rectangle(xStart,yStart,60,100);
			box.setColor(Color.BLUE);
			box.fill();
			Text name = new Text(20,50,"Neato");
			name.draw(); 
			Text loc = new Text(20,70,box.getX()+", "+box.getY());
			loc.draw();
			td = 10000/2;
			Text trail = new Text(-10,box.getY()+box.getWidth()/2,w);
			while(box.getX() < 570)
			{
				if(box.getX() % trail.getWidth() == 0)
				{
					Text t = new Text(box.getX(),box.getY()+box.getWidth()/2,w);
					t.draw();
				}
				int locDx = xStart-box.getX();
				int locDy = yStart - box.getY();
				Canvas.pause(td);
				box.translate(d,0);
				name.translate(d,0);
				loc.translate(d,0);
				loc.setText(box.getX()+", "+box.getY());
			}
		}

}
