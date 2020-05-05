/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.systemtest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sonvhh.system.ToyRobotApp;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class ApplicationTest {
        
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;  
    
    @BeforeEach
    public void setUp(){      
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown(){
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @DisplayName("Should display welcome and instructionde")
    @Test
    public void shouldDisplayWelcomAndInstruction(){
        ByteArrayInputStream in = new ByteArrayInputStream("EXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().contains("Welcome to Toy Robot simulater"));
        assertTrue(outContent.toString().contains("\t-PLACE X,Y,F\twith X,Y is location, F is NORTH, WEST, SOUTH or EAST"));
        assertTrue(outContent.toString().contains("\t-MOVE \t\tmove robot 1 point forward"));
        assertTrue(outContent.toString().contains("\t-LEFT \t\trotate robot 90 degrees to the left hand side"));
        assertTrue(outContent.toString().contains("\t-RIGHT \t\trotate robot 90 degrees to the right hand side"));
        assertTrue(outContent.toString().contains("\t-REPORT \tget the current attributes of the robot"));        
        assertTrue(outContent.toString().contains("\t-EXIT \t\tExit the simulater")); 
    }
    
    @DisplayName("Should display not found command when enter invalid command")
    @Test
    public void shouldDisplayNotFoundCommand(){
        ByteArrayInputStream in = new ByteArrayInputStream("ABC\r\nEXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().endsWith(Constants.ERROR_INVALID_COMMANDLINE+"\r\n>>")); 
    }
    
    @DisplayName("Should display location not on table when enter invalid location in PLACE")
    @Test
    public void shouldDisplayLocationOutOfTable(){
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 1,6,NORTH\r\nEXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().endsWith(Constants.ERROR_LOCATION_INVALID+"\r\n>>")); 
    }
    
    @DisplayName("Should display move successfully")
    @Test
    public void shouldDisplayMoveSuccessfully(){
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 1,2,NORTH\r\nMOVE\r\nEXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().endsWith(Constants.NOTICE_MOVE_SUCCESSFULLY+"\r\n>>")); 
    }
    
    @DisplayName("Should display move unsuccessfully")
    @Test
    public void shouldDisplayMoveUnSuccessfully(){
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 0,0,WEST\r\nMOVE\r\nEXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().endsWith(Constants.NOTICE_MOVE_UNSUCCESSFULLY+"\r\n>>")); 
    }
    
    @DisplayName("Should display place robot first")
    @Test
    public void shouldDisplayPlaceFirst(){
        ByteArrayInputStream in = new ByteArrayInputStream("LEFT\r\nEXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().endsWith(Constants.ERROR_NULL_LOCATION+"\r\n>>")); 
    }
    
    @DisplayName("Should display correct report")
    @Test
    public void shouldDisplayCorrectReport(){
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 0,0,NORTH\r\nMOVE\r\nLEFT\r\nREPORT\r\nEXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().endsWith("0,1,WEST\r\n>>")); 
    }
    
    @DisplayName("Combine commands each one time")
    @Test
    public void CombineCommandsEachOneTime(){
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 1,1,NORTH\r\nLEFT\r\nMOVE\r\nRIGHT\r\nREPORT\r\nEXIT".getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().contains(Constants.NOTICE_MOVE_SUCCESSFULLY)); 
        assertTrue(outContent.toString().endsWith("0,1,NORTH\r\n>>")); 
    }
    
    @DisplayName("Combine commands each multi times")
    @ParameterizedTest
    @CsvSource({"'PLACE 1,1,NORTH\r\nMOVE\r\nMOVE\r\nREPORT\r\nEXIT','1,3,NORTH\r\n>>'"
            ,"'PLACE 1,1,NORTH\r\nLEFT\r\nMOVE\r\nLEFT\r\nMOVE\r\nREPORT\r\nEXIT','0,0,SOUTH\r\n>>'"
            ,"'PLACE 1,2,NORTH\r\nRIGHT\r\nRIGHT\r\nLEFT\r\nREPORT\r\nEXIT','1,2,EAST\r\n>>'"
            ,"'PLACE 1,1,NORTH\r\nRIGHT\r\nMOVE\r\nRIGHT\r\nMOVE\r\nREPORT\r\nEXIT','2,0,SOUTH\r\n>>'"
            ,"'PLACE 1,1,NORTH\r\nRIGHT\r\nMOVE\r\nLEFT\r\nMOVE\r\nREPORT\r\nPLACE 0,0,NORTH\r\nREPORT\r\nEXIT','0,0,NORTH\r\n>>'"})
    public void CombineCommandsEachMultiTime(String command, String expect){
        ByteArrayInputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        ToyRobotApp.main(null);
        assertTrue(outContent.toString().endsWith(expect)); 
    }
}
