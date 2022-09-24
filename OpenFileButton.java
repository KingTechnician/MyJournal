package journal;
import javafx.event.*;
import javafx.geometry.*;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
public class OpenFileButton 
{
	public static EventHandler<ActionEvent> get(boolean authenticated,FileChooser fileChooser,Stage journalStage,TextArea journalEntry,FileChooser.ExtensionFilter fileFilter)
	{
		EventHandler<ActionEvent> openFileHandler = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				if(authenticated)
				{
					File openFile = fileChooser.showOpenDialog(journalStage);
					while(openFile!=null&&!openFile.getName().endsWith(".mjor"))
					{
						openFile=  fileChooser.showOpenDialog(journalStage);
						if(openFile==null)
						{
							break;
						}
					}					
					String fileContent = "";
					if(openFile!=null)
					{
						TextInputDialog keyDialog = new TextInputDialog("Enter the key for decrypting this file.");
						keyDialog.setHeaderText("Enter the specified key here");
						Optional<String> key = keyDialog.showAndWait();
						String getKey = key.get();
						HeavyEncryption.AES aes = new HeavyEncryption.AES(getKey,"1234");
						try
						{
							int count = 0;
							Scanner readFile = new Scanner(openFile);
							while(readFile.hasNextLine())
							{
								readFile.nextLine();
								count++;
							}
							readFile.close();
							readFile = new Scanner(openFile);
							ArrayList<String> getBinaries = new ArrayList<String>();
							for (int i = 0; i<count;i++)
							{
								String newBinary = "";
								newBinary = aes.decrypt(readFile.nextLine());
								getBinaries.add(newBinary);
								System.out.println(newBinary);
								System.out.println(aes.getKey());
								System.out.println(aes.getSalt());
							}
							readFile.close();
							int keyCount = 0;
							String keyfromFile = "";
							for (String binary:getBinaries)
							{
								if(keyCount<getKey.length())
								{
									keyfromFile+= HeavyEncryption.convertToString(binary);
								}
								else
								{
									fileContent+=HeavyEncryption.convertToString(binary);
								}
								keyCount++;
							}
							getKey = getKey.replaceAll("\\P{Print}","");
							keyfromFile = keyfromFile.replaceAll("\\P{Print}","");
							if(getKey.equals(keyfromFile))
							{
								TextArea newInformation = new TextArea(journalEntry.getText()+fileContent);
								System.out.println("New information: "+newInformation);
								Stage newStage = JournalStage.get(authenticated,fileChooser,fileFilter,newInformation);
								newStage.setTitle(openFile.getName());
								Rectangle2D screen = Screen.getPrimary().getBounds();
								newStage.setHeight(screen.getHeight());
								newStage.setWidth(screen.getWidth());
								newStage.setMaximized(true);
								newStage.show();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Incorrect Key.");
							}
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Incorrect key");
				}
				
				
			}
		};
		return openFileHandler;
	}
}
