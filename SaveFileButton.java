package dataStructures;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import javax.swing.JOptionPane;
public class SaveFileButton 
{
	public static EventHandler<ActionEvent> get(TextArea journalEntry, FileChooser fileChooser, Stage journalStage)
	{
		EventHandler<ActionEvent> saveFileHandler = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				int keyDecision = JOptionPane.showConfirmDialog(null,"Use a custom key?","Key Decision",JOptionPane.YES_NO_OPTION);
				ProgressIndicator encryptProgress = new ProgressIndicator();
				Scene progressScene = new Scene(encryptProgress,200,200);
				Stage encryptStage = new Stage();
				encryptStage.setScene(progressScene);
				encryptStage.show();
				if(keyDecision==JOptionPane.YES_OPTION)
				{
					TextInputDialog keyDialog = new TextInputDialog("Enter the key you would like to use to encrypt this file.");
					keyDialog.setHeaderText("Choose a desired key");
					Optional<String> receiveKey = keyDialog.showAndWait();
					String getKey = receiveKey.get();
					HeavyEncryption.AES aes = new HeavyEncryption.AES(getKey,"1234");
					String[] setBinaries = aes.formEncryptedBinaries(journalEntry.getText());
					String[] binaryKeys = aes.formEncryptedBinaries(getKey);
					String[] getBinaries = new String[setBinaries.length+binaryKeys.length];
					int arrayCount = 0;
					for (int i = 0; i<binaryKeys.length;i++)
					{
						getBinaries[arrayCount] = binaryKeys[i];
						arrayCount++;
					}
					for (int i = 0; i<setBinaries.length;i++)
					{
						getBinaries[arrayCount] = setBinaries[i];
						arrayCount++;
					}
					File saveFile = fileChooser.showSaveDialog(journalStage);
					try
					{
						FileWriter writeFile = new FileWriter(saveFile);
						for (int i  =0; i<getBinaries.length;i++)
						{
							writeFile.append(getBinaries[i]+"\n");
							System.out.println(getBinaries[i]);
							encryptProgress.setProgress(i/getBinaries.length);
							if((i/getBinaries.length)==((getBinaries.length-1)/getBinaries.length))
							{
								encryptStage.close();
							}
						}
						writeFile.close();
						journalStage.setTitle(saveFile.getName());
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					String randomCode = new JournalWindow().randomKey();
					HeavyEncryption.AES aes = new HeavyEncryption.AES(randomCode,"1234");
					randomCode+="\n";
					String[] setBinaries = aes.formEncryptedBinaries(journalEntry.getText());
					String[] binaryKeys = aes.formEncryptedBinaries(randomCode);
					String[] getBinaries = new String[setBinaries.length+binaryKeys.length];
					int arrayCount = 0;
					for (int i = 0; i<binaryKeys.length;i++)
					{
						getBinaries[arrayCount] = binaryKeys[i];
						arrayCount++;
					}
					for (int i = 0; i<setBinaries.length;i++)
					{
						getBinaries[arrayCount] = setBinaries[i];
						arrayCount++;
					}
					File saveFile = fileChooser.showSaveDialog(journalStage);
					try
					{
						FileWriter writeFile = new FileWriter(saveFile);
						for (int i  =0; i<getBinaries.length;i++)
						{
							writeFile.append(getBinaries[i]+"\n");
							System.out.println(getBinaries[i]);
							encryptProgress.setProgress(i/getBinaries.length);
							if((i/getBinaries.length)==((getBinaries.length-1)/getBinaries.length))
							{
								encryptStage.close();
							}
						}
						writeFile.close();
						journalStage.setTitle(saveFile.getName());
						Alert codeAlert = new Alert(AlertType.INFORMATION);
						codeAlert.setTitle("Your randomized code");
						codeAlert.setContentText("Your randomized key is: "+randomCode);
						codeAlert.showAndWait();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		};
		return saveFileHandler;
	}
}
