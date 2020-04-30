/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class FacingTest {

    public FacingTest() {
    }   
    
    @DisplayName("Should successfully set the name 'NORTH' with correct leftSideName:'WEST' and rightSideName:'EAST' ")
    @Test
    public void testSetNorthNameSuccessfully() {
        Facing target = new Facing();
        target.setName(Constants.NORTH_FACING);
        assertEquals(Constants.NORTH_FACING,target.getName());
        assertEquals(Constants.WEST_FACING,target.getLeftSideName());
        assertEquals(Constants.EAST_FACING,target.getRightSideName());
    }
    
    
    @DisplayName("Should successfully set the name 'WEST' with correct leftSideName:'SOUTH' and rightSideName:'NORTH' ")
    @Test
    public void testSetWestNameSuccessfully() {
        Facing target = new Facing();
        target.setName(Constants.WEST_FACING);
        assertEquals(Constants.WEST_FACING,target.getName());
        assertEquals(Constants.SOUTH_FACING,target.getLeftSideName());
        assertEquals(Constants.NORTH_FACING,target.getRightSideName());
    }
    
    @DisplayName("Should successfully set the name 'SOUTH' with correct leftSideName:'EAST' and rightSideName:'WEST' ")
    @Test
    public void testSetSouthNameSuccessfully() {
        Facing target = new Facing();
        target.setName(Constants.SOUTH_FACING);
        assertEquals(Constants.SOUTH_FACING,target.getName());
        assertEquals(Constants.EAST_FACING,target.getLeftSideName());
        assertEquals(Constants.WEST_FACING,target.getRightSideName());
    }
    
    @DisplayName("Should successfully set the name 'EAST' with correct leftSideName:'NORTH' and rightSideName:'SOUTH' ")
    @Test    
    public void testSetEastNameSuccessfully() {
        Facing target = new Facing();
        target.setName(Constants.EAST_FACING);
        assertEquals(Constants.EAST_FACING,target.getName());
        assertEquals(Constants.NORTH_FACING,target.getLeftSideName());
        assertEquals(Constants.SOUTH_FACING,target.getRightSideName());
    }
    
}
