/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.reatoyrobot.mvp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
public class MainViewTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    private MainView target;
    public MainViewTest() {
    }
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));        
        target = new MainView();
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        target = null;
    }

    @DisplayName("Should throw exception when set null presenter")
    @Test
    public void shouldExceptionWhenSetNullPresenter(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setPresenter(null));
        assertEquals(Constants.ERROR_NULL_PRESENTER, exception.getMessage());
    }
    
    @DisplayName("Should successfully set presenter")
    @Test
    public void shouldSuccessfullyWhenSetPresenter(){
        Presenter presenter = new PresenterImpl();
        assertDoesNotThrow(() -> target.setPresenter(presenter));
    }
    
    @DisplayName("Should throw exception when updateView with null data")
    @Test
    public void shouldExceptionWhenUpdateWithNullData(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.updateView(null));
        assertEquals(Constants.ERROR_NULL_SHOWING_DATA, exception.getMessage());
    }
    
    @DisplayName("Should throw exception when updateView with empty data")
    @ParameterizedTest
    @ValueSource(strings = {""," ","  "})
    public void shouldExceptionWhenUpdateWithEmptyData(String data){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.updateView(data));
        assertEquals(Constants.ERROR_NULL_SHOWING_DATA, exception.getMessage());
    }
    
    @DisplayName("Should successfully print data")
    @ParameterizedTest
    @ValueSource(strings = {"Hello world","??!@#123"})
    public void shouldSuccessfullyPrintData(String data){
        assertDoesNotThrow(() -> target.updateView(data));
        assertEquals(data+"\r\n", outContent.toString());
    }
}
