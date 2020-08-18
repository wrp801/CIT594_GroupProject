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

//public class ZipCodeReader {
//
//    protected String filename;
//
//    public ZipCodeReader(String name) {
//        filename = name;
//    }
//
//    List<ZipCode> zipCodes = new ArrayList<ZipCode>();
//    Scanner in = null;
//
//    public List<ZipCode> getAllZipCodes() {
//
//        try {
//            in = new Scanner(new File(filename));
//
//            while (in.hasNext()) {
//
//                String nextLine = in.nextLine();
//                String[] items = nextLine.split(" ");
//
//                int zip = Integer.parseInt(items[0]);
//                int pop = Integer.parseInt(items[1]);
//
//                zipCodes.add(new ZipCode(zip, pop));
//            }
//
//        } catch (
//                FileNotFoundException e) {
//            System.out.println("Could not open ZipCodes file.");
//            System.exit(0);
//        }
//
//        return zipCodes;
//
//    }
//
//}

