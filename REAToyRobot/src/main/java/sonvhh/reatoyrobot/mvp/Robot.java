/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

import sonvhh.reatoyrobot.Facing;
import sonvhh.reatoyrobot.Location;
import sonvhh.reatoyrobot.Table;


/**
 * This interface represent behavior of a toy robot including
 * <ul>
 * <li>place
 * <li>move
 * <li>turn left
 * <li>turn right
 * <li>report
 * </ul>
 * @author HaiSonVH
 */
public interface Robot {
    /**
     * This method use to place or re-place the position of the robot.
     * After validate the location and facing, these data will be assigned to robot's attributes 
     * @param location the location point on the table
     * @param facing facing of the robot which must have name in list: "SOUTH","WEST","NORTH","EAST" 
     * @throws NullPointerException  when "no table", "location is null", or "facing is null"
     * @throws IllegalArgumentException  when "location is invalid"
     * @see Constants to know the value of facing's name.
     */
    public void place(Location location,Facing facing) throws IllegalArgumentException,NullPointerException;
    
    /**
     * This method will rotate the robot 90 degrees to the left hand side.
     * Before changing, the robot will check is it on the table or not via checking location
     * The facing of the robot after call this method will be change following 
     * the sequence:NORTH - WEST - SOUTH - EAST - NORTH
     * @throws NullPointerException  when "location is null"
     */
    public void turnLeft() throws NullPointerException ;
    
    /**
     * This method will rotate the robot 90 degrees to the right hand side.
     * Before changing, the robot will check is it on the table or not via checking location
     * The facing of the robot after call this method will be change following 
     * the sequence:SOUTH - WEST - NORTH - EAST - SOUTH
     * @throws NullPointerException  when "location is null"
     */
    public void turnRight() throws NullPointerException ;
    
    /**
     * This method will ask robot report its current attributes.
     * The report will be follow a string format "x,y,facing name".
     * Example: 0,2,NORTH
     * 
     * @return the current attributes of the robot with string format
     */
    public String report();
    
    /**
     * This method will command robot step one unit forward.
     * Before the robot step forward, it will check the current location so that
     * it is on the table, and also check the next step is safe or not by 
     * calling the valid location functions provide by table. It the next 
     * location (next step) is valid, it will step forward and vice versa it will
     * ignore the command.
     * @return the status of move command; true if successfully step forward
     * @throws NullPointerException  when "location is null"
     */
    public boolean move() throws NullPointerException ;
    
    /**
     * This method use to set the table where robot will be place on.
     * @param table the table which robot is on.
     * @throws NullPointerException  when "table is null"
     * @see Table
     */
    public void setTable(Table table)throws NullPointerException ;
}
