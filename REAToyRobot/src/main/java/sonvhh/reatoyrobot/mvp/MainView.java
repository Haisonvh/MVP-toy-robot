/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

import java.util.Scanner;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class MainView implements View{
    private Presenter presenter;
    
    @Override
    public void setPresenter(Presenter presenter) throws NullPointerException{
        if (presenter == null)
            throw new NullPointerException(Constants.ERROR_NULL_PRESENTER);
        this.presenter = presenter;
    }

    @Override
    public void updateView(String data) throws NullPointerException{
        if (data == null || data.trim().equals(""))
            throw new NullPointerException(Constants.ERROR_NULL_SHOWING_DATA);
        System.out.println(data);
    }

    @Override
    public void startListen() throws NullPointerException{
        if (presenter == null)
            throw new NullPointerException(Constants.ERROR_NULL_PRESENTER);
        //clear the console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        
        //print the welcome and instruction
        System.out.println("Welcome to Toy Robot simulater");
        System.out.println("------------------------------");
        System.out.println("Using the command line to control the robot:");
        System.out.println("\t-PLACE X,Y,F\twith X,Y is location, F is NORTH, WEST, SOUTH or EAST");
        System.out.println("\t-MOVE \t\tmove robot 1 point forward");
        System.out.println("\t-LEFT \t\trotate robot 90 degrees to the left hand side");
        System.out.println("\t-RIGHT \t\trotate robot 90 degrees to the right hand side");
        System.out.println("\t-REPORT \tget the current attributes of the robot");
        System.out.println("\t-EXIT \t\tExit the simulater");
        System.out.println("------------------------------");
        
        String command = "";
        do {
            System.out.print(">>");
            command = scanner.nextLine();
            presenter.recieveCommand(command);
        } while (!command.equals("EXIT")); 
        
    }
    
}
