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
import org.junit.jupiter.params.provider.ValueSource;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class PresenterImplTest {
    
    public PresenterImplTest() {
    }
    private PresenterImpl target;
    @BeforeEach
    public void setUp() {
        target = new PresenterImpl();
    }
    
    @AfterEach
    public void tearDown() {
        target = null;
    }

    @DisplayName("Should return null when enter invalid command")
    @ParameterizedTest
    @ValueSource(strings = {"ABC", "123", ""," ","PLACE  1,2,NORTH","PLACE 1, 2,NORTH","PLACE 1,2, NORTH"
            ,"PLACE 1,2,NORTH EAST", "PLACE","PLACE 1","PLACE 1,2","PLACE a,b"
            ,"MOVE 1", "LEFT LEFT"})
    public void shouldReturNullForInvalidCommand(String command) {
        String response = target.validateCommand(command);
        assertNull(response);
    }
    
    @DisplayName("Should return null when command is null")
    @Test
    public void shouldReturNullForNullCommand() {
        String response = target.validateCommand(null);
        assertNull(response);
    }
    
    @DisplayName("Should return command name when enter valid command")
    @ParameterizedTest
    @ValueSource(strings = {"PLACE 1,2,NORTH","PLACE 1,2,EAST","PLACE 1,2,SOUTH"
            ,"PLACE 1,2,WEST","MOVE","RIGHT","REPORT","EXIT","LEFT"
            ," LEFT","LEFT "})
    public void shouldReturnCommandName(String command) {
        String response = target.validateCommand(command);
        String[] commandSplit = command.trim().split(" ");
        assertEquals(commandSplit[0],response);
    }
    
    @DisplayName("Should throw exception when set null model")
    @Test
    public void shouldExceptionWhenSetNullModel(){
        NullPointerException exception = assertThrows(NullPointerException.class, ()->target.setModel(null));
        assertEquals(Constants.ERROR_NULL_MODEL, exception.getMessage());
    }
    
    @DisplayName("Should throw exception when set null view")
    @Test
    public void shouldExceptionWhenSetNullView(){
        NullPointerException exception = assertThrows(NullPointerException.class, ()->target.setView(null));
        assertEquals(Constants.ERROR_NULL_VIEW, exception.getMessage());
    }
}
