/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

import org.apache.commons.lang.math.NumberUtils;
import sonvhh.reatoyrobot.Facing;
import sonvhh.reatoyrobot.Location;
import sonvhh.reatoyrobot.Table;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class PresenterImpl implements Presenter {

    private Robot model;
    private View view;

    @Override
    public void setModel(Robot model) {
        if (model == null) {
            throw new NullPointerException(Constants.ERROR_NULL_MODEL);
        }
        Table table = new Table();
        this.model = model;
        this.model.setTable(table);
    }

    @Override
    public void setView(View view) {
        if (view == null) {
            throw new NullPointerException(Constants.ERROR_NULL_VIEW);
        }
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void recieveCommand(String command) {
        String commandName = validateCommand(command);
        if (commandName == null) {
            view.updateView(Constants.ERROR_INVALID_COMMANDLINE);
        } else {
            switch (commandName) {
                case Constants.COMMAND_PLACE:
                    //parser to get parameters
                    String[] commandElements = command.trim().split(" ");
                    String[] params = commandElements[1].split(",");
                    Location location = new Location();
                    location.setX(Integer.parseInt(params[0]));
                    location.setY(Integer.parseInt(params[1]));
                    Facing facing = new Facing();
                    facing.setName(params[2]);
                    model.place(location, facing);
                    break;                
                case Constants.COMMAND_LEFT:
                    try {
                        model.turnLeft();
                    } catch (NullPointerException ex){
                        view.updateView(ex.getMessage());
                    }                    
                    break;
                case Constants.COMMAND_MOVE:
                    try {
                        boolean response = model.move();
                        if (response)
                            view.updateView(Constants.NOTICE_MOVE_SUCCESSFULLY);
                        else
                            view.updateView(Constants.NOTICE_MOVE_UNSUCCESSFULLY);
                    } catch (NullPointerException ex){
                        view.updateView(ex.getMessage());
                    }
                case Constants.COMMAND_REPORT:
                    try {
                        String response = model.report();
                        view.updateView(response);
                    } catch (NullPointerException ex){
                        view.updateView(ex.getMessage());
                    }
                case Constants.COMMAND_RIGHT:
                    try {
                        model.turnRight();
                    } catch (NullPointerException ex){
                        view.updateView(ex.getMessage());
                    }
                    break;
                case Constants.COMMAND_EXIT:
                    break;
            }
        }
    }

    /**
     * This function is to validate the command line which is receive from view
     *
     * @param command the command need to be validated
     * @return command type of the command, if return null mean the input
     * command is invalid
     */
    public String validateCommand(String command) {
        String response = null;
        //parser and checking the valid of the command line
        if (command != null) {
            String[] commandElements = command.trim().split(" ");
            if (commandElements.length == 2) {
                switch (commandElements[0]) {
                    case Constants.COMMAND_PLACE:
                        if (commandElements.length == 2) {
                            String[] params = commandElements[1].split(",");
                            if (params.length == 3
                                    && NumberUtils.isNumber(params[0])
                                    && NumberUtils.isNumber(params[1])) {
                                if (params[2].equals(Constants.FACING_EAST)
                                        || params[2].equals(Constants.FACING_NORTH)
                                        || params[2].equals(Constants.FACING_SOUTH)
                                        || params[2].equals(Constants.FACING_WEST)) {
                                    response = Constants.COMMAND_PLACE;
                                }
                            }
                        }
                        break;
                }
            } else if (commandElements.length == 1) {
                switch (commandElements[0]) {
                    case Constants.COMMAND_EXIT:
                    case Constants.COMMAND_LEFT:
                    case Constants.COMMAND_MOVE:
                    case Constants.COMMAND_REPORT:
                    case Constants.COMMAND_RIGHT:
                        response = commandElements[0];
                        break;
                }
            }
        }
        return response;
    }
}
