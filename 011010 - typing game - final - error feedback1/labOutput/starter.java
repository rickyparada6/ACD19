public class starter implements InputKeyControl {
		private static int place;
		private static String sent;
		private static String w;
		private static boolean stillMoving;
        public static void main(String args[])
        {
			// please leave alone, necessary for keyboard input
			
			stillMoving = true;
			KeyController mC = new KeyController(Canvas.getInstance(),new starter());
			place = 5;
			sent = "";
			String answer;
			Text dispAns = new Text(5,200," ");
			dispAns.draw();
			//  System.out.print("Please enter your favorite word: ");
			EasyReader in;
			in = new EasyReader();
			// String w = in.readLine();
			w = "the quick.";
			int wLen = w.length();
			int m = -1;
			// System.out.println(wLen);
			Text bigT = new Text(-5,20,"");
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
			td = 10000;
			// this is just to get the spacing correct
			// first try - String test = w.substring(0,1);
			// String test = w.substring(0,1);
			// Text trail = new Text(-10,box.getY()+box.getWidth()/2,test);
			for(int i = 0; i < w.length(); i++)
			{
				String s = w.substring(i, i+1);
				Text t = new Text(5,counter*10,s);
				// System.out.println(s + " " + t.getWidth());
				if(t.getWidth() > m)
				{
					m = t.getWidth();
					bigT = t;
				}
			}
			while(box.getX() < 570)
			{
				if(stillMoving)
				{
					if(counter < w.length())
					{
						if(box.getX() % bigT.getWidth() == 0)
						{
							String s = w.substring(counter, counter+1);
							counter++;
							Text tt = new Text(box.getX(),box.getY()+box.getWidth()/2,s);
							tt.draw();
						}
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
		public void keyPress(String s)
		{
			// this is an enter char
			char done = (char)10;
			String temp = Character.toString(done);
			if(temp.equals(s))
			{
				stillMoving = false;
				System.out.println("check if its correct");
				if(sent.equals(w))
				{
					System.out.println("U got it correct!");
					Text res = new Text(200,150, "CORRECT!");
					res.draw();
				}
				else
				{
					System.out.println("U got it wrong.");
					Text res = new Text(200,150, "WRONG!");
					res.draw();
				}
			}
			else
			{
				sent = sent+s;
				Text dispAns = new Text(place,200,s);
				dispAns.draw();
				place = place+10;
			}
		}

}
