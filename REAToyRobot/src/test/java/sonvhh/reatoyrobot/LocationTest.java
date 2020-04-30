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

/**
 *
 * @author HaiSonVH
 */
public class LocationTest {
    
    public LocationTest() {
    }
    
    @DisplayName("Should initialize a location point with value (-1,-1)")
    @Test
    public void shouldSuccessfullyInitializeLocation(){
        Location target = new Location();
        assertEquals(-1, target.getX());
        assertEquals(-1, target.getY());
    }
    
    @DisplayName("Should set X value successfully")
    @Test
    public void shouldSuccessfullysetX(){
        Location target = new Location();
        target.setX(0);
        assertEquals(0, target.getX());
        assertEquals(-1, target.getY());
    }
    
    @DisplayName("Should set Y value successfully")
    @Test
    public void shouldSuccessfullysetY(){
        Location target = new Location();
        target.setY(0);
        assertEquals(-1, target.getX());
        assertEquals(0, target.getY());
    }    
}
