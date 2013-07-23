/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotscripter;

import java.util.ArrayList;

/**
 * Class that handles all the classes that implement the ChatbotListener,
 * fires to all of them when called
 * @author Garrett
 */
public class ChatbotHandler {
    
    static ArrayList<Object> objects = new ArrayList<Object>();
    
    /**
     * Add a object that uses the listener
     * @param o : The object
     */
    public static void addListener(Object o){
        ChatbotHandler.objects.add(o);
    }
    
    /**
     * Remove a object that uses the listener
     * @param o : The object
     */
    public static void removeListener(Object o){
        ChatbotHandler.objects.remove(o);
    }
    
    /**
     * Currently does nothing, perhaps will remove later
     * @param s : Message
     * @param code : Code
     */
    public static void fireListeners(String s, int code){
        
    }
    
    /**
     * When a question is asked
     * @param s : The Question
     */
    public static void questionAsked(String s){
        for(int i = 0; i < objects.size(); i++){
            ((ChatbotListener)objects.get(i)).onQuestionAsked(s);
        }
    }
    
    /**
     * When an answer is received
     * @param s : The Answer
     */
    public static void answerRecieved(String s){
        for(int i = 0; i < objects.size(); i++){
            ((ChatbotListener)objects.get(i)).onAnswerRecieved(s);
        }
    }
    
    /**
     * When the name is changed of the bot
     * @param s : The name
     */
    public static void nameChanged(String s){
        for(int i = 0; i < objects.size(); i++){
            ((ChatbotListener)objects.get(i)).onNameChanged(s);
        }
    }
    
    /**
     * When the chatbot is ready for use, IE it loaded
     */
    public static void chatBotReady(){
        for(int i = 0; i < objects.size(); i++){
            ((ChatbotListener)objects.get(i)).onChatbotReady();
        }
    }
    
    /**
     * When the application has been called to exit
     * @param code  : The exit code
     */
    public static void onExit(int code){
        for(int i = 0; i < objects.size(); i++){
            ((ChatbotListener)objects.get(i)).onExit(code);
        }
    }
}
