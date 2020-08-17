package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.Violation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class ViolationReader implements Reader{
    protected String filename;
    protected String filetype;

    public ViolationReader(String filename,String filetype) {
        this.filename = filename;
        this.filetype = filetype;
    }

    // helper function to parse JSON data
    private HashMap<Integer, Violation> readJson() throws IOException, ParseException, java.text.ParseException {
        HashMap<Integer,Violation> ret_map = new HashMap<>();

        BufferedReader reader = null;
        FileReader f = new FileReader(this.filename);
        String line;
        int linenum = 1;
        reader = new BufferedReader(f);
        JSONArray ja = (JSONArray) new JSONParser().parse(f);
        //SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DDThh:mm:ssZ");

        Iterator itr = ja.iterator();

        while (itr.hasNext()) {
            JSONObject jo = (JSONObject) itr.next();
            int ticket_number = Integer.parseInt(jo.get("ticket_number").toString());
            int plateid = Integer.parseInt((String) jo.get("plate_id"));
            String dateString = (String) jo.get("date");
            //String date = dt.parse(dateString);
            String zip_str = (String)jo.get("zip_code");
            int zip = zip_str.equals("") ? 0 : Integer.parseInt(zip_str);
            String description = (String) jo.get("violation");
            int fine = Integer.parseInt(jo.get("fine").toString());
            String state = (String) jo.get("state");
            Violation v = new Violation(dateString,fine,description,plateid,state,ticket_number,zip);
            ret_map.put(linenum,v);
            linenum++;
        }
        return ret_map;
    }

    // helper function to read in the csv file
    private HashMap<Integer,Violation> readCSV() throws FileNotFoundException {
        HashMap<Integer,Violation> ret_map = new HashMap<>();
        BufferedReader reader = null;
        FileReader f = new FileReader(this.filename);
        SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");

        int linenum = 1;
        try {
            String line;
            reader = new BufferedReader(f);
            int zipcode = 0;
            while ((line = reader.readLine())!=null) {
                String [] lineData = line.split(",");
                String dateString = lineData[0];
                //String date = dt.parse(dateString);
                int fine = Integer.parseInt(lineData[1]);
                String description = lineData[2];
                int plateId = Integer.parseInt(lineData[3]);
                String state = lineData[4];
                int ticketNumber = Integer.parseInt(lineData[5]);
                // set the zipcode to zero if it is missing in the file
                zipcode = lineData.length < 7 ? 0 : Integer.parseInt(lineData[6]);
                //int zipcode = Integer.parseInt(lineData[6]);
                Violation v = new Violation(dateString,fine,description,plateId,state,ticketNumber,zipcode);
                ret_map.put(linenum,v);
                linenum++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return ret_map;
    }


    //todo need to account for missing data in both read functions
    /**
     * Reads in either a JSON or CSV file based on the filetype argument when instantiated
     * @return a hashmap containing the row number and a Violation Object per row
     * @throws ParseException
     * @throws java.text.ParseException
     * @throws IOException
     */
    @Override
    public HashMap<Integer, Violation> read() throws ParseException, java.text.ParseException, IOException {
        HashMap<Integer,Violation> ret_map = new HashMap<>();
        if (this.filetype.equalsIgnoreCase("json")) {
            ret_map = readJson();
        }
        else if (this.filetype.equalsIgnoreCase("csv")) {
            ret_map = readCSV();
        }
        else {
            System.out.println("Incorrect file type. Please Enter csv or json");
        }

        return ret_map;
    }
}
