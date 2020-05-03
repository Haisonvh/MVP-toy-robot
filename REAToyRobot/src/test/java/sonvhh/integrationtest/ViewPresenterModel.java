/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.integrationtest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sonvhh.reatoyrobot.Table;
import sonvhh.reatoyrobot.mvp.MainView;
import sonvhh.reatoyrobot.mvp.Presenter;
import sonvhh.reatoyrobot.mvp.PresenterImpl;
import sonvhh.reatoyrobot.mvp.Robot;
import sonvhh.reatoyrobot.mvp.RobotImpl;
import sonvhh.reatoyrobot.mvp.View;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import sonvhh.reatoyrobot.Facing;
import sonvhh.reatoyrobot.Location;
import sonvhh.utilities.Constants;
/**
 *
 * @author HaiSonVH
 */
public class ViewPresenterModel {
    private View view;
    private Robot robot;
    private Presenter presenter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;  
   
    @BeforeEach
    public void setUp(){
        view  = new MainView();
        robot = new RobotImpl();
        presenter = new PresenterImpl();      
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown(){
        view  = null;
        robot = null;
        presenter = null;
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @DisplayName("Should successfully set model")
    @Test
    public void ShouldSuccessfullySetModel(){        
        assertDoesNotThrow(()->presenter.setModel(robot));
        Location location = new Location();
        location.setX(0);
        location.setY(0);
        Facing facing = new Facing();
        facing.setName(Constants.FACING_NORTH);
        assertDoesNotThrow(()->robot.place(location, facing));
        assertEquals("0,0,"+Constants.FACING_NORTH, robot.report());
    }
    
    @DisplayName("Should successully set view")
    @Test
    public void shouldSuccessfullySetView(){
        assertDoesNotThrow(()-> presenter.setView(view));
        ByteArrayInputStream in = new ByteArrayInputStream("EXIT".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.startListen());
        
    }
    
    //The main purpose of this test is to ensure the communication from View to Presenter to Model
    //Because each of their unit test already pass. Heace we just need concern the output which is 
    //printed on the console screen.
    @DisplayName("Should update model and view when enter valid command")
    @ParameterizedTest
    @CsvSource({"'PLACE 1,2,NORTH\r\nREPORT\r\nEXIT','1,2,NORTH\r\n'"
            ,"'PLACE 1,2,NORTH\r\nLEFT\r\nREPORT\r\nEXIT','1,2,WEST\r\n'"
            ,"'PLACE 1,2,NORTH\r\nRIGHT\r\nREPORT\r\nEXIT','1,2,EAST\r\n'"
            ,"'PLACE 1,2,NORTH\r\nMOVE\r\nREPORT\r\nEXIT','1,3,NORTH\r\n'"})
    public void shouldUpdateModelAndViewWhenEnterValidCommand(String command,String expect) {
        
        assertDoesNotThrow(()->presenter.setModel(robot));
        assertDoesNotThrow(()-> presenter.setView(view));
        ByteArrayInputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.startListen());
        assertTrue(outContent.toString().contains(expect));
        if (command.contains("MOVE"))
            assertTrue(outContent.toString().contains(Constants.NOTICE_MOVE_SUCCESSFULLY));
    }
    
    //Take 1 example of invalid command to check the out put in the console screen.
    @DisplayName("Should not update model when enter invalid command")
    @Test
    public void shouldNotUpdateModelWhenEnterInValidCommand() {
        
        assertDoesNotThrow(()->presenter.setModel(robot));
        assertDoesNotThrow(()-> presenter.setView(view));
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 1,2, NORTH\r\nEXIT".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.startListen());
        assertTrue(outContent.toString().contains(Constants.ERROR_INVALID_COMMANDLINE));
    }
    
    
    @DisplayName("Should not update model when enter valid command before calling PLACE")
    @ParameterizedTest
    @ValueSource(strings = {"REPORT\r\nEXIT"
            ,"LEFT\r\nEXIT"
            ,"RIGHT\r\nEXIT"
            ,"MOVE\r\nEXIT"})
    public void shouldNotUpdateModelWhenEnterValidCommandBeforePLACE(String command) {
        
        assertDoesNotThrow(()->presenter.setModel(robot));
        assertDoesNotThrow(()-> presenter.setView(view));
        ByteArrayInputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.startListen());
        assertTrue(outContent.toString().contains(Constants.ERROR_NULL_LOCATION));
    }
    
    @DisplayName("Should not update model and anounce the status of unsafe MOVE command")
    @Test
    public void shouldNotUpdateModelWhenEnterUnsafeMOVE() {
        
        assertDoesNotThrow(()->presenter.setModel(robot));
        assertDoesNotThrow(()-> presenter.setView(view));
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 0,0,SOUTH\r\nMOVE\r\nREPORT\r\nEXIT".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.startListen());
        assertTrue(outContent.toString().contains("0,0,SOUTH"));
        assertTrue(outContent.toString().contains(Constants.NOTICE_MOVE_UNSUCCESSFULLY));            
    }
}
