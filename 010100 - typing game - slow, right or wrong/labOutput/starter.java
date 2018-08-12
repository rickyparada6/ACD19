public class starter {

        public static void main(String args[])
        {
			String answer;
			Text dispAns = new Text(5,200," ");
			dispAns.draw();
			//  System.out.print("Please enter your favorite word: ");
			EasyReader in;
			in = new EasyReader();
			// String w = in.readLine();
			String w = "The";
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
			System.out.println("got Here ");
			answer = in.readLine();
			dispAns.setText(answer);
			dispAns.draw();
			if(answer.equals(w))
			{
				System.out.println("congratz!, you got it");
			}
			else
			{
				System.out.println("you got it wrong");
			}
			
			// System.out.println("The max is: "+ m);
				
		}

}
