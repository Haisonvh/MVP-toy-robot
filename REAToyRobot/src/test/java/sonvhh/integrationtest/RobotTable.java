/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.integrationtest;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sonvhh.reatoyrobot.Facing;
import sonvhh.reatoyrobot.Location;
import sonvhh.reatoyrobot.Table;
import sonvhh.reatoyrobot.mvp.Robot;
import sonvhh.reatoyrobot.mvp.RobotImpl;

/**
 *
 * @author HaiSonVH
 */
public class RobotTable {

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
        facing = null;
        location = null;
        table = null;
    }
    
    @DisplayName("should successfully move when next step is safe")
    @ParameterizedTest
    @CsvSource({"2,2,NORTH,2,3", "2,2,WEST,1,2", "2,2,SOUTH,2,1", "2,2,EAST,3,2"})
    public void shouldSuccessfullyMoveWhenSafe(int x, int y, String facingName, int nextX, int nextY) {
        facing.setName(facingName);
        location.setX(x);
        location.setY(y);
        target.setTable(table);
        assertDoesNotThrow(() -> target.place(location, facing));
        assertDoesNotThrow(() -> target.move());
        assertEquals(nextX + "," + nextY + "," + facingName, target.report());
    }

    @DisplayName("should ignore move when next step is not safe")
    @ParameterizedTest
    @CsvSource({"0,0,WEST", "0,0,SOUTH", "0,2,WEST",
         "0,4,WEST", "0,4,NORTH", "2,4,NORTH",
         "4,4,EAST", "4,4,NORTH", "4,2,EAST",
         "4,0,EAST", "4,0,SOUTH", "2,0,SOUTH"})
    public void shouldIgnoreMoveWhenNotSafe(int x, int y, String facingName) {
        facing.setName(facingName);
        location.setX(x);
        location.setY(y);
        target.setTable(table);
        assertDoesNotThrow(() -> target.place(location, facing));
        assertDoesNotThrow(() -> target.move());
        assertEquals(x + "," + y + "," + facingName, target.report());
    }
}
