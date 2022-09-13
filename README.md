# MyJournal
A desktop app that allows for simple text and note-taking, that can be protected with SHA-256.

The name of this app largely outlines its purposes: The MyJournal app is meant to serve as an even more well-protected personal journal.

The typical nature of keeping a locked notebook is taken to the next level with this app, that would ideally store text files as unique .mjor (MyJournal) files that are encrypted based on a key that the user sets.

A user would only have to keep up with their keys, and use those keys to open certain files.

There are four files that encompass the MyJournal desktop app (at this time):

* HeavyEncryption - .java file that uses the javax.crypto resources to perform encryptions and decryptions in SHA-256, when given a key.

* JournalWindow - .java file that holds the bulk of the app. Creates the main screen, along with the text file screens for opening and saving files.

* OpenFileButton - .java file that holds a function that returns an ActionEvent for opening .mjor files.

* SaveFileButton - .java file that holds a function that returns an ActionEvent for saving .mjor files.
