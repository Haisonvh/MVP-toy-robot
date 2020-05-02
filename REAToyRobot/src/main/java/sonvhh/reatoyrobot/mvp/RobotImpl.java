/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

import java.util.HashMap;
import sonvhh.reatoyrobot.Facing;
import sonvhh.reatoyrobot.Location;
import sonvhh.reatoyrobot.Table;
import sonvhh.utilities.Constants;

/**
 * This class implement Robot interface
 * @author HaiSonVH
 */
public class RobotImpl implements Robot{

    private Table table;
    private Facing facing;
    private Location location;
    private HashMap<String,Facing> facingMap;

    public RobotImpl() {
        facingMap = new HashMap<>();
        Facing north = new Facing();
        north.setName(Constants.FACING_NORTH);
        facingMap.put(Constants.FACING_NORTH,north);
        Facing west = new Facing();
        west.setName(Constants.FACING_WEST);
        facingMap.put(Constants.FACING_WEST,west);
        Facing south = new Facing();
        south.setName(Constants.FACING_SOUTH);
        facingMap.put(Constants.FACING_SOUTH,south);
        Facing east = new Facing();
        east.setName(Constants.FACING_EAST);
        facingMap.put(Constants.FACING_EAST,east);
    }
    
    @Override
    public void place(Location location, Facing facing) throws IllegalArgumentException,NullPointerException {
        if (table == null)
            throw new NullPointerException(Constants.ERROR_NO_TABLE);
        if (facing == null)
            throw new NullPointerException(Constants.ERROR_NULL_FACING);
        if (facing.getName() == null)
            throw new NullPointerException(Constants.ERROR_INVALID_FACING_NAME);
        if (location == null)
            throw new NullPointerException(Constants.ERROR_LOCATION_INVALID);
        if (!table.validLocation(location.getX(), location.getY()))
            throw new IllegalArgumentException(Constants.ERROR_LOCATION_INVALID);
        this.facing = facing;
        this.location = location;
    }

    @Override
    public void turnLeft() throws NullPointerException {
        if (this.location == null)
            throw new NullPointerException(Constants.ERROR_NULL_LOCATION);
        this.facing = facingMap.get(this.facing.getLeftSideName());
    }

    @Override
    public void turnRight() throws NullPointerException{
        if (this.location == null)
            throw new NullPointerException(Constants.ERROR_NULL_LOCATION);
        this.facing = facingMap.get(this.facing.getRightSideName());
    }

    @Override
    public String report() {
        String response = Constants.ERROR_ROBOT_NOT_ON_TBLE;
        if (this.location != null && this.facing != null) {
            response = this.location.getX()+","+this.location.getY()+","+this.facing.getName();
        }
        return response;
    }

    @Override
    public boolean move() throws NullPointerException{
        if (this.location == null)
            throw new NullPointerException(Constants.ERROR_NULL_LOCATION);
        boolean response = false;
        //calculate next location
        Location nextStept = new Location();
        switch (facing.getName()){
            case Constants.FACING_NORTH:
                nextStept.setY(location.getY()+1);
                nextStept.setX(location.getX());
                break;
            case Constants.FACING_WEST:
                nextStept.setY(location.getY());
                nextStept.setX(location.getX()-1);
                break;
            case Constants.FACING_SOUTH:
                nextStept.setY(location.getY()-1);
                nextStept.setX(location.getX());
                break;
            case Constants.FACING_EAST:
                nextStept.setY(location.getY());
                nextStept.setX(location.getX()+1);
                break;
        }
        if (table.validLocation(nextStept.getX(), nextStept.getY())) {
            this.location = nextStept;
            response = true;
        }
        return response;
    }

    @Override
    public void setTable(Table table) throws NullPointerException{
        if (table == null)
            throw new NullPointerException(Constants.ERROR_NULL_TABLE);
        this.table = table;
    }
    
}
