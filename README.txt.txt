This code is implemented for "Operating System" Course at Kristianstad University.

TestProcessBuilder needs to be opened as follow:
- Open CMD
- open by first typing in cmd: javac TestProcessBuilder.java
- then run the program as normal in cmd: java TestProcessBuilder

The purpose of the seminar this code is intended for is to show how the file system works for an operating system and also showcase input/output-operations.

For task 1 - Had to implement filedump method and exception handling 
For task 2 - Had to implement copyfile method and exception handling 
For task 3 - Free choice of what command to implement and exception handling

For task 3 I decided to implement the delete command. It does the following:
- Checks if there are enough arguments in the command line (so if it was written as: 'the command + the file name you want to delete)
- Checks if the input has a command and nameofafile by checking the size of the input. If not, it returns an error.
- Gets the file name to delete from the 1st position in the command line. The command is at 0 position
- It then creates an instance of the file name called file.
- It then checks that the file exists with "exists" and if yes it goes ahead and deletes the file with the "delete"
- If successful, prints a confirmation.
- If not, exception handling for if the file cant be deleted or if the file doesnt exist.

