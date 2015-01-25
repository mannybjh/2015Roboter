/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Team 20
 * Creates a common interface for both XBox and Direct game pad devices.
 * Developed and tested with Logitech f310 game pad.
 * 
 */
public class T20GamePad {
    
    //xbox map for logitech f310
    static final int XB_BUTT_A = 1 ;
    static final int XB_BUTT_B = 2;
    static final int XB_BUTT_X = 3;
    static final int XB_BUTT_Y = 4;
    static final int XB_BUTT_LB = 5;
    static final int XB_BUTT_RB = 6;
    static final int XB_BUTT_BACK = 7;
    static final int XB_BUTT_START = 8;
    static final int XB_BUTT_LS = 9;
    static final int XB_BUTT_RS = 10;
    static final int XB_AXIS_LS_X = 1;
    static final int XB_AXIS_LS_Y = 2;
    static final int XB_AXIS_RS_X = 4;
    static final int XB_AXIS_RS_Y = 5;
    static final int XB_AXIS_TRIGGER = 3;
    static final int XB_AXIS_DPAD_X = 6; 

    //direct map for logitech f310
    static final int DR_BUTT_A = 2 ;
    static final int DR_BUTT_B = 3;
    static final int DR_BUTT_X = 1;
    static final int DR_BUTT_Y = 4;
    static final int DR_BUTT_LB = 5;
    static final int DR_BUTT_RB = 6;
    static final int DR_BUTT_BACK = 9;
    static final int DR_BUTT_START = 10;
    static final int DR_BUTT_LS = 11;
    static final int DR_BUTT_RS = 12;
    static final int DR_BUTT_RT = 8 ;
    static final int DR_BUTT_LT = 7;
    static final int DR_AXIS_LS_X = 1;
    static final int DR_AXIS_LS_Y = 2;
    static final int DR_AXIS_RS_X = 3;
    static final int DR_AXIS_RS_Y = 4;
    static final int DR_AXIS_DPAD_X = 5;
    static final int DR_AXIS_DPAD_Y = 6; 
    
    static final int JS_TYPE_XBOX = 1;
    static final int JS_TYPE_DRCT = 2;
    
    //joystick quadrents 
    static final int JS_QUAD_CTR = 0;
    static final int JS_QUAD_LR = 1;
    static final int JS_QUAD_UR = 2;
    static final int JS_QUAD_UL = 3;
    static final int JS_QUAD_LL = 4;
    
    private Joystick joystick; 
    private int jsType;
    
    private int buttA;
    private int buttB;
    private int buttX;
    private int buttY;
    private int buttLB;
    private int buttRB;
    private int buttStart;
    private int buttBack;
    private int buttLStick;
    private int buttRStick;
    private int buttLTrigger;
    private int buttRTrigger;
    private int axisLSX;
    private int axisLSY;
    private int axisRSX;
    private int axisRSY;
    private int axisDPadX;
    private int axisDPadY;
    private int axisTrigger;

    /**
     *
     */
    public double leftStickTolerance;

    /**
     *
     */
    public double rightStickTolerance;

    /**
     *
     */
    public double leftStickNeutral;

    /**
     *
     */
    public double rightStickNeutral;
    
    
    /**
     * Creates XBox and Direct controller interface.<br>
     * both interfaces will behave identically with the exception of<br> 
     * differences in Dpad Y axis and trigger axis.<br>
     * see getAxisTrigger and getAxisDpadY for specifics.<br>
     * jsType will be either <br>
     * <br>
     * GamePad.JSad.JS_TYPE_DRCT<br>
     * GamePad.JSad.JS_TYPE_XBOX<br>
     * 
     * @param jsType GamePad.JSad.JS_TYPE_...
     * @param jsIndex int index to game controller.
     */
    T20GamePad(int jsType, int jsIndex){
        
        leftStickTolerance = 0.08;
        rightStickTolerance = 0.08;
        leftStickNeutral = 180;
        rightStickNeutral = 180;
        if (jsType == JS_TYPE_XBOX){
              this.jsType = jsType;
              joystick = new Joystick(jsIndex);
              buttA = XB_BUTT_A;
              buttB = XB_BUTT_B;
              buttX = XB_BUTT_X;
              buttY = XB_BUTT_Y;
              buttLB = XB_BUTT_LB;
              buttRB = XB_BUTT_RB;
              buttStart = XB_BUTT_START;
              buttBack = XB_BUTT_BACK;
              buttLStick = XB_BUTT_LS;
              buttRStick = XB_BUTT_RS;
              buttLTrigger = 0;
              buttRTrigger = 0;
              axisLSX = XB_AXIS_LS_X;
              axisLSY = XB_AXIS_LS_Y;
              axisRSX = XB_AXIS_RS_X;
              axisRSY = XB_AXIS_RS_Y;
              axisDPadX = XB_AXIS_DPAD_X;
              axisDPadY = 0;
              axisTrigger = XB_AXIS_TRIGGER;
              return;
        }//if 
        
        if (jsType == JS_TYPE_DRCT){
            this.jsType = jsType;
            joystick = new Joystick(jsIndex);
              buttA = DR_BUTT_A;
              buttB = DR_BUTT_B;
              buttX = DR_BUTT_X;
              buttY = DR_BUTT_Y;
              buttLB = DR_BUTT_LB;
              buttRB = DR_BUTT_RB;
              buttStart = DR_BUTT_START;
              buttBack = DR_BUTT_BACK;
              buttLStick = DR_BUTT_LS;
              buttRStick = DR_BUTT_RS;
              buttLTrigger = DR_BUTT_LT;
              buttRTrigger = DR_BUTT_RT;
              axisLSX = DR_AXIS_LS_X;
              axisLSY = DR_AXIS_LS_Y;
              axisRSX = DR_AXIS_RS_X;
              axisRSY = DR_AXIS_RS_Y;
              axisDPadX = DR_AXIS_DPAD_X;
              axisDPadY = DR_AXIS_DPAD_Y;
              axisTrigger = 0;
        } 
    }
    
    /**
     * Gets the status of the game controllers A button.<br>
     * <br>
     * @return boolean True if Button A is pressed.
     */
    public boolean getButtonA(){
        return joystick.getRawButton(this.buttA);
    }
    
    /**
     * Gets the status of the game controllers B button.<br>
     * <br>
     * @return boolean True if Button B is pressed. 
     */
    public boolean getButtonB(){
        return joystick.getRawButton(this.buttB);
    }
    
    /**
     * Gets the status of the game controllers X button.<br>
     * <br>
     * @return boolean True if Button X is pressed. 
     */
    public boolean getButtonX(){
        return joystick.getRawButton(this.buttX);
    }
    
    /**
     * Gets the status of the game controllers Y button.<br>
     * <br>
     * @return boolean True if Button Y is pressed. 
     */
    public boolean getButtonY(){
        return joystick.getRawButton(this.buttY);    
    }
    
    /**
     * Gets the status of the game controllers Left Bumper button.<br>
     * <br>
     * @return boolean True if Button Left Bumper is pressed. 
     */
    public boolean getButtonLB(){
        return joystick.getRawButton(this.buttLB);
    }
    
    /**
     * Gets the status of the game controllers Right Bumper button.<br>
     * <br>
     * @return boolean True if Button Right Bumper is pressed. 
     */
    public boolean getButtonRB(){
        return joystick.getRawButton(this.buttRB);
    }
    
    /**
     * Gets the status of the game controllers Left Stick button.<br>
     * <br>
     * @return boolean True if Button Left Stick is pressed down. 
     */
    public boolean getButtonLS(){
        return joystick.getRawButton(this.buttLStick);
    }
    
    /**
     * Gets the status of the game controllers Right Stick button.<br>
     * <br>
     * @return boolean True if Button Right Stick is pressed down. 
     */
    public boolean getButtonRS(){
        return joystick.getRawButton(this.buttRStick);
    }
    
    /**
     * Gets the status of the game controllers Start button.<br>
     * <br>
     * @return boolean True if Button Start is pressed. 
     */
    public boolean getButtonStart(){
        return joystick.getRawButton(this.buttStart);
    }
    
    /**
     * Gets the status of the game controllers Back button.<br>
     * <br>
     * @return boolean True if Button Back is pressed. 
     */
    public boolean getButtonBack(){
        return joystick.getRawButton(this.buttBack);
    }
    
    /**
     * Gets the status of the game controllers DPad Left X button.<br>
     * <br>
     * @return boolean True if Button DPad Left X is pressed. 
     */
    public boolean getButtonDPadXL(){

        return(joystick.getRawAxis(this.axisDPadX) < -0.5);
    }
    
    /**
     * Gets the status of the game controllers DPad Right X button.<br>
     * <br>
     * @return boolean True if Button DPad Right X is pressed. 
     */
    public boolean getButtonDPadXR(){

        return(joystick.getRawAxis(this.axisDPadX) > .5);
    }
    /**
     * Gets the status of the game controllers DPad Left X button.<br>
     * <br>
     * @return boolean True if Button DPad Left X is pressed. 
     */
    public boolean getButtonDPadYL(){

        return(joystick.getRawAxis(this.axisDPadY) < -0.5);
    }
    
    /**
     * Gets the status of the game controllers DPad Right X button.<br>
     * <br>
     * @return boolean True if Button DPad Right X is pressed. 
     */
    public boolean getButtonDPadYR(){

        return(joystick.getRawAxis(this.axisDPadY) > .5);
    }
    
    
    /**
     * Returns the position of the Left Joystick X Axis.<br>
     * Stick to left will be -1, to right +1, 0 for center.<br>
     * <br>
     * @return double range -1 to +1 
     */
    public double getAxisLeftStickX(){
        return joystick.getRawAxis(this.axisLSX);
    }
    
    /**
     * Returns the position of the Left Joystick Y Axis.<br>
     * Stick to forward will be -1, to back +1, 0 for center.<br>
     * <br>
     * @return double range -1 to +1 
     */
    public double getAxisLeftStickY(){
        return joystick.getRawAxis(this.axisLSY);
    }
    
    /**
     * Returns the position of the right Joystick X Axis.<br>
     * Stick to left will be -1, to right +1, 0 for center.<br>
     * <br>
     * @return double range -1 to +1 
     */
    public double getAxisRightStickX(){
        return joystick.getRawAxis(this.axisRSX);
    }
    
    /**
     * Returns the position of the Right Joystick Y Axis.<br>
     * Stick to forward will be -1, to back +1, 0 for center.<br>
     * <br>
     * @return double range -1 to +1 
     */
    public double getAxisRightStickY(){
        return joystick.getRawAxis(this.axisRSY);
    }
    
    /**
     * Returns the differential position of left and right triggers.<br>
     * Left trigger only -1,<br>
     * Right trigger only +1;<br>
     * Both 0, neither 0;<br>
     * <br>
     * Only XBox controllers will give analog output from triggers.<br>
     * <br>
     * @return double range -1 to +1 (XBox) -1 or 0 or +1 (Direct)
     */
    public double getAxisTrigger(){
        boolean buttL;
        boolean buttR;
        
        
        if (jsType == JS_TYPE_XBOX){
            return joystick.getRawAxis(this.axisTrigger);
        }
        //simiulate axis for direct joystick
        buttL = joystick.getRawButton(this.buttLTrigger);
        buttR = joystick.getRawButton(this.buttRTrigger);
        if (!buttL && !buttR ) return 0.0;
        if (buttL) return 1.0;
        if (buttR) return -1.0;
        return 0.0;
    }
    
    /**
     * Returns Y Axis for Direct controllers, 0 for XBox.<br>
     * <br>
     * 
     * 
     * @return boolean -1 pressed forward, +1 for backward, 0 for none. 
     */
    public double getAxisDPadY(){
        
        
        if (jsType == JS_TYPE_XBOX){
            return 0.0;
        }
        return joystick.getRawAxis(this.axisDPadY);
    }
    
    /**
     * Get the angle 0-360� of the Left Joy Stick.<br>
     * Angle is 0-360� Counter Clockwise. 0� with stick straight back.<br>
     * leftStickNeutral will be returned <br>
     * if leftStickMagnatude is < leftStickTolerance.<br>
     * <br>
     * @return double angle 0-360�
     */
    public double getLeftStickAngle(){
        if (this.getLeftStickMagnitude() < leftStickTolerance) 
            return leftStickNeutral;
        return getAngle(this.getAxisLeftStickX(), this.getAxisLeftStickY());
    }
    
    /**
     * Get the angle 0-360� of the Right Joy Stick.<br>
     * Angle is 0-360� Counter Clockwise. 0� with stick straight back.<br>
     * rightStickNeutral will be returned<br>
     * if rightStickMagnatude is < rightStickTolerance.<br>
     * <br>
     * @return double angle 0-360�
     */
    public double getRightStickAngle(){
        if (this.getRightStickMagnitude() < rightStickTolerance) 
            return rightStickNeutral;
        return getAngle(this.getAxisRightStickX(), this.getAxisRightStickY());
    }
    
    /**
     * Gets the displacement of Left joy stick.<br>
     * <br>
     * @return double 0 to +1 
     */
    public double getLeftStickMagnitude(){
        double h, x, y;
        
        x = Math.abs(this.getAxisLeftStickX());
        y = Math.abs(this.getAxisLeftStickY());
        h = Math.sqrt((x * x) + (y * y));
        return h;
    }
    
    /**
     * Gets the displacement of Right joy stick.<br>
     * <br>
     * @return double 0 to +1 
     */
    public double getRightStickMagnitude(){
        double h, x, y;
        
        x = Math.abs(this.getAxisRightStickX());
        y = Math.abs(this.getAxisRightStickY());
        h = Math.sqrt((x * x) +  (y * y));
        return h;
    }
    
    /**
     * Gets the quadrant of left stick.<br>
     *  
     * T20GamePad.JS_QUAD_CTR (Center)<br>
     * T20GamePad.JS_QUAD_LR (Lower Right)<br>
     * T20GamePad.JS_QUAD_UR (Upper Right)<br>
     * T20GamePad.JS_QUAD_UL (Upper Left)<br>
     * T20GamePad.JS_QUAD_LL (Lower Left)<br>
     * <br>
     * Center when leftStickMagnatude < leftStickTolerance.<br>
     * <br>
     * @return int Quadrant 1-4 CCW from bottom 0 for center<br>
     */
    public int getLeftStickQuadrant(){
        double x, y;
        
        x = this.getAxisLeftStickX();
        y = this.getAxisLeftStickY();
        if (this.getLeftStickMagnitude() < this.leftStickTolerance) 
            return T20GamePad.JS_QUAD_CTR;
        if (y >= 0 && x >= 0) return T20GamePad.JS_QUAD_LR;
        if (y <= 0 && x >= 0) return T20GamePad.JS_QUAD_UR;
        if (y <= 0 && x <= 0) return T20GamePad.JS_QUAD_UL;
        if (y >= 0 && x <= 0) return T20GamePad.JS_QUAD_LL;
        return 0;
    }
    
    /**
     * Gets the quadrant of right stick.<br>
     *  
     * T20GamePad.JS_QUAD_CTR (Center)<br>
     * T20GamePad.JS_QUAD_LR (Lower Right)<br>
     * T20GamePad.JS_QUAD_UR (Upper Right)<br>
     * T20GamePad.JS_QUAD_UL (Upper Left)<br>
     * T20GamePad.JS_QUAD_LL (Lower Left)<br>
     * <br>
     * Center when rightStickMagnatude < rightStickTolerance.<br>
     * <br>
     * @return int Quadrant 1-4 CCW from bottom 0 for center<br>
     */
    public int getRightStickQuadrant(){
        double x, y;
        
        x = this.getAxisRightStickX();
        y = this.getAxisRightStickY();
        if (this.getRightStickMagnitude() < this.rightStickTolerance) 
            return T20GamePad.JS_QUAD_CTR;
        if (y >= 0 && x >= 0) return T20GamePad.JS_QUAD_LR;
        if (y <= 0 && x >= 0) return T20GamePad.JS_QUAD_UR;
        if (y <= 0 && x <= 0) return T20GamePad.JS_QUAD_UL;
        if (y >= 0 && x <= 0) return T20GamePad.JS_QUAD_LL;
        return 0;
    }
    
    private double arctan(double y, double x){
        double x2 = x*x;
        double y2 = y*y;
        double radians;
        if (y < x){
            radians = (x*y)/(x2 + (0.28125 * y2));
        }else{
            // pi/2 = 1.5707963
            radians = (1.5707963 - (x*y)/(y2 + 0.28125 * x2));
        }
        return (radians * 180 / Math.PI);
    }
    
    private double getAngle (double x, double y){
        double angle;
        double tan;
        
        if(x == 0 && y == 0 ) return 0;
        angle = this.arctan(Math.abs(y),Math.abs(x));
        if (y >= 0 && x >= 0) return 90 - angle;
        if (y <= 0 && x >= 0) return angle + 90;
        if (y <= 0 && x <= 0) return 270 - angle;
        if (y >= 0 && x <= 0) return angle + 270;
        return angle;        
    }//getAngle
}
