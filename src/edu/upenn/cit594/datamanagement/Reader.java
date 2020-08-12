package edu.upenn.cit594.datamanagement;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public interface Reader<E> {

    public HashMap<Integer,E> read() throws ParseException, java.text.ParseException, IOException;

}
