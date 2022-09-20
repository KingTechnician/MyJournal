package journal;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Object;
public class JournalStyles
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String style = getStyle("darkmenu");
		System.out.println(style);
	}
	public static String getStyle(String styleName ) throws IOException, FileNotFoundException
	{
		String search = "";
		switch(styleName)
		{
		 case "button":
			 search = "buttonStyles.css";
			 break;
		 case "mainmenu":
			 search = "mainMenuStyles.css";
			 break;
		 case "darkbutton":
			 search = "darkButtonStyles.css";
			 break;
		 case "darkmainmenu":
			 search = "darkMenuStyles.css";
			 break;
		 case "journalmenu":
			 search = "journalMenuBar.css";
			 break;
		}
		String fileName = System.getProperty("user.dir")+"\\src\\journal\\"+search;
		File styleFile = new File(fileName);
		Scanner readFile = new Scanner(styleFile);
		String style = "";
		while(readFile.hasNextLine())
		{
			style+=readFile.nextLine()+"\n";
		}
		return style;
	}
}