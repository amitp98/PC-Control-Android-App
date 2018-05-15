/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteDesktop;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.io.IOException;
/**
 *
 * @author amit
 */
public class MouseKey {
    Robot robot;
    
    public MouseKey() {
        try {
            robot = new Robot();
        }
        
        catch(Exception e) {
            RemoteDesktop.info.setText("Error in Robot");
        }
    }
    
    public void launch(int a) {
        Runtime runtime = Runtime.getRuntime();
        
        try {
            switch(a) {
                case 1: runtime.exec("firefox");   
            		  break;
                case 2: runtime.exec("gedit");   
            		  break;                
                case 3: runtime.exec("nautilus");    
            		  break;
            }
        }
        catch (IOException e)
        {
            RemoteDesktop.info.setText("Cannot launch Application");
        }
    }
    
    public void keyPress(int keyCode) {
        robot.keyPress(keyCode);
    }
    public void keyRelease(int keyCode) {
        robot.keyRelease(keyCode);
    }
    
    public void AltF4() {
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
    }
    public void ShiftF5() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_F5);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_F5);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }
    public void CtrlShiftZ() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_Z);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }   
    public void CtrlAltT() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_T);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
    
    public void press(int... keyCodes) {
        int length = keyCodes.length;
        for (int i = 0; i < length; i++) {
            robot.keyPress(keyCodes[i]);
        }
        robot.delay(10);
        for (int i = length - 1; i >= 0; i--) {
            robot.keyRelease(keyCodes[i]);
        }
    }
    
    public void type(char character) {
        switch (character) {
            case 'a': press(VK_A);
            		  break;
            case 'b': press(VK_B); 
            		  break;
            case 'c': press(VK_C); 
            		  break;
            case 'd': press(VK_D); 
            		  break;
            case 'e': press(VK_E); 
            		  break;
            case 'f': press(VK_F); 
            		  break;
            case 'g': press(VK_G); 
            		  break;
            case 'h': press(VK_H); 
            		  break;
            case 'i': press(VK_I); 
            		  break;
            case 'j': press(VK_J); 
            		  break;
            case 'k': press(VK_K); 
            		  break;
            case 'l': press(VK_L); 
            		  break;
            case 'm': press(VK_M); 
            		  break;
            case 'n': press(VK_N); 
            		  break;
            case 'o': press(VK_O); 
            		  break;
            case 'p': press(VK_P); 
            		  break;
            case 'q': press(VK_Q); 
            		  break;
            case 'r': press(VK_R); 
            		  break;
            case 's': press(VK_S); 
            		  break;
            case 't': press(VK_T); 
            		  break;
            case 'u': press(VK_U); 
            		  break;
            case 'v': press(VK_V); 
            		  break;
            case 'w': press(VK_W); 
            		  break;
            case 'x': press(VK_X); 
            		  break;
            case 'y': press(VK_Y); 
            		  break;
            case 'z': press(VK_Z); 
            		  break;
            case 'A': press(VK_SHIFT, VK_A); 
            		  break;
            case 'B': press(VK_SHIFT, VK_B); 
            		  break;
            case 'C': press(VK_SHIFT, VK_C); 
            		  break;
            case 'D': press(VK_SHIFT, VK_D); 
            		  break;
            case 'E': press(VK_SHIFT, VK_E); 
            		  break;
            case 'F': press(VK_SHIFT, VK_F); 
            		  break;
            case 'G': press(VK_SHIFT, VK_G); 
            		  break;
            case 'H': press(VK_SHIFT, VK_H); 
            		  break;
            case 'I': press(VK_SHIFT, VK_I); 
            		  break;
            case 'J': press(VK_SHIFT, VK_J); 
            		  break;
            case 'K': press(VK_SHIFT, VK_K); 
            		  break;
            case 'L': press(VK_SHIFT, VK_L); 
            		  break;
            case 'M': press(VK_SHIFT, VK_M); 
            		  break;
            case 'N': press(VK_SHIFT, VK_N); 
            		  break;
            case 'O': press(VK_SHIFT, VK_O); 
            		  break;
            case 'P': press(VK_SHIFT, VK_P); 
            		  break;
            case 'Q': press(VK_SHIFT, VK_Q); 
            		  break;
            case 'R': press(VK_SHIFT, VK_R); 
            		  break;
            case 'S': press(VK_SHIFT, VK_S); 
            		  break;
            case 'T': press(VK_SHIFT, VK_T); 
            		  break;
            case 'U': press(VK_SHIFT, VK_U); 
            		  break;
            case 'V': press(VK_SHIFT, VK_V); 
            		  break;
            case 'W': press(VK_SHIFT, VK_W); 
            		  break;
            case 'X': press(VK_SHIFT, VK_X); 
            		  break;
            case 'Y': press(VK_SHIFT, VK_Y); 
            		  break;
            case 'Z': press(VK_SHIFT, VK_Z); 
            		  break;
            case '`': press(VK_BACK_QUOTE); 
            		  break;
            case '0': press(VK_0); 
            		  break;
            case '1': press(VK_1); 
            		  break;
            case '2': press(VK_2); 
            		  break;
            case '3': press(VK_3); 
            		  break;
            case '4': press(VK_4); 
            		  break;
            case '5': press(VK_5); 
            		  break;
            case '6': press(VK_6); 
            		  break;
            case '7': press(VK_7); 
            		  break;
            case '8': press(VK_8); 
            		  break;
            case '9': press(VK_9); 
            		  break;
            case '-': press(VK_MINUS); 
            		  break;
            case '=': press(VK_EQUALS); 
            		  break;
            case '~': press(VK_BACK_QUOTE); 
            		  break;
            case '!': press(VK_SHIFT, VK_EXCLAMATION_MARK); 
            		  break;
            case '@': press(VK_SHIFT, VK_AT); 
            		  break;
            case '#': press(VK_SHIFT, VK_NUMBER_SIGN); 
            		  break;
            case '$': press(VK_SHIFT, VK_DOLLAR); 
            		  break;
            case '%': press(VK_SHIFT, VK_5); 
            		  break;
            case '^': press(VK_SHIFT, VK_CIRCUMFLEX); 
            		  break;
            case '&': press(VK_SHIFT, VK_AMPERSAND); 
            		  break;
            case '*': press(VK_SHIFT, VK_ASTERISK); 
            		  break;
            case '(': press(VK_LEFT_PARENTHESIS); 
            		  break;
            case ')': press(VK_RIGHT_PARENTHESIS); 
            		  break;
            case '_': press(VK_SHIFT, VK_UNDERSCORE); 
            		  break;
            case '+': press(VK_SHIFT, VK_PLUS); 
            		  break;
            case '\t': press(VK_TAB); 
            		  break;
            case '\n': press(VK_ENTER); 
            		  break;
            case '[': press(VK_OPEN_BRACKET); 
            		  break;
            case ']': press(VK_CLOSE_BRACKET); 
            		  break;
            case '\\': press(VK_BACK_SLASH); 
            		  break;
            case '{': press(VK_SHIFT, VK_OPEN_BRACKET); 
            		  break;
            case '}': press(VK_SHIFT, VK_CLOSE_BRACKET); 
            		  break;
            case '|': press(VK_SHIFT, VK_BACK_SLASH); 
            		  break;
            case ';': press(VK_SEMICOLON); 
            		  break;
            case ':': press(VK_SHIFT, VK_COLON); 
            		  break;
            case '\'': press(VK_QUOTE); 
            		  break;
            case '"': press(VK_SHIFT, VK_QUOTEDBL); 
            		  break;
            case ',': press(VK_COMMA); 
            		  break;
            case '<': press(VK_SHIFT, VK_COMMA); 
            		  break;
            case '.': press(VK_PERIOD); 
            		  break;
            case '>': press(VK_SHIFT, VK_PERIOD); 
            		  break;
            case '/': press(VK_SLASH); 
            		  break;
            case '?': press(VK_SHIFT, VK_SLASH); 
            		  break;
            case ' ': press(VK_SPACE); 
            		  break;
            case '\b': press(VK_BACK_SPACE); 
            		  break;
        }
    }
    
    public void type(int keyCode) {
        robot.keyPress(keyCode);
        robot.delay(10);
        robot.keyRelease(keyCode);
    }
    
    public void pressLeftArrowKey() {
        type(VK_LEFT);
    }
    public void pressDownArrowKey() {
        type(VK_DOWN);
    }
    public void pressRightArrowKey() {
        type(VK_RIGHT);
    }
    public void pressUpArrowKey() {
        type(VK_UP);
    }
    public void pressF5Key() {
        type(VK_F5);
    }
    
    public void LeftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public void LeftP() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }
    public void LeftR() {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public void RightClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }
    public void DoubleClick() {
        LeftClick();
        robot.delay(500);
        LeftClick();
    }
    public void Move(int x, int y) {
        robot.mouseMove(x, y);
    }
    
    
}


