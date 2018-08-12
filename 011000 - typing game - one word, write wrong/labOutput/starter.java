public class starter {

        public static void main(String args[])
        {
			String answer;
			Text dispAns = new Text(5,200," ");
			dispAns.draw();
			Text result;
			//  System.out.print("Please enter your favorite word: ");
			EasyReader inKb;
			inKb = new EasyReader();
			Text sam;
			String magic = "";
			EasyReader in;
			in = new EasyReader("words.txt");
			int counter = 0;
			int c2;
			while(!in.eof())
			{
				System.out.println(in.readWord());
				counter++;
			}
			in.close();
			in = new EasyReader("words.txt");
			System.out.println("there are "+counter+" words.");
			c2 = (int)(Math.random()*counter)+1;
			System.out.println("the magic number is: "+c2);
			counter = 0;
			while(counter < c2)
			{
				magic = in.readWord();
				counter++;
			}
			System.out.println(magic);
			sam = new Text(5,23,magic);
			sam.draw();
			answer = inKb.readLine();
			dispAns.setText(answer);
			dispAns.draw();
			if(answer.equals(magic))
			{
				System.out.println("congratz!, you got it");
				result = new Text(200,100,"congratz!, you got it");
				result.draw();
			}
			else
			{
				System.out.println("you got it wrong");
				result = new Text(200,100,"sorry, you got it wrong");
				result.draw();
			}
		}

}
