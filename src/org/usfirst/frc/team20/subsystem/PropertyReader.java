package org.usfirst.frc.team20.subsystem;

import java.io.Reader;
import java.util.Map;

public interface PropertyReader {
	
	Map.Entry<String, Object> getNextProperty();
	
	void setReader(Reader reader);
}
