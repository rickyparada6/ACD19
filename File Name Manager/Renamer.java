import java.io.File;
import java.util.ArrayList;

public class Renamer
{
	//shifts file names by one
	// only shifts files numbered between (and including) start and end
	//precondition: files are numbered in base 10
	public static void shiftFileOrder(File file, int start, int end)
	{
        rename(file,"sfo", start, end);	
	}
	//precondition: files are numbered in binary
	public static void renameFilesInBase10(File file)
	{
		rename(file,"b10",0,0);
	}
	//precondition: files are numbered in base 10
	public static void renameFilesInBinary(File file)
	{
		rename(file,"bin",0,0);
	}
	private static boolean isBetween(int num, int low, int high)
	{
		return ((num >= low) && (num <= high));
	}
	//number is positive
	private static int convertToBase10(int num)
	{
		//array of digits in reverse order
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while(num > 0)
		{
			digits.add(num % 10);
			num /= 10;
		}
		int sum = 0;
		for(int i=0; i<digits.size(); i++)
			sum += digits.get(i) * Math.pow(2, i);
		return sum;
	}
	//number is positive
	//conversion capped to numbers less than 2^8
	private static String convertToBinary(int num)
	{
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int c = 0;
		//"shopping cart" method
		//adds 1's where necessary to create the sum
		while(num > 0)
		{
			int greatest = getGreatestPower(num);
			num -= Math.pow(2, greatest);
			//first iteration creates the array with the proper length
			if(c == 0)
			{
				for(int i=0; i<7; i++)
					digits.add(0);
			}
			//other iterations simply set the correct 0's to 1's
			digits.set(digits.size() - (greatest + 1), 1);
			c++;
		}
		return ArrayToString(digits);
	}
	//gets greatest power of two less than or equal to number
	private static int getGreatestPower(int number)
	{
		int[] Powers_Of_2 = new int[8];
		//creates array of the powers of 2
		for(int i=0; i<Powers_Of_2.length; i++)
			Powers_Of_2[i] = (int)Math.pow(2, i);
		int upperBound = 0;
		while(upperBound < Powers_Of_2.length && number >= Powers_Of_2[upperBound])
			upperBound++;
		return upperBound - 1;
	}
	//returns an int that is the combined digits of the array arr
	private static String ArrayToString(ArrayList<Integer> arr)
	{
		String nums = "";
		for(int i=0; i<arr.size(); i++)
			nums += arr.get(i).toString();
		return nums;
	}
	
	private static void rename(File file, String id, int start, int end)
	{
		File[] ACD = file.listFiles();
        for (int i = 0; i < ACD.length; i++)
        {
            if (ACD[i].isDirectory() && !ACD[i].isHidden() && !ACD[i].getName().equals("File Name Manager"))
            {
				String extra = "";
				int num = 0;
				File myfile = new File(file.getPath() + "\\" + ACD[i].getName());
				String alphabet = "abcdefghijklmnopqrstuvwxyz";
				String[] letters = alphabet.split("");
				boolean isExtraLab = true;
				for(int j=0;j<letters.length;j++)
				{
					if(ACD[i].getName().indexOf(letters[j]) < ACD[i].getName().indexOf(" ") && ACD[i].getName().indexOf(letters[j]) != -1)
					{	
						num = Integer.valueOf(ACD[i].getName().substring(0,ACD[i].getName().indexOf(letters[j])));
						extra = ACD[i].getName().substring(ACD[i].getName().indexOf(letters[j]), ACD[i].getName().indexOf(letters[j])+1);
						break;
					}
					if(j == letters.length-1)
						isExtraLab = false;
				}
				if(!isExtraLab)
				{
					num = Integer.valueOf(ACD[i].getName().substring(0,ACD[i].getName().indexOf(" ")));
				}
				switch(id){
					case "sfo":
						if(isBetween(num, start, end))
							num++;
						myfile.renameTo(new File(file.getPath() + "\\" + num + extra + ACD[i].getName().substring(ACD[i].getName().indexOf(" "))));
						break;
					case "b10":
						myfile.renameTo(new File(file.getPath() + "\\" + convertToBase10(num) + extra + ACD[i].getName().substring(ACD[i].getName().indexOf(" "))));
						break;
					case "bin":
						myfile.renameTo(new File(file.getPath() + "\\" + convertToBinary(num) + extra + ACD[i].getName().substring(ACD[i].getName().indexOf(" "))));
						break;
					default:
						System.out.print("Unrecognized ID");
						break;
				}
			}
		}	
	}
}