/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

/**
 * This interface represent the Presenter of the application which has a 
 * responsibility to receive the command from the view and update view base on 
 * the model (robot) 
 * @author HaiSonVH
 */
public interface Presenter {
    /**
     * @param model Robot which is on the table 
     * @throws NullPointerException when model is null
     */
    void setModel(Robot model) throws NullPointerException;
    
    /**
     * Set the console view which display data
     * @param view the console view which display data and receive data form user 
     * @throws NullPointerException when view is null
     */
    void setView(View view) throws NullPointerException;
    
    /**
     * This method will receive the command line entered by user.
     * The command will be analyzed and call the correct function of robot to 
     * update data 
     * @param command follow the format:
     *                PLACE X,Y,F : X and Y are the location value; F will be NORTH, EAST, SOUTH, or WEST
     *                MOVE
     *                LEFT
     *                RIGHT
     *                REPORT
     * @throws IllegalArgumentException when the command does not follow the format
     */
    void recieveCommand(String command) throws IllegalArgumentException;
}
