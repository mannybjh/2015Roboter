package org.usfirst.frc.team20.subsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InlinePropertyReader extends Reader implements PropertyReader {

	private BufferedReader reader;
	private final String delimiter;
	private final Parser parser;

	public InlinePropertyReader(String delimiter, Parser parser) {
		this.delimiter = delimiter;
		this.parser = parser;
	}
	
	@Override public void setReader(Reader reader){
		this.reader = new BufferedReader(reader);
	}

	@Override public Entry<String, Object> getNextProperty() {
		if(reader == null)
			return null;
		String line;
		try {
			if ((line = reader.readLine()) == null)
				return null;
		} catch (IOException e) {
			Logger.getGlobal()
				.log(Level.SEVERE, "FAILED ON READ OF PROPERTIES");
			return null;
		}
		String[] keyVal = line.split(delimiter);
		if (keyVal.length != 2)
			return null;
		return new AbstractMap.SimpleEntry<String, Object>(keyVal[0].trim(),
			parser.parseValue(keyVal[1].trim()));
	}

	@Override public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf, off, len);
	}

	@Override public void close() throws IOException {
		reader.close();
	}
}
