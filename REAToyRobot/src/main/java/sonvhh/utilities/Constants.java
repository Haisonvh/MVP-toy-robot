/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.utilities;

/**
 *
 * @author HaiSonVH
 */
public final class Constants {
    public static final String FACING_NORTH = "NORTH";
    public static final String FACING_SOUTH = "SOUTH";
    public static final String FACING_EAST = "EAST";
    public static final String FACING_WEST = "WEST";
    
    public static final String COMMAND_PLACE = "PLACE";
    public static final String COMMAND_LEFT = "LEFT";
    public static final String COMMAND_RIGHT = "RIGHT";
    public static final String COMMAND_MOVE = "MOVE";
    public static final String COMMAND_REPORT = "REPORT";
    public static final String COMMAND_EXIT = "EXIT";
    
    //error message
    public static final String ERROR_INVALID_FACING_NAME = "This facing name is invalid";
    public static final String ERROR_NULL_FACING_NAME = "Name cannot be null";
    public static final String ERROR_NEGATIVE_VALUE = "Value must be greater than 0";
    public static final String ERROR_LOCATION_INVALID = "The location is out of table";    
    public static final String ERROR_NO_TABLE = "The table is not set up";
    public static final String ERROR_NULL_TABLE = "The table is not null";
    public static final String ERROR_NULL_LOCATION = "Please place robot on table first";
    public static final String ERROR_NULL_FACING = "Facing is null";
    public static final String ERROR_ROBOT_NOT_ON_TBLE = "The robot is not on the table"; 
    public static final String ERROR_NULL_PRESENTER = "The presenter is null";
    public static final String ERROR_NULL_SHOWING_DATA = "The data cannot be null or ''";
    public static final String ERROR_NULL_COMMANDLINE = "The command line cannot be null or ''";
    public static final String ERROR_NULL_MODEL = "Model cannot be null";
    public static final String ERROR_NULL_VIEW = "View cannot be null";
    public static final String ERROR_INVALID_COMMANDLINE = "The entered command line cannot be found";
    
    //annoucement message
    public static final String NOTICE_MOVE_SUCCESSFULLY = "The robot step forward successfully";
    public static final String NOTICE_MOVE_UNSUCCESSFULLY = "Next step is not safe";
    
}
