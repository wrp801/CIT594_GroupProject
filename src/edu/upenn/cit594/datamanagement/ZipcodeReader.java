package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.data.Zipcode;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.ZipOutputStream;

public class ZipcodeReader implements Reader {

    protected String filename;

    public ZipcodeReader(String filename) {
        this.filename = filename;
    }


    /**
     * Reads the zipcode and population data from the text file
     * @return A hashmap with a row number and a zipcode object
     */
    @Override
    public HashMap<Integer, Zipcode> read() {
        HashMap<Integer, Zipcode> ret_map = new HashMap<>();
        try {
            BufferedReader reader = null;
            FileReader f = new FileReader(this.filename);
            String line;
            reader = new BufferedReader(f);
            while ((line = reader.readLine())!=null) {
                String[] lineData = line.split(" ");
                int zip = Integer.parseInt(lineData[0]);
                int pop = Integer.parseInt(lineData[1]);
                ret_map.put(zip, new Zipcode(zip, pop));
            }
        }
        catch (IOException e ) {
            System.out.println("The file " + this.filename + " provided could not be found\n");
            System.exit(1);
        }
        return ret_map;
    }

}


