/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotscripter;

import java.util.ArrayList;

/**
 * Handler that handles all the Input listening classes and fires to them when 
 * called
 * @author Garrett
 */
public class InputHandler {
    
    static ArrayList<Object> objects  = new ArrayList<Object>();
    
    /**
     * Add an object to the list
     * @param o : The object
     */
    public static void addListener(Object o){
        objects.add(o);
    }
    
    /**
     * Remove and object from the list
     * @param o : The object
     */
    public static void removeListener(Object o){
        objects.remove(o);
    }
    
    /**
     * Send user input to the listeners
     * @param s : The input (question)
     */
    public static void sendUserInput(String s){
        for(int i = 0; i < objects.size(); i++){
            ((InputListener)objects.get(i)).inputSubmitted(s);
        }
    }
    
    /**
     * Notify the listening objects the user has requested an exit
     * @param code : Exit code
     */
    public static void notifyUserExit(int code){
        for(int i = 0; i < objects.size(); i++){
            ((InputListener) objects.get(i)).onExit(code);
        }
    }
    
    /**
     * Notify the Input type of the user has been changed
     * @param newInputType : The new Input type (See InputListener for static constants)
     */
    public static void notifyInputTypeChanged(int newInputType){
        for(int i = 0; i < objects.size(); i++){
            ((InputListener) objects.get(i)).inputTypeChanged(newInputType);
        }
    }
}
