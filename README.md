# MyJournal
A desktop app that allows for simple text and note-taking, that can be protected with SHA-256 bit encryption.

The name of this app largely outlines its purposes: The MyJournal app is meant to serve as an even more well-protected personal journal.

The typical nature of keeping a locked notebook is taken to the next level with this app, that would ideally store text files as unique .mjor (MyJournal) files that are encrypted based on a key that the user sets.

A user would only have to keep up with their keys, and use those keys to open certain files.

There are four files that encompass the MyJournal desktop app (at this time):

* HeavyEncryption - .java file that uses the javax.crypto resources to perform encryptions and decryptions in SHA-256, when given a key.

* JournalWindow - .java file that holds the bulk of the app. Creates the main screen, along with the text file screens for opening and saving files.

* OpenFileButton - .java file that holds a function that returns an ActionEvent for opening .mjor files.

* SaveFileButton - .java file that holds a function that returns an ActionEvent for saving .mjor files.

Below will include descriptions of each file, their purpose, and the nature of the functions within them.

## HeavyEncrpytion.java

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

This file handles all of the encryption and decryption of text in files using SHA-256 bit encryption. The method of encrypting and decrypting the text is as follows:

# Version 0.1
 
 * Baseline inital menu, file openers, and savers have been made. Some CSS was done to create a more approachable UI, but some improvements can be made.
 
 * SHA-256 encryption is applied to all .mjor files, but for the purposes of ensuring everything worked, some secure variables are unsecure (i.e. not private).
 
 * 
