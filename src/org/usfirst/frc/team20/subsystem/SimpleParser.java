package org.usfirst.frc.team20.subsystem;


public class SimpleParser implements Parser {

	@Override public Object parseValue(String value) {
		try{
			return Integer.decode(value);
		}catch(NumberFormatException ex){}
		try{
			Double.parseDouble(value);
		}catch(NumberFormatException ex){}
		
		/*recursively parse an array of objects given a string (<arg 1>,<arg 2>,...<arg n>)
		Note that commas are not escaped so values in an array cannot contain commas.
		Implement a more complex parser if you really need that feature.  Seriously, there's a lexer
		like, right there. Right there.*/
		if(value.startsWith("(") && value.endsWith(")")){
			String[] args = value.substring(1, value.length() - 1).split(",");
			Object[] argv = new Object[args.length];
			for(int i = 0; i < args.length; ++i){
				argv[i] = this.parseValue(args[i]);
			}
			return argv;
		}
		return value;
	}
}
