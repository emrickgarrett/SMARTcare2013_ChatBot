/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotscripter;

import java.util.ArrayList;
import java.util.List;


/**
 * Listener for the Chatbot, takes input from both user and bot, not to be confused
 * with the InputListener which actually gets the input from the user
 * @author Garrett
 */
public abstract interface ChatbotListener {
    
    abstract void onQuestionAsked(String s);
    
    abstract void onAnswerRecieved(String s);
    
    abstract void onNameChanged(String s);
    
    abstract void onExit(int code);
    
    abstract void notify(String s, int source);
    
    abstract void onErrorRecieved(int errorCode);
    
    abstract void onChatbotReady();
    
}
