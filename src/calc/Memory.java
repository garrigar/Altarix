package calc;

import java.io.*;
import java.util.ArrayList;

/**
 * Class for work with calculator's memory
 */
public class Memory implements Closeable {

    private ArrayList<String> items;
    private File file;
    private BufferedWriter bw;

    /**
     * Creates memory instance with given file to work
     * @param filename - filename
     * @throws IOException
     */
    public Memory(String filename) throws IOException {
        items = new ArrayList<String>();
        file = new File(filename);
        if (file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null){
                items.add(line);
            }
            br.close();
        } else {
            file.createNewFile();
        }
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Add record
     * @param s - string to record
     * @throws IOException
     */
    public void record(String s) throws IOException {
        items.add(s);
        bw.write(s);
        bw.newLine();
    }

    /**
     * Correct closing
     */
    @Override
    public void close() {
        try {
            bw.close();
        } catch (IOException ioe) {}
    }

    /**
     * Clears the memory
     * @throws IOException
     */
    public void clear() throws IOException {
        bw.close();
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file));
        bw1.write("");
        bw1.close();
        items.clear();
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Get item off from last by given index
     * @param index - index
     * @return - needed item
     * @throws IndexOutOfBoundsException
     */
    public String getItemOffFromLast(int index) throws IndexOutOfBoundsException {
        if (index + 1 > items.size()) {
            throw new IndexOutOfBoundsException();
        }
        return items.get(items.size() - 1 - index);
    }

}
