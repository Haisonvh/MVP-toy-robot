/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author HaiSonVH
 */
public class TableTest {
    
    public TableTest() {
    }
    
    @DisplayName("Should initialize a table with width and length equa (5,5)")
    @Test
    public void shouldSuccessfullyInitializeTable(){
        Table target = new Table();
        assertEquals(5, target.getWidth());
        assertEquals(5, target.getLength());
    }
    
    @DisplayName("Should set Width value successfully")
    @Test
    public void shouldSuccessfullySetWidth(){
        Table target = new Table();
        target.setWidth(10);
        assertEquals(10, target.getWidth());
        assertEquals(5, target.getLength());
    }
    
    @DisplayName("Should set Legnth value successfully")
    @Test
    public void shouldSuccessfullysetY(){
        Table target = new Table();
        target.setLength(10);
        assertEquals(5, target.getWidth());
        assertEquals(10, target.getLength());
    }
    
    @DisplayName("Should valid success location on the table")
    @ParameterizedTest
    @CsvSource({"0,0","4,0","2,0","0,4","0,2","3,2"})
    public void shouldValidLocationOnTable(int x, int y){
        Table target = new Table();
        assertTrue(target.validLocation(x, y));
    }
    
    @DisplayName("Should unvalid location out of the table")
    @ParameterizedTest
    @CsvSource({"-1,0","5,0","0,6","0,-2","-3,-2"})
    public void shouldUnValidLocationOutOfTable(int x, int y){
        Table target = new Table();
        assertFalse(target.validLocation(x, y));
    }
}
