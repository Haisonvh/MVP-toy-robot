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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
       facing.setName(Constants.FACING_NORTH);
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
       facing.setName(Constants.FACING_NORTH);
       location.setX(0);
       location.setY(0);
       NullPointerException exception = assertThrows(NullPointerException.class,() ->target.place(location, facing));
       assertEquals(Constants.ERROR_NO_TABLE, exception.getMessage());
   }
   
   @DisplayName("Should annouce robot is not on table before successfully call place command")
   @Test
   public void shouldAnnounceRoBotNotOnTableBeforeCallPlace(){
       assertEquals(Constants.ERROR_ROBOT_NOT_ON_TBLE, target.report());
       target.setTable(table);
       assertEquals(Constants.ERROR_ROBOT_NOT_ON_TBLE, target.report());
   }
   
   @DisplayName("should successfully call place command after set table")
   @Test
   public void shouldSuccessfullyCallPlaceAfterSetTable(){
       facing.setName(Constants.FACING_NORTH);
       location.setX(0);
       location.setY(0);
       target.setTable(table);
       assertDoesNotThrow(() ->target.place(location, facing));   
       assertEquals("0,0,NORTH", target.report());
   }
   
   @DisplayName("should throw exception when call turnLeft command before successfully call place command")
   @Test
   public void shouldExceptionCallTurnLeftBeforePlace(){
       NullPointerException exception = assertThrows(NullPointerException.class, ()->target.turnLeft());
       assertEquals(Constants.ERROR_NULL_LOCATION, exception.getMessage());
   }
   
   @DisplayName("should successfully call turnLeft command after successfully call place command")
   @Test
   public void shouldSuccessfullyCallTurnLeftAfterPlace(){
       facing.setName(Constants.FACING_NORTH);
       location.setX(0);
       location.setY(0);
       target.setTable(table);
       assertDoesNotThrow(() ->target.place(location, facing));
       assertDoesNotThrow(() ->target.turnLeft());
       assertEquals("0,0,WEST", target.report());
       assertDoesNotThrow(() ->target.turnLeft());
       assertEquals("0,0,SOUTH", target.report());
       assertDoesNotThrow(() ->target.turnLeft());
       assertEquals("0,0,EAST", target.report());
       assertDoesNotThrow(() ->target.turnLeft());
       assertEquals("0,0,NORTH", target.report());
   }
   
   @DisplayName("should throw exception when call turnRight command before successfully call place command")
   @Test
   public void shouldExceptionCallTurnRightBeforePlace(){
       NullPointerException exception = assertThrows(NullPointerException.class, ()->target.turnRight());
       assertEquals(Constants.ERROR_NULL_LOCATION, exception.getMessage());
   }
   
   @DisplayName("should successfully call turnRight command after successfully call place command")
   @Test
   public void shouldSuccessfullyCallTurnRightAfterPlace(){
       facing.setName(Constants.FACING_NORTH);
       location.setX(0);
       location.setY(0);
       target.setTable(table);
       assertDoesNotThrow(() ->target.place(location, facing));
       assertDoesNotThrow(() ->target.turnRight());
       assertEquals("0,0,EAST", target.report());
       assertDoesNotThrow(() ->target.turnRight());
       assertEquals("0,0,SOUTH", target.report());
       assertDoesNotThrow(() ->target.turnRight());
       assertEquals("0,0,WEST", target.report());
       assertDoesNotThrow(() ->target.turnRight());
       assertEquals("0,0,NORTH", target.report());
   }
   
   @DisplayName("should throw exception when call move command before successfully call place command")
   @Test
   public void shouldExceptionCallMoveBeforePlace(){
       NullPointerException exception = assertThrows(NullPointerException.class, ()->target.move());
       assertEquals(Constants.ERROR_NULL_LOCATION, exception.getMessage());
   }
   
   @DisplayName("should successfully move when next step is safe")
   @ParameterizedTest
   @CsvSource({"2,2,NORTH,2,3","2,2,WEST,1,2","2,2,SOUTH,2,1","2,2,EAST,3,2"})
   public void shouldSuccessfullyMoveWhenSafe(int x, int y, String facingName, int nextX, int nextY){
       facing.setName(facingName);
       location.setX(x);
       location.setY(y);
       target.setTable(table);
       assertDoesNotThrow(() ->target.place(location, facing));
       assertDoesNotThrow(() ->target.move());
       assertEquals(nextX + "," + nextY + "," + facingName, target.report());
   }
   
   @DisplayName("should ignore move when next step is not safe")
   @ParameterizedTest
   @CsvSource({"0,0,WEST","0,0,SOUTH","0,2,WEST"
           ,"0,4,WEST","0,4,NORTH","2,4,NORTH"
           ,"4,4,EAST","4,4,NORTH","4,2,EAST"
           ,"4,0,EAST","4,0,SOUTH","2,0,SOUTH"})
   public void shouldIgnoreMoveWhenNotSafe(int x, int y, String facingName){
       facing.setName(facingName);
       location.setX(x);
       location.setY(y);
       target.setTable(table);
       assertDoesNotThrow(() ->target.place(location, facing));
       assertDoesNotThrow(() ->target.move());
       assertEquals(x + "," + y + "," + facingName, target.report());
   }
}
