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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import sonvhh.utilities.Constants;

/**
 *
 * @author HaiSonVH
 */
public class TableTest {
    
    public TableTest() {
    }
    Table target;
    @BeforeEach
    public void setUp(){
        target = new Table();
    }
    @AfterEach
    public void tearDown() {
        target = null;
    }
    @DisplayName("Should initialize a table with width and length equal (5,5)")
    @Test
    public void shouldSuccessfullyInitializeTable(){
        assertEquals(5, target.getWidth());
        assertEquals(5, target.getLength());
    }
    
    @DisplayName("Should set Width value successfully")
    @Test
    public void shouldSuccessfullySetWidth(){
        target.setWidth(10);
        assertEquals(10, target.getWidth());
        assertEquals(5, target.getLength());
    }
    
    @DisplayName("Should set Legnth value successfully")
    @Test
    public void shouldSuccessfullysetY(){
        target.setLength(10);
        assertEquals(5, target.getWidth());
        assertEquals(10, target.getLength());
    }
    
    @DisplayName("Should valid success location on the table")
    @ParameterizedTest
    @CsvSource({"0,0","4,0","2,0","0,4","0,2","3,2"})
    public void shouldValidLocationOnTable(int x, int y){
        assertTrue(target.validLocation(x, y));
    }
    
    @DisplayName("Should unvalid location out of the table")
    @ParameterizedTest
    @CsvSource({"-1,0","5,0","0,6","0,-2","-3,-2"})
    public void shouldUnValidLocationOutOfTable(int x, int y){
        assertFalse(target.validLocation(x, y));
    }
    
    @DisplayName("Should throw exception when set width with invalid value")
    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    public void shouldExceptionSetInvalidWidth(int width){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setWidth(width));
        assertEquals(Constants.ERROR_NEGATIVE_VALUE, exception.getMessage());
    }
    
    @DisplayName("Should throw exception when set length with invalid value")
    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    public void shouldExceptionSetInvalidLength(int length){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setLength(length));
        assertEquals(Constants.ERROR_NEGATIVE_VALUE, exception.getMessage());
    }
}
