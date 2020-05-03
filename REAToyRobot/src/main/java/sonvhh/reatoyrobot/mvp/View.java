/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

/**
 * This interface represent the view of application
 * Display information and interact with user
 * @author HaiSonVH
 */
public interface View {
    /**
     * Set the Presenter for the View
     * @param presenter 
     * @throws NullPointerException when the presenter is null;
     */
    public void setPresenter(Presenter presenter) throws NullPointerException;
    
    /**
     * This method will allow view to print information on the console.
     * This data can be announcement or the report of the robot.
     * @param data which need to print on the console
     * @throws NullPointerException when data is null or equals "";
     */
    public void updateView(String data) throws NullPointerException;
    
    /**
     * This method allow view start to listen input from the user until user type 'EXIT'.Before starting listen, the view will print the welcome and also instruction.
     * This listener will be simulated with a simple while loop
     */
    public void startListen();
}
