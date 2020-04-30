/*
 * 
 */
package sonvhh.reatoyrobot;

/**
 * This class represents the table of in which the robot will move on.
 * It has 2 field width and length. It will be initialized with width = 5 and length=5 
 * so the valid value will be from 0 to 4.
 * The location(width,length) = (0,0) is the corner of SOUTH WEST
 * @author HaiSonVH
 */
public class Table {
    private int width;
    private int length;

    public Table() {
        width = 5;
        length = 5;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
    
    public boolean validLocation(int x,int y){
        boolean response = true;
        if (x < 0 || x >= width)
            return false;
        if (y < 0 || y >= length)
            return false;
        return response;
    }
}
