import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;;

public class FileHelper {

    //Takes a folder path as a parameter and creates a File object out of it, this File object is a directory,
    // so it is converted to an Arraylist holding File objects.
    public ArrayList<File> getFileDirectory(String fileDir) {
        File folderToRead = new File(fileDir);

        ArrayList<File> fileList = new ArrayList<File>();
        Collections.addAll(fileList, folderToRead.listFiles());

        return fileList;
    }

    //A File object is used to create a Scanner object to read from. The 'try' in this case means it will "try"
    //to create a Scanner from the File object. and if the "try" fails, it will be "caught" and we will print our error,
    //FileNotFoundException.
    public Scanner getFileScanner(File fileToRead) {
        try {
            Scanner fileScanner = new Scanner(fileToRead);
            return fileScanner;
        } catch (FileNotFoundException error) {
            System.out.println("The file was unable to be located.");
            error.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}