# MyJournal
A desktop app that allows for simple text and note-taking, that can be protected with AES-256 bit encryption.

The name of this app largely outlines its purposes: The MyJournal app is meant to serve as an even more well-protected personal journal.

The typical nature of keeping a locked notebook is taken to the next level with this app, that would ideally store text files as unique .mjor (MyJournal) files that are encrypted based on a key that the user sets.

A user would only have to keep up with their keys, and use those keys to open certain files.

The following are the files that encompass the MyJournal desktop app (at this time, not including .css files):

* HeavyEncryption - .java file that uses the javax.crypto resources to perform encryptions and decryptions in AES-256, when given a key.

* JournalApp - .java file that holds the initializer for the app. Starts the app and creates the main screen.

* JournalStage - .java file that holds a function that returns the editor for .mjor files.

* OpenFileButton - .java file that holds a function that returns an ActionEvent for opening .mjor files.

* SaveFileButton - .java file that holds a function that returns an ActionEvent for saving .mjor files.

# Version 0.0.1 - The Baseline
 
 * Baseline inital menu, file openers, and savers have been made. Some CSS was done to create a more approachable UI, but some improvements can be made.
 
 * AES-256 encryption is applied to all .mjor files, but for the purposes of ensuring everything worked, some secure variables are unsecure (i.e. not private).
 
# Version 0.0.2 - Security Improvements and Clarifications
 
 * AES class now has the salt and secretKey methods private, for secure best practice.
 
 * New .java file called JournalStage.java that will be creating the window for opening and saving files. This will be used by other files in the future.
 
 * JournalWindow.java has been renamed to *JournalApp.java* for clarity. 
 
 # Version 0.0.4 - The CSS Update
 
 * New fancy, yet viewable color scheme for the main menu, along with a color scheme for a dark version
 * All buttons, menus, and file openers receive their styles from .css files through JavaFX.
 
 # Version 0.0.5 - Configuration File
 
  * New configurations.txt file that saves certain information about the app and other settings. At the moment, it only holds the last time the program was opened and whether or not the app was in dark mode prior to the app being closed.
 
# Next Steps
 
 * A Settings window for some basic color and font changes (dark mode, to start)
 
 * Email authentication:
 
    * MyJournal is planned to include an email authentication. While there is already prepared code for using this through the JavaMail API:
    
      1. It is done through a Yahoo email, which can have its computation limits and would not be a long-term solution, if this were to be used by even hundreds of users.
      2. The current code, due to it being a patchwork status, does not securely store the credentials for this email. It would be better to create some form of secure email server, then connect it directly to the app. 
    * Research is ongoing for how to do this properly.
     
  * Running the app as an executable: As of now, no process has been done to make the program runnable directly on a Windows machine without using Java SE or some other compiler/IDE.
