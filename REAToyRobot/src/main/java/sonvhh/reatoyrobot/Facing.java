/*
 */
package sonvhh.reatoyrobot;

import sonvhh.utilities.Constants;

/**
 * This class represents the robot's facing. 
 * 
 * It has 3 fields including
 * <ul>
 * <li>name: name of this facing
 * <li>leftSideName: name of the facing in the left hand side
 * <li>rightSideName: name of the facing in the right hand side
 * </ul> 
 * so that it is easy to recognize the next facing.
 *  
 * @author HaiSonVH
 */
public class Facing {
    private String name;
    private String leftSideName;
    private String rightSideName;
 
    public String getName() {
        return name;
    }

    /**
     * Set the name of the facing and base on this name, 
     * set the name of the facing in the left hand side and right side
     * @param name name of facing which is define in Constants class    
     * @throws IllegalArgumentException when name is not is "NORTH","SOUTH","EAST", or "WEST"
     * @throws NullPointerException when name is null
     *  @see Constants
     */
    public void setName(String name) throws IllegalArgumentException,NullPointerException{
        if (name == null)
            throw new NullPointerException(Constants.ERROR_NULL_FACING_NAME);
        switch (name) {
            case Constants.FACING_EAST :
                this.leftSideName = Constants.FACING_NORTH;
                this.rightSideName = Constants.FACING_SOUTH;
                break;
            case Constants.FACING_NORTH :
                this.leftSideName = Constants.FACING_WEST;
                this.rightSideName = Constants.FACING_EAST;
                break;
            case Constants.FACING_WEST :
                this.leftSideName = Constants.FACING_SOUTH;
                this.rightSideName = Constants.FACING_NORTH;
                break;
            case Constants.FACING_SOUTH :
                this.leftSideName = Constants.FACING_EAST;
                this.rightSideName = Constants.FACING_WEST;
                break;
            default:
                throw new IllegalArgumentException(Constants.ERROR_INVALID_FACING_NAME);
        }
        this.name = name;
    }

    public String getLeftSideName() {
        return leftSideName;
    }   

    public String getRightSideName() {
        return rightSideName;
    }
}
