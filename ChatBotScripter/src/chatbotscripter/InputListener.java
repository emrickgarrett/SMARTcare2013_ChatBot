/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotscripter;

/**
 * Listener for user input and input type
 * @author Garrett
 */
public abstract interface InputListener {
    
    //Examples of some input types
    final int INPUT_TYPE_SYSTEM_IN = -9000;
    final int INPUT_TYPE_FILE = -9001;
    final int INPUT_TYPE_COMPONENT = -9002;
    final int INPUT_TYPE_INTERNET_SOURCE = -9003;
    final int INPUT_TYPE_OTHER = -9004;
    
    abstract void inputSubmitted(String s);
    
    abstract void onExit(int code);
    
    abstract void inputTypeChanged(int inputType);
}
