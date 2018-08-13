public class starter {

        public static void main(String args[])
        {
			int counter = 0;
			EasyReader in;
			int td;
			in = new EasyReader();
			Rectangle box = new Rectangle(5,10,60,80);
			box.setColor(Color.BLUE);
			box.fill();
			System.out.print("Please enter time delay: ");
			td = in.readInt();
			while(true)
			{
				Canvas.pause(td);
				box.translate(10,0);
			}
		}

}
