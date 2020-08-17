package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

public class PropertyReader implements Reader {
    protected String filename;

    public PropertyReader(String filename) {
        this.filename = filename;
    }

    // helper function to find the position of a given column name in the first row
private int findPosition(String colname,String filename) throws IOException {
        int res = 0;
        BufferedReader reader = null;
        FileReader f = new FileReader(this.filename);
        reader = new BufferedReader(f);
        String line = reader.readLine();
        String[] lineContents = line.split(",");
        for (String s: lineContents) {
            if (s.equalsIgnoreCase(colname)) {
                return res;
            }
            res++;
        }
        reader.close();
        return -1;
}
    @Override
    public HashMap<Integer,Property> read() throws ParseException, java.text.ParseException, IOException {
        HashMap<Integer, Property> ret_map = new HashMap<>();
        HashMap<String,Integer> colPosition = new HashMap<>();
        ArrayList<String> colNames = new ArrayList<>();
        colNames.add("zip_code");
        colNames.add("total_livable_area");
        colNames.add("market_value");
        colNames.add("building_code");
        for (String s:colNames) {
            int pos = findPosition(s,this.filename);
            colPosition.put(s,pos);
        }

        BufferedReader reader = null;
        FileReader f = new FileReader(this.filename);
        int linenum = 0;
        Pattern pattern = Pattern.compile("[A-z]",Pattern.CASE_INSENSITIVE);
        try {
            String line;
            reader = new BufferedReader(f);
            while ((line = reader.readLine())!=null) {
                if (linenum == 0) {
                    linenum++;
                    continue;
                }
                String [] lineData = line.split(",");
                // Get the zipcode
                String zipStr = lineData[colPosition.get("zip_code")].trim();
                Matcher matcher = pattern.matcher(zipStr);
                if (matcher.find()) continue; // skip if there are any alpha characters -- todo make zero instead of skipping
                if (zipStr.equals("")) continue; // skip if null -- todo revisit, maybe we don't want to chuck; set to zero instead
                int strLen = zipStr.length();
                String zip_substr = strLen < 5 ? zipStr.substring(0,strLen): zipStr.substring(0,5);
                int zip = Integer.parseInt(zip_substr.trim());
                // Get the total livable area
                String tlaStr = lineData[colPosition.get("total_livable_area")].trim();
                matcher = pattern.matcher(tlaStr);
                if (matcher.find()) continue;
                if (tlaStr.equals("")) continue; // skip if null -- todo this needs to be set null or negative 1 instead
                double tla = Double.parseDouble(tlaStr);
                // Get the market value
                String marketStr = lineData[colPosition.get("market_value")].trim();
                matcher = pattern.matcher(marketStr);
                if (matcher.find()) continue;
                if (marketStr.equals("")) continue; //skip if null -- todo this needs to be set null or negative 1 instead
                double market_value = Double.parseDouble(marketStr);
                // Get the building code
                String building_code = lineData[colPosition.get("building_code")].trim();
                Property p = new Property(market_value,tla,zip,building_code);
                ret_map.put(linenum,p);
                linenum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret_map;
    }

}

