public class starter implements InputKeyControl {
		private static int place;
        public static void main(String args[])
        {
			// please leave alone, necessary for keyboard input
			
			KeyController mC = new KeyController(Canvas.getInstance(),new starter());
			place = 5;
		}
		public void keyPress(String s)
		{
			char done = (char)65;
			String temp = Character.toString(done);
			System.out.println("Go! "+"happy");
			Text dispAns = new Text(place,200,temp);
			dispAns.draw();
			place = place+10;
		}

}
