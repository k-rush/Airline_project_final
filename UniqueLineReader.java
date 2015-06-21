package rushairdatagen;
import java.io.*;
import java.util.*;

public class UniqueLineReader extends BufferedReader {
    Set<String> lines = new HashSet<String>();

    public UniqueLineReader(Reader arg0) {
        super(arg0);
    }

    @Override
    public String readLine() throws IOException {
        String uniqueLine;
        while (lines.add(uniqueLine = super.readLine()) == false) { System.out.println("finding uniqueline");  } //read until encountering a unique line
            return uniqueLine;
    }

}