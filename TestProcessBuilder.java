import java.io.*;
import java.util.*;

public class TestProcessBuilder {

    static void createProcess(String command) throws java.io.IOException {

        List<String> input = Arrays.asList(command.split(" "));
        //Makes it so that it nows position 0 is a command
        if (input.get(0).equalsIgnoreCase("filedump")) {
            // TASK 1
            filedump(input);
        } else if (input.get(0).equalsIgnoreCase("copyfile")) {
            // TASK 2
            copyfile(input);
        } else if (input.get(0).equalsIgnoreCase("delete")) {
            // TASK 3
            delete(input);
        }else {
            ProcessBuilder processBuilder = new ProcessBuilder(input);
            BufferedReader bufferReader = null;
            try {

                Process proc = processBuilder.start();
                InputStream inputStream = proc.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                bufferReader = new BufferedReader(isr);

                String line;
                while ((line = bufferReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (java.io.IOException ioe) {
                System.err.println("Error");
                System.err.println(ioe);
            } finally {
                if (bufferReader != null) {
                    bufferReader.close();
                }
            }
        }
    }
    static void filedump(List<String> input) throws IOException {
        //Check if the input has two arguments (commands + filename)
        if (input.size() != 2) {
            System.err.println("Invalid");
            return;
        }

        //get the filename in last position and create object
        String filename = input.get(1);
        File file = new File(filename);

        //check if file exists.
        if (!file.exists() || !file.isFile()) {
            System.err.println("File does not exist");
            return;
        }

        //Open and read file
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // not able to read file
            System.err.println("Unable to read file");
        }
    }

    static void copyfile(List<String> input) throws IOException {
        // Check if the input has three arguments (command + source filename + destination filename)
        if (input.size() != 3) {
            System.err.println("Invalid");
            return;
        }

        // read the files from command
        String nameOfFile = input.get(1);
        String nameOfCopiedFile = input.get(2);

        // Create File objects for the files
        File sourceFile = new File(nameOfFile);
        File destinationFile = new File(nameOfCopiedFile);

        // Check if the source file exists
        if (!sourceFile.exists()) {
            System.err.println("Original file does not exist.");
            return;
        }

        // Copy the content of the source file to the destination file
        try (BufferedReader fileReader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(destinationFile))) {

            String line;
            while ((line = fileReader.readLine()) != null) {
                fileWriter.write(line);
                fileWriter.newLine(); // Add newline to separate lines
            }
            System.out.println("File copied.");
        } catch (IOException e) {
            // Handle errors if file is not successful
            System.err.println("File not copied.");
        }
    }
    static void delete(List<String> input) {
        // Check if the input has two arguments (command + filename)
        if (input.size() != 2) {
            System.err.println("Invalid");
            return;
        }

        // Get the filename in the last position and create an object
        String filename = input.get(1);
        File file = new File(filename);

        // Check if the file exists and attempt to delete it otherwise
        //throw exception
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted");
            } else {
                System.err.println("Can't delete file");
            }
        } else {
            System.err.println("File does not exist");
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        String commandLine;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n***** Welcome to the Java Command Shell *****");
        System.out.println("If you want to exit the shell, type END and press RETURN.\n");
        System.out.println("Try: 'filedump + name of file you want to open'.");
        System.out.println("Try: 'copyfile + name of file you want to copy + name of copied file'.");
        System.out.println("Try: 'delete + name of file you want to delete.");

        while (true) {
               System.out.print("jsh>");
            commandLine = scanner.nextLine();
            // if user entered a return, just loop again
            if (commandLine.equals("")) {
                continue;
            }
            if (commandLine.toLowerCase().equals("end")) { //User wants to end shell
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                scanner.close();
                System.exit(0);
            }
            createProcess(commandLine);
        }   
    }
   
}
