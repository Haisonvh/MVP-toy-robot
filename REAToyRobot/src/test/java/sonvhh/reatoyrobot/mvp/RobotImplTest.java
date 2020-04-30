/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import sonvhh.reatoyrobot.Facing;
import sonvhh.reatoyrobot.Location;
import sonvhh.reatoyrobot.Table;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class RobotImplTest {
    
    public RobotImplTest() {
    }
    
    Robot target;
    Facing facing;
    Location location;
    Table table;
    @BeforeEach
    public void setUp() {
        target = new RobotImpl();
        facing = new Facing();
        location = new Location();
        table = new Table();
    }
    
    @AfterEach
    public void tearDown() {
        target = null;
    }

   @DisplayName("should throw exception when call place command with a null location")
   @Test
   public void shouldExceptionCallPlaceWithNullLocation(){
       target.setTable(table);
       facing.setName(Constants.NORTH_FACING);
       NullPointerException exception = assertThrows(NullPointerException.class,() ->target.place(null, facing));
       assertEquals(Constants.ERROR_LOCATION_INVALID, exception.getMessage());
   }
   
   @DisplayName("should throw exception when call place command with a null facing or null facing name")
   @Test
   public void shouldExceptionCallPlaceWithNullFacing(){
       target.setTable(table);
       location.setX(0);
       location.setY(0);
       NullPointerException exception = assertThrows(NullPointerException.class,() ->target.place(location, null));
       assertEquals(Constants.ERROR_NULL_FACING, exception.getMessage());
       
       exception = assertThrows(NullPointerException.class,() ->target.place(location, facing));
       assertEquals(Constants.ERROR_INVALID_FACING_NAME, exception.getMessage());
   }
   
   @DisplayName("should throw exception when call place command before set table")
   @Test
   public void shouldExceptionCallPlaceBeforeSetTable(){
       facing.setName(Constants.NORTH_FACING);
       location.setX(0);
       location.setY(0);
       NullPointerException exception = assertThrows(NullPointerException.class,() ->target.place(location, facing));
       assertEquals(Constants.ERROR_NO_TABLE, exception.getMessage());
   }
   
   @DisplayName("should successfully call place command after set table")
   @Test
   public void shouldSuccessfullyCallPlaceAfterSetTable(){
       facing.setName(Constants.NORTH_FACING);
       location.setX(0);
       location.setY(0);
       target.setTable(table);
       assertDoesNotThrow(() ->target.place(location, facing));
   }
   
}
