package dataStructures;
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

public class JournalWindow extends Application
{
	boolean authenticated = true;
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
	public void start(Stage primaryStage)
	{
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
		Button openButton = new Button("New File");
		Font openFont = Font.font("Courier New",FontWeight.BOLD,36);
		Button secondOpenButton = new Button("Open File");
		secondOpenButton.setFont(openFont);
		double buttonWidth = journalStage.getMinWidth()/1;
		double buttonHeight = journalStage.getMinHeight()/1;
		secondOpenButton.setMinWidth(200);
		secondOpenButton.setMinHeight(200);
		openButton.setFont(openFont);
		openButton.setMinWidth(200);
		openButton.setMinHeight(200);
		openButton.setStyle("#Button {\r\n"
				+ "    -fx-padding: 8 15 15 15;\r\n"
				+ "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\n"
				+ "    -fx-background-radius: 8;\r\n"
				+ "    -fx-background-color: \r\n"
				+ "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\r\n"
				+ "        #9d4024,\r\n"
				+ "        #d86e3a,\r\n"
				+ "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\r\n"
				+ "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
				+ "    -fx-font-weight: bold;\r\n"
				+ "    -fx-font-size: 1.1em;\r\n"
				+ "}\r\n"
				+ "#Button:hover {\r\n"
				+ "    -fx-background-color: \r\n"
				+ "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\r\n"
				+ "        #9d4024,\r\n"
				+ "        #d86e3a,\r\n"
				+ "        radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);\r\n"
				+ "}\r\n"
				+ "#Button:pressed {\r\n"
				+ "    -fx-padding: 10 15 13 15;\r\n"
				+ "    -fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;\r\n"
				+ "}\r\n"
				+ "#Button Text {\r\n"
				+ "    -fx-fill: white;\r\n"
				+ "    -fx-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );\r\n"
				+ "} ");
		secondOpenButton.setStyle(openButton.getStyle());
		secondOpenButton.setOnAction(OpenFileButton.get(authenticated, fileChooser, journalStage, journalEntry, fileFilter));
		openButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				Rectangle2D screen = Screen.getPrimary().getBounds();
				double width = screen.getWidth();
				double height = screen.getHeight();
				Stage newStage = JournalStage.get(authenticated,fileChooser,fileFilter,journalEntry);
				newStage.setWidth(width);
				newStage.setHeight(height);
				newStage.setMaximized(true);
				newStage.show();
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
		buttonGrid.add(openButton, 0, 0);
		buttonGrid.add(secondOpenButton, 0, 1);
		buttonGrid.setVgap(50);
		welcomeGrid.add(buttonGrid,0,2);
		welcomeGrid.setVgap(50);
		GridPane.setValignment(welcome, VPos.CENTER);
		Scene titleScene= new Scene(welcomeGrid,600,600);
		welcomeGrid.setStyle(".root{\r\n"
				+ "    -fx-font-size: 16pt;\r\n"
				+ "    -fx-font-family: \"Courier New\";\r\n"
				+ "    -fx-base: rgb(132, 145, 47);\r\n"
				+ "    -fx-background: rgb(225, 228, 203);\r\n"
				+ "}");
		journalStage.setScene(titleScene);
		journalStage.show();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}
