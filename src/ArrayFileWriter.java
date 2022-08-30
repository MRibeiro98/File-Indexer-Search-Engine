import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.List;

//class to write results to a file
public class ArrayFileWriter {

	//creates new .txt file
    final static String USER_DIR = System.getProperty("user.dir") + "\\IO";
    private String file;

    public ArrayFileWriter(String fileName) {
        this(fileName, USER_DIR);
    }
    public ArrayFileWriter(String fileName, String baseDirectory) {
        this.file = baseDirectory + "\\" + fileName;
    }
    
    public void writeLines(List<String> lines) {
    	//write contents of an array list to a specified file
        try {
        	BufferedWriter buWriter = new BufferedWriter(new FileWriter(this.file, true));
            for (String line : lines) {
            	buWriter.write(line);
            	buWriter.newLine();
            }
            buWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void wipeFile() {
        try {
            PrintWriter writer = new PrintWriter(new File(this.file));
            writer.print("");
            writer.close();
        } catch (IOException e) {
            // if file doesn't exist, silently ignore it
        }
    }   
}