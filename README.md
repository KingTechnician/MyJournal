# MyJournal
A desktop app that allows for simple text and note-taking, that can be protected with AES-256 bit encryption.

The name of this app largely outlines its purposes: The MyJournal app is meant to serve as an even more well-protected personal journal.

The typical nature of keeping a locked notebook is taken to the next level with this app, that would ideally store text files as unique .mjor (MyJournal) files that are encrypted based on a key that the user sets.

A user would only have to keep up with their keys, and use those keys to open certain files.

There are four files that encompass the MyJournal desktop app (at this time):

* HeavyEncryption - .java file that uses the javax.crypto resources to perform encryptions and decryptions in AES-256, when given a key.

* JournalWindow - .java file that holds the bulk of the app. Creates the main screen and the window for opening and saving files.

* OpenFileButton - .java file that holds a function that returns an ActionEvent for opening .mjor files.

* SaveFileButton - .java file that holds a function that returns an ActionEvent for saving .mjor files.

Below will include descriptions of each file, their purpose, and the nature of the functions within them.

## HeavyEncryption.java

### encrypt

 * Receives a string and returns a new string encryped in AES-256 bit, using the secretKey and salt attributes of the AES class.
 
### decrypt

 * Receive an AES-256 bit encrypted string and returns a decrypted string, using the secretKey and salt attributes of the AES class.
 
### convertToString

 * Receives a String representing a binary number, converts it into its original ASCII value, and returns the value casted to a String.
 
### getBinaries

 * Receives a String representing the text of a file, breaks each character into individual elements of an array, and converts its ASCII value into binary. Returns the entire String array.
 
### formEncryptedBinaries

 * Receives an unencrypted message. Uses the getBinaries method to receive each character in binary form. Encrypts each binary element using the encrypt method. Returns those encrypted binary values.

## JournalWindow.java

### journalStage

 * Receives several elements necessary for the file writer: 
   * FileChooser for opening local directory, 
   * ExtensionFilter to filter by .mjor files, 
   * TextArea for holding the text desired for the file, 

and creates the UI. Adds a menu bar with the standard File, Edit and Help Buttons (Edit is likely to be removed in future versions). Uses the OpenFileButton and SaveFileButton java files to assign action listeners to the Open and Save buttons. Sets a New button to create a new file. Sets a close event of clearing the text when the user decides to close the window. Returns this entire window as a Stage.
 
### randomCode (NOT in use, for now)

 * Uses the Random class, along with an array, to return a String representation of 7 values randomized between the numbers 0 and 9.
 
### randomKey (See SaveFileButton.java for context)

 * Creates a randomKey for encryption that returns a key with one random letter, followed by 7 numbers, randomized to numbers from 0 to 9. Returns key as a String.
 
### start

 * The required method for starting JavaFX applications. Creates a decently approachable GUI with a small introduction, along with two buttons for opening a file or creating a new file. Also uses OpenFileButton for the action listener for the Open button. Includes a New button for creating new files.
 
## SaveFileButton.java

### get

 * Method for returning the action listener for the Save button. This method receives:
 
   * The TextArea representing the journal entry,
   
   * The FileChooser for saving to the local directory,
   
   * The Stage representing the window made from the journalStage method.
   
  The action listener will ask the user if they would like to use a custom key or a key of their own choice.
  
   * If they choose to use a custom key, the user will be prompted for the key and will encrypt the text using the key and the salt (NOTE: For now, the salt is a naive "1234" until a proper way of implementing the salt can be done. See Next Steps). It will then prompt the user to save the file.
   
   * Additional note: The key is also encrypted INTO the file itself. See OpenFileButton.java's documentation for the reason for this.
   
   * Otherwise, a random key will be made using the randomKey method, and that will serve as the key for the encryption and saving process.
   
## OpenFileButon.java

### get

  Method for returning the loaction listener for the Open button. This method receives:
 
  * A boolean representing whether or not the user has been authenticated (NOT fully implemented - see Next Steps),
  
  * A FileChooser for opening the local directory,
  
  * The window created from the journalStage() method,
  
  * A TextArea representing the text for the journalStage,
  
  * The ExtensionFilter for filtering by .mjor files.
  
 The action listener will ask the user for a key to be used for decrypting the file.
 
  * The file will START to be encrypted. However, the true key is encrypted into the file. If the true key in the decryption does not match the key the user choice, it will stop decrypting and return that the key is incorrect. This is to save time decrypting a large text file that will otherwise end up being false.
  
  * Assuming the chosen key matches the true key, it will finish decrypting and display the file contents. The window will be renamed to the name of the file, and edits can be made, and saved once more.

# Version 0.0.1
 
 * Baseline inital menu, file openers, and savers have been made. Some CSS was done to create a more approachable UI, but some improvements can be made.
 
 * AES-256 encryption is applied to all .mjor files, but for the purposes of ensuring everything worked, some secure variables are unsecure (i.e. not private).
  
 
# Next Steps

 * Improvements to the CSS to make it look more appealing and vision-friendly.
 
 * Making necessary variables (authenticated, secretKey, salt) private for security purposes.
 
 * Creating set methods for the secretKey and salt for those same reasons.

 * Utilization of salt in the SaveFileButton and OpenFileButton class: Potentially, salt could be the same as the key, but unsure if it's secure to do so.
 
 * Utilization of authenticated:
 
    * authenticated is planned to be used for an email authentication. While there is already prepared code for using this through the JavaMail API:
    
      1. It is done through a Yahoo email, which can have its computation limits and would not be a long-term solution, if this were to be used by even hundreds of users.
      2. The current code, due to it being a patchwork status, does not securely store the credentials for this email. It would be better to create some form of secure email server, then connect it directly to the app. 
    * Research is ongoing for how to do this properly.
     
  * Running the app as an executable: As of now, no process has been done to make the program runnable directly on a Windows machine without using Java SE or some other compiler/IDE.
