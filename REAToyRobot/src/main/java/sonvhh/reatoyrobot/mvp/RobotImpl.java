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
        north.setName(Constants.NORTH_FACING);
        facingMap.put(Constants.NORTH_FACING,north);
        Facing west = new Facing();
        north.setName(Constants.WEST_FACING);
        facingMap.put(Constants.WEST_FACING,west);
        Facing south = new Facing();
        north.setName(Constants.SOUTH_FACING);
        facingMap.put(Constants.SOUTH_FACING,south);
        Facing east = new Facing();
        north.setName(Constants.EAST_FACING);
        facingMap.put(Constants.EAST_FACING,east);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean move() throws NullPointerException{
        if (this.location == null)
            throw new NullPointerException(Constants.ERROR_NULL_LOCATION);
        boolean response = false;
        //calculate next location
        Location nextStept = new Location();
        switch (facing.getName()){
            case Constants.NORTH_FACING:
                nextStept.setY(location.getY()+1);
                nextStept.setX(location.getX());
                break;
            case Constants.WEST_FACING:
                nextStept.setY(location.getY());
                nextStept.setX(location.getX()-1);
                break;
            case Constants.SOUTH_FACING:
                nextStept.setY(location.getY()-1);
                nextStept.setX(location.getX());
                break;
            case Constants.EAST_FACING:
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
