package journal;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;
public class JournalApp extends Application
{
	String mode;
	boolean authenticated = true;
	public void setMode(String m)
	{
		this.mode = m;
	}
	public String getMode()
	{
		return this.mode;
	}
	public String randomCode()
	{
		Random rand = new Random();
		int[] randomValues=  new int[4];
		String combineValues="";
		for(int i = 0; i<randomValues.length;i++)
		{
			randomValues[i] = rand.nextInt(9);
			combineValues+=Integer.toString(randomValues[i]);
		}
		System.out.println(combineValues);
		return combineValues;
	}
	public String randomKey()
	{
		String randomKey = "";
		Random rand = new Random();
		int[] randomValues=  new int[7];
		String letters = "abcdefghijklmnopqrstuvwxyz";
		String actualLetter = Character.toString(letters.charAt(rand.nextInt(26)));
		randomKey+=actualLetter;
		for (int i = 0; i<randomValues.length;i++)
		{
			randomValues[i] = rand.nextInt(9);
			randomKey+=Integer.toString(randomValues[i]);
		}
		System.out.println(randomKey);
		return randomKey;
	}
	public void start(Stage primaryStage) throws IOException
	{
		mode = "";
		TextArea journalEntry = new TextArea();
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("MJOR files (*.mjor)","*.mjor");
		fileChooser.getExtensionFilters().add(fileFilter);
		Stage journalStage = new Stage();
		journalStage.setMaximized(true);
		journalStage.setTitle("Welcome Page");
		GridPane welcomeGrid = new GridPane();
		StackPane welcomeMessage = new StackPane();
		
		
		Label welcome = new Label("Share Your Thoughts with MyJournal");
		Text purpose = new Text("At MyJournal, we want to make sure our users can write and store private thoughts for the purpose of catharsis, \nventing, and various other secure tasks.");
		Button newFileButton = new Button("New File");
		Font openFont = Font.font("Courier New",FontWeight.BOLD,36);
		Button openButton = new Button("Open File");
		Button testButton = new Button("Switch Colors");
		
		newFileButton.setFont(openFont);
		newFileButton.setMinWidth(200);
		newFileButton.setMinHeight(200);
		newFileButton.setStyle(JournalStyles.getStyle("button"));
		
		openButton.setFont(openFont);
		openButton.setMinWidth(200);
		openButton.setMinHeight(200);
		openButton.setStyle(newFileButton.getStyle());
		System.out.println(mode);
		openButton.setOnAction(OpenFileButton.get(mode,authenticated, fileChooser, journalStage, journalEntry, fileFilter));
		
		testButton.setFont(openFont);
		testButton.setMinWidth(200);
		testButton.setMinHeight(200);
		testButton.setStyle(JournalStyles.getStyle("button"));
		
		testButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				try
				{
					if(mode.equals(""))
					{
						mode = "dark";
					}
					else if(mode.equals("dark"))
					{
						mode = "";
					}
					newFileButton.setStyle(JournalStyles.getStyle(mode+"button"));
					openButton.setStyle(JournalStyles.getStyle(mode+"button"));
					testButton.setStyle(JournalStyles.getStyle(mode+"button"));
					welcomeGrid.setStyle(JournalStyles.getStyle(mode+"mainmenu"));
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		});
		newFileButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				Rectangle2D screen = Screen.getPrimary().getBounds();
				double width = screen.getWidth();
				double height = screen.getHeight();
				System.out.println("Open File Mode: "+mode);
				Stage newStage = JournalStage.get(mode,authenticated,fileChooser,fileFilter,journalEntry);
				newStage.setWidth(width);
				newStage.setHeight(height);
				newStage.setMaximized(true);
				newStage.show();
				if(mode.equals(""))
				{
					mode = "dark";
				}
				else if (mode.equals("dark"))
				{
					mode = "";
				}
			}
		});
		purpose.setWrappingWidth(journalStage.getMinWidth());
		Font welcomeFont = new Font("Times New Roman", 36);
		Font purposeFont=  new Font("Times New Roman",34);
		welcome.setFont(welcomeFont);
		purpose.setFont(purposeFont);
		welcome.setAlignment(Pos.CENTER);
		welcome.setTextAlignment(TextAlignment.CENTER);
		purpose.setTextAlignment(TextAlignment.CENTER);
		welcomeGrid.add(welcome, 0, 0);
		welcomeGrid.add(purpose, 0, 1);
		GridPane buttonGrid = new GridPane();
		buttonGrid.add(newFileButton, 0, 0);
		buttonGrid.add(openButton, 1, 0);
		buttonGrid.add(testButton, 0, 2);
		buttonGrid.setVgap(50);
		welcomeGrid.add(buttonGrid,0,2);
		welcomeGrid.setVgap(50);
		GridPane.setValignment(welcome, VPos.CENTER);
		Scene titleScene= new Scene(welcomeGrid,600,600);
		welcomeGrid.setStyle(JournalStyles.getStyle("mainmenu"));
		journalStage.setScene(titleScene);
		journalStage.show();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}
