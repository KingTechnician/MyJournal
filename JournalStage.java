package dataStructures;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JournalStage 
{
	public static Stage get(boolean authenticated,FileChooser fileChooser,FileChooser.ExtensionFilter fileFilter,TextArea journalEntry)
	{
		Stage journalStage = new Stage();
		MenuBar menuBar = new MenuBar();
		fileChooser.getExtensionFilters().add(fileFilter);
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu helpMenu = new Menu("Help");
		MenuItem openFileItem = new MenuItem("Open File");
		MenuItem saveItem = new MenuItem("Save File");
		MenuItem exitItem = new MenuItem("Exit");
		MenuItem cutItem = new MenuItem("Cut");
		MenuItem copyItem = new MenuItem("Copy");
		MenuItem pasteItem=  new MenuItem("Paste");
		exitItem.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				journalEntry.setText("");
				journalStage.close();
			}
		});
		openFileItem.setOnAction(OpenFileButton.get(authenticated, fileChooser, journalStage, journalEntry, fileFilter));
		saveItem.setOnAction(SaveFileButton.get(journalEntry, fileChooser, journalStage));
		saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		openFileItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
		fileMenu.getItems().addAll(saveItem,openFileItem,exitItem);
		editMenu.getItems().addAll(cutItem,copyItem,pasteItem);
		
		menuBar.getMenus().addAll(fileMenu,editMenu,helpMenu);
		
		journalEntry.setPrefHeight(200);
		journalEntry.setPrefWidth(500);
		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setCenter(journalEntry);
		Scene scene = new Scene(root,600,600);
		journalStage.setTitle("New File");
		journalStage.setScene(scene);
		Rectangle2D screen = Screen.getPrimary().getBounds();
		journalStage.setHeight(screen.getHeight());
		journalStage.setWidth(screen.getWidth());
		EventHandler<WindowEvent> closeHandler = new EventHandler<WindowEvent>() 
		{
			public void handle (WindowEvent event)
			{
				journalEntry.setText("");
			}
		};
		journalStage.setOnCloseRequest(closeHandler);
		//primaryStage.show();
		return journalStage;
	}
}
