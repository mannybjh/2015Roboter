
package org.usfirst.frc.team20.robot;


import java.io.IOException;
import java.io.InputStream;

import org.usfirst.frc.team20.robotlang.Interpreter;
import org.usfirst.frc.team20.robotlang.SyntaxException;
import org.usfirst.frc.team20.robotlang.TestInterpreter;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Robot class holding initialization
 */
public class Robot extends SampleRobot {
	
	Interpreter interpreter;
	
	private static final InputStream stream;
	private static StringBuilder next;
	
	/* static initialize an input stream to get data from the SmartDashboard
	 * and forward it to the interpreter.  This input stream will block until
	 * an executable command is passed to it when read is called.
	 */
	static{
		stream = new InputStream(){

			private boolean newcommand;
			private boolean closed;
			
			@Override public int read() throws IOException {
				if(closed)
					return -1;
				if(next.length() == 0){
					if(!SmartDashboard.getBoolean("commandread")){
						next = new StringBuilder(SmartDashboard.getString("command"));
					}else{
						SmartDashboard.putBoolean("commandread", true);
						/*
						 * Spawn a thread to monitor the commandread flag on the smart
						 * dashboard in order to notify this method when data is ready
						 * to be read.
						 */
						new Thread(() -> {
							for(;;){
								if(!SmartDashboard.getBoolean("commandread")){
									newcommand = true;
									notifyAll();
								}
								try {
									Thread.sleep(10);
								} catch (Exception e) {}
							}//for
						}).start();//Thread
						
						synchronized(Robot.class){
							while(!newcommand)
								try {
									wait();
								} catch (InterruptedException e) {}
							next = new StringBuilder(SmartDashboard.getString("command"));
						}//synchronized
					}//if-else
				}//if-else
				char nextchar = next.charAt(0);
				next.deleteCharAt(0);
				return nextchar;
			}//method read
			
			@Override public void close(){
				this.closed = true;
			}
		};//anonymous class
	}
	
    public Robot() {
    	interpreter = new TestInterpreter(10);
    }

    /**
     * 
     */
    public void autonomous() {
    	try {
    		new Thread(()->{
    			for(;;){
    				try{
    					if(!isAutonomous()){
    						stream.close();
    						return;
    					}
    				}catch(Exception ex){}//we don't care about exceptions here
    			}//for
    		});//Thread
			interpreter.interpret(stream);
		} catch (SyntaxException e) {
			SmartDashboard.putString("interpreterstatus", "Syntax Error");
		}//try-catch
    }

    /**
     * 
     */
    public void operatorControl() {
    }

    /**
     * 
     */
    public void test() {
    }
}
