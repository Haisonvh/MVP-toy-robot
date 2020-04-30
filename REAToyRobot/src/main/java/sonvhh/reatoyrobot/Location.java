/*
 */
package sonvhh.reatoyrobot;

/**
 * This class represents the location (or point) on the table. 
 * This will be used by the robot to store its location.
 * <p>
 * The location will be initialized with the point x=-1 and y=-1. This mean this location is not on the table
 * </p>
 * 
 * @author HaiSonVH
 */
public class Location {
    private int x;
    private int y;

    public Location(){
        x = -1;
        y = -1;
    }
    
    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
