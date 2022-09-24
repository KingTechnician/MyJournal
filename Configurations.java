package journal;
import java.io.File;
//String fileName = System.getProperty("user.dir")+"\\src\\journal\\configurations.txt";
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.io.IOException;
public class Configurations 
{
	public static void main(String[] args) throws IOException
	{
		Duration duration = lastOpened();
		System.out.println(duration.toSeconds());
		HashMap<String,String> configurations = grabConfigurations();
		System.out.println(configurations);
		System.out.println(configurations.get("<mode>"));
		
	}
	public static HashMap<String,String> grabConfigurations() throws IOException
	{
		String fileName = System.getProperty("user.dir")+"\\src\\journal\\configurations.txt";
		File configFile = new File(fileName);
		Scanner readFile = new Scanner(configFile);
		HashMap<String,String> configurations = new HashMap<String,String>();
		while(readFile.hasNextLine())
		{
			String readLine = readFile.nextLine();
			String[] splitLine = readLine.split("=");
			if(splitLine.length==2)
			{
				configurations.put(splitLine[0], splitLine[1]);
			}
		}
		readFile.close();
		return configurations;
	}
	public static boolean checkExistence() throws IOException
	{
		String fileName = System.getProperty("user.dir")+"\\src\\journal\\configurations.txt";
		File checkFile = new File(fileName);
		if(checkFile.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static void createDefaultFile() throws IOException
	{
		if(!checkExistence())
		{
			HashMap<String,String> configurations = new HashMap<String,String>();
			configurations.put("<time>", Instant.now().toString());
			configurations.put("<mode>", "default");
			saveConfigurations(configurations);
		}
		else
		{
			System.out.println("Configuration file found!");
		}
	}
	public static void saveConfigurations(HashMap<String,String> configurations) throws IOException
	{
		Set<String> contents = configurations.keySet();
		String fileContent = "";
		for(String c:contents)
		{
			fileContent+=c+"="+configurations.get(c)+"\n";
		}
		String fileName = System.getProperty("user.dir")+"\\src\\journal\\configurations.txt";
		File configFile = new File(fileName);
		FileWriter writer = new FileWriter(configFile);
		writer.write(fileContent);
		writer.close();
	}
	public static Duration lastOpened() throws IOException
	{
		String fileName = System.getProperty("user.dir")+"\\src\\journal\\configurations.txt";
		File configFile = new File(fileName);
		HashMap<String,String> configurations = new HashMap<String,String>();
		Scanner readFile = new Scanner(configFile);
		while(readFile.hasNextLine())
		{
			String nextLine = readFile.nextLine();
			String[] splitLine = nextLine.split("=");
			if(splitLine.length==2)
			{
				configurations.put(splitLine[0], splitLine[1]);
			}
		}
		readFile.close();
		if(configurations.get("<time>")==null)
		{
			return Duration.ZERO;
		}
		else
		{
			Instant before = Instant.parse(configurations.get("<time>"));
			Instant during = Instant.now();
			Duration duration = Duration.between(before, during);
			return duration;
		}
	}
}
