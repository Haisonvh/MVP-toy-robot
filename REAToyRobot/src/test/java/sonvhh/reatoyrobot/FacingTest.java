/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class FacingTest {

    public FacingTest() {
    }   
    Facing target;
    @BeforeEach    
    public void setUp(){
        target = new Facing();
    }
    @AfterEach
    public void tearDown() {
        target = null;
    }
    @DisplayName("Should successfully set the name 'NORTH' with correct leftSideName:'WEST' and rightSideName:'EAST' ")
    @Test
    public void testSetNorthNameSuccessfully() {
        target.setName(Constants.FACING_NORTH);
        assertEquals(Constants.FACING_NORTH,target.getName());
        assertEquals(Constants.FACING_WEST,target.getLeftSideName());
        assertEquals(Constants.FACING_EAST,target.getRightSideName());
    }
    
    
    @DisplayName("Should successfully set the name 'WEST' with correct leftSideName:'SOUTH' and rightSideName:'NORTH' ")
    @Test
    public void testSetWestNameSuccessfully() {
        target.setName(Constants.FACING_WEST);
        assertEquals(Constants.FACING_WEST,target.getName());
        assertEquals(Constants.FACING_SOUTH,target.getLeftSideName());
        assertEquals(Constants.FACING_NORTH,target.getRightSideName());
    }
    
    @DisplayName("Should successfully set the name 'SOUTH' with correct leftSideName:'EAST' and rightSideName:'WEST' ")
    @Test
    public void testSetSouthNameSuccessfully() {
        target.setName(Constants.FACING_SOUTH);
        assertEquals(Constants.FACING_SOUTH,target.getName());
        assertEquals(Constants.FACING_EAST,target.getLeftSideName());
        assertEquals(Constants.FACING_WEST,target.getRightSideName());
    }
    
    @DisplayName("Should successfully set the name 'EAST' with correct leftSideName:'NORTH' and rightSideName:'SOUTH' ")
    @Test    
    public void testSetEastNameSuccessfully() {
        target.setName(Constants.FACING_EAST);
        assertEquals(Constants.FACING_EAST,target.getName());
        assertEquals(Constants.FACING_NORTH,target.getLeftSideName());
        assertEquals(Constants.FACING_SOUTH,target.getRightSideName());
    }
    
    @DisplayName("Should successfully set the name 'EAST' with correct leftSideName:'NORTH' and rightSideName:'SOUTH' ")
    @Test    
    public void throwExceptionWhenSetNullName() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setName(null));
        assertEquals(Constants.ERROR_NULL_FACING_NAME, exception.getMessage());
    }
    
    @DisplayName("Throw exception when set name which is not 'SOUTH','NORTH','WEST', or 'EAST'")
    @ParameterizedTest
    @ValueSource(strings = {" ","abc",""})
    public void throwExceptionWhenSetInvalidName(String name){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setName(name));
        assertEquals(Constants.ERROR_INVALID_FACING_NAME, exception.getMessage());
    }
}
