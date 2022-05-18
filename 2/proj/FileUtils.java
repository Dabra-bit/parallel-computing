package proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileUtils {
    private static final String FILE_PATH = "/Users/dberrosp/ceti/parallel-computing/2/proj/IPs.txt";

    public static BufferedReader getReadableFile() {
        BufferedReader bufferedReader = null;
        File file = new File(FILE_PATH);

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bufferedReader;
    }
}
