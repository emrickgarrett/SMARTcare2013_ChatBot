package ChatBot_ReponseCore;

import ChatBot_Core.ChatBot_CoreCharacteristics;
import DatabaseStorage.MethodReturnObject;
import DatabaseStorage.OwnershipContainer;
import DatabaseStorage.DatabaseContainer;
import DatabaseStorage.DatabaseDirectoryList;
import chatbotscripter.BotCompiler;
import chatbotscripter.ChatbotHandler;
import chatbotscripter.InputHandler;
import chatbotscripter.InputListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the base template class for the SMART care Chatbot input output system. Its purpose is to
 * acts as a question - response program from which Nursing students may communicate with the
 * ChatBot.
 *
 * @author William Giffin
 */
public class ChatBot_InputOutput implements Runnable, InputListener
{

    //Using this for Garrett's Chatbot Compiler
    BotCompiler compiler;
    ChatBot_CoreCharacteristics newChatBot;
    static boolean hasQuestion = false;
    static String question = "";
    boolean exitCue = false;

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args)
//    {
//        //take input and process it.
//        uploadFilesToDatabase();
//    }//end of main method
    public ChatBot_InputOutput(ChatBot_CoreCharacteristics newChatBot)
    {
        //load the databases
        this.newChatBot = newChatBot;
        InputHandler.addListener(this);
    }//end of constructor

    @Override
    public void run()
    {
        Scanner inputReader = new Scanner(System.in);
        while (!exitCue)
        {
            DatabaseDirectoryList directoryList = uploadFilesToDatabase();
            ArrayList<DatabaseContainer> databaseList = directoryList.getDatabaseContainerList();
            ChatbotHandler.chatBotReady();
            System.out.println("I'm up now, how can I help?");
            ChatbotHandler.answerRecieved("I'm up now, how can I help?");
            int i = 0;
            do
            {
                if (i > 0)
                {
                    //System.out.println("anything else?");
                    ChatbotHandler.answerRecieved("anything else?");
                }//end of if
                //String question = inputReader.nextLine();
                String question = getUserInput();
                ChatbotHandler.questionAsked(question);
                if (question.equalsIgnoreCase("yes"))
                {
                    System.out.println("ok, what is it?");
                    ChatbotHandler.answerRecieved(("ok, what is it?"));
                    //question = inputReader.nextLine();
                    question = getUserInput();
                    ChatbotHandler.questionAsked(question);
                    AnswerPackage chatBotAnswerPackage = isQuestionInDatabase(databaseList, question);
                    directoryList = processInput(chatBotAnswerPackage, directoryList, newChatBot, inputReader);

                    if (chatBotAnswerPackage.isIsInDataBase())
                    {
                        System.out.println("WAS IN THE DATABASE :)");
                        compiler.queryQuestion(question);
                    }
                }//end of if
                else if (question.equalsIgnoreCase("no"))
                {
                    System.out.println("ok, bye!");
                    ChatbotHandler.answerRecieved("ok, bye!");
                    ChatbotHandler.onExit(0);
                    System.exit(0);
                }//end of else 
                else if (question.equalsIgnoreCase("print"))
                {
                    directoryList = identifyContext(inputReader, directoryList, newChatBot);
                    System.out.println("The new database is: ");
                    ChatbotHandler.answerRecieved("The new database is: ");
                    for (DatabaseContainer currentContainer : databaseList)
                    {
                        System.out.println("Name: " + currentContainer.getDatabaseName());
                        ChatbotHandler.answerRecieved("Name: " + currentContainer.getDatabaseName());
                        System.out.println("Contents: " + currentContainer.getContents().toString());
                        ChatbotHandler.answerRecieved("Contents: " + currentContainer.getContents().toString());
                        System.out.println("");
                    }//end of for
                }//end of else if
                else if (question.equalsIgnoreCase("goodbye"))
                {
                    exitCue = true;
                    System.out.println("bye!");
                    ChatbotHandler.answerRecieved("Bye!");
                }//end of if
                else
                {

                    //question = inputReader.nextLine();
                    //question = getUserInput();
                    //ChatbotHandler.questionAsked(question);
                    AnswerPackage chatBotAnswerPackage = isQuestionInDatabase(databaseList, question);
                    directoryList = processInput(chatBotAnswerPackage, directoryList, newChatBot, inputReader);

                    if (chatBotAnswerPackage.isIsInDataBase())
                    {
                        System.out.println("WAS IN THE DATABASE :)");
                        compiler.queryQuestion(question);
                    } else
                    {
                        System.out.println("Vague response interpereted as yes. proceed with conversation.");
                        ChatbotHandler.answerRecieved("Vague response interpreted as a yes. Proceed with conversation");
                    }
                }//end of else
                i++;
            } while (exitCue == false);
        }
    }

    /**
     * This method uploads a batch of files from a file folder and loads them into the program.
     *
     * @return ArrayList<DatabaseContainer> returnList - This is the list of Maps that contain the
     *         name and contents of each file.
     */
    private static DatabaseDirectoryList uploadFilesToDatabase()
    {
        DatabaseDirectoryList newDirectoryList = new DatabaseDirectoryList();
        ArrayList<DatabaseContainer> returnList = new ArrayList<>(10);
        File databaseFolder = new File("DatabaseFiles");
        File[] fileList = databaseFolder.listFiles();
        for (File currentFile : fileList)
        {
            String fileName = currentFile.getName();
            ArrayList<String> fileContents = new ArrayList<>(5);
            if (fileName.equalsIgnoreCase("OwnershipDatabase.txt"))
            {
                OwnershipContainer newOwnershipContainer = getOwnershipList(currentFile, fileContents);
                ArrayList<OwnershipContainer> ownershipContainerList = new ArrayList<>(5);
                ownershipContainerList.add(newOwnershipContainer);
                newDirectoryList.setOwnershipContainerList(ownershipContainerList);
            }//end of if
            else
            {
                try
                {
                    Scanner fileScanner = new Scanner(currentFile);
                    do
                    {
                        fileContents.add(fileScanner.nextLine());
                    } while (fileScanner.hasNext());
                    //now I have the information of the file
                } //end of try
                catch (FileNotFoundException ex)
                {
                    System.out.println("Error has been thrown in uploadFilesToDatabase!!!");
                }//end of try
                DatabaseContainer newContainer = new DatabaseContainer(fileName, fileContents);
                returnList.add(newContainer);
            }//end of else

        }//end of for
        newDirectoryList.setDatabaseContainerList(returnList);
        return newDirectoryList;
    }//end of getFiles method

    /**
     * This method gets the ownershipList for the program.
     *
     * @param currentFile  - The file that contains the ownership information
     * @param fileContents The contents of the file
     * @return {@link OwnershipContainer} This is the container that holds the ownership information
     */
    private static OwnershipContainer getOwnershipList(File currentFile, ArrayList<String> fileContents)
    {
        //The ownership file must be handled seperately, thus this block
        System.out.println("Found the ownership database");
        OwnershipContainer newOwnershipContainer = new OwnershipContainer("OwnershipList", fileContents);
        try
        {
            int i = 0;
            Scanner fileScanner = new Scanner(currentFile);
            ArrayList<ArrayList<Integer>> referenceList = new ArrayList<>(5);
            do
            {
                String currentLine = fileScanner.nextLine();
                if (i >= 2)
                {

                    String[] lineContents = currentLine.split(" ");
                    fileContents.add(lineContents[0]);
                    ArrayList<Integer> referenceSublist = new ArrayList<>(5);
                    for (int j = 1; j < lineContents.length; j++)
                    {
                        referenceSublist.add(Integer.parseInt(lineContents[j]));
                    }//end of for
                    referenceList.add(referenceSublist);
                    newOwnershipContainer.setContents(fileContents);
                    newOwnershipContainer.setReferenceList(referenceList);
                }//end of if
                i++;
            } while (fileScanner.hasNext());
        }//end of try
        catch (FileNotFoundException fnf)
        {
            System.out.println("The DatabaseOwnership file was not found");
        }//end of catch
        return newOwnershipContainer;
    }//end of getOwnershipList method

    /**
     * This method gets the input from the outside. This will be from the input of the second life
     * viewer.
     *
     * @param questionList This is the list containing all of the questions from the QuestionsList
     *                     file
     * @param question     This is the question that the user has asked the program
     */
    private static AnswerPackage isQuestionInDatabase(ArrayList<DatabaseContainer> containerList, String question)
    {
        boolean isInQuestionDatabase = false;
        ArrayList<String> questionList = new ArrayList<>(2);
        AnswerPackage newAnswerPackage = new AnswerPackage();
        newAnswerPackage.setQuestionString(question);
        //System.out.println("QUESTION SPOT 1: " + question);
        //get the input from the outside. This will always be in text.
        //get the question list database
        //System.out.println("I'm Here");
        for (DatabaseContainer currentContainer : containerList)
        {
            if (currentContainer.getDatabaseName().equals("QuestionList.txt"))
            {
                //System.out.println("Got the database");
                questionList = currentContainer.getContents();
            }//end of if
        }//end of for
        for (int i = 0; i < questionList.size(); i++)
        {
            String currentQuestion = questionList.get(i);
            //System.out.println("CURRENT QUESTION SPOT 2: " + currentQuestion);
            if (question.equalsIgnoreCase(currentQuestion))
            {
                isInQuestionDatabase = true;
                newAnswerPackage.setIsInDataBase(isInQuestionDatabase);
                return newAnswerPackage;
            }//end of if
        }//end of for
        //if it isn't
        if (isInQuestionDatabase == false)
        {
            newAnswerPackage.setIsInDataBase(isInQuestionDatabase);
            return newAnswerPackage;
        }//end of if
        return newAnswerPackage;
    }//end of getInput method

    /**
     * This method takes the input and processes it, changing the contents of the Database files to
     * increase knowledge capacity if needed.
     *
     * @param chatbotAnswerPackage The {@link AnswerPackage} containing the initial question,
     *                             possible answer, and if the question was in the database.
     * @param databaseList         The list of database files that the program contains
     * @param newChatbot           The core characteristics of the Chat bot
     * @param inputReader          The Scanner that the program uses to read in the information from
     *                             the user.
     */
    private static DatabaseDirectoryList processInput(AnswerPackage chatbotAnswerPackage, DatabaseDirectoryList directoryList, ChatBot_CoreCharacteristics newChatbot, Scanner inputReader)
    {
        ArrayList<DatabaseContainer> databaseList = directoryList.getDatabaseContainerList();
        if (chatbotAnswerPackage.isIsInDataBase())
        {
            //System.out.println(chatbotAnswerPackage.getResponseString());
        }//end of if
        else
        {
            boolean goOn = false;
            int i = 0;
            do
            {
                MethodReturnObject returnObject = dissectStatement(chatbotAnswerPackage.getQuestionString(), directoryList);
                String response = returnObject.getStringHolder();
                String contextString = returnObject.getStringHolder();
                if (!returnObject.isBooleanHolder())
                {
                    System.out.println(response);
                    ChatbotHandler.answerRecieved(response);
                    directoryList = identifyContext(inputReader, directoryList, newChatbot);
                    break;
                }//end of if
                else
                {
                    System.out.println("Did you mean " + response + "?");
                    ChatbotHandler.answerRecieved("Did you mean " + response + "?");
                }//end of else
                response = inputReader.nextLine();
                if (returnObject.getIntHolder() > 1)
                {
                    //these responses are geared towards multiple contexts which the program was able to find.
                    response = getUserInput();
                    ChatbotHandler.questionAsked(response);
                    //this is what the user wants.
                    System.out.println("ok, we're talking about " + contextString);
                    ChatbotHandler.answerRecieved("ok, we're talking about " + contextString);
                }//end of if
                else
                {
                    //these responses are geared towards a single response because the program was only able to find a single context which matched the question.
                    if (response.equalsIgnoreCase("yes"))
                    {
                        System.out.println("ok, i get it, we're talking about " + contextString + "!");
                        ChatbotHandler.answerRecieved("ok, I get it, we're talking about " + contextString + "!");
                        goOn = true;
                    }//end of if
                    else if (response.equalsIgnoreCase("no") && (i > 0))
                    {

                        directoryList = identifyContext(inputReader, directoryList, newChatbot);
                        break;
                    }//end of else if
                    else if (response.equalsIgnoreCase("no"))
                    {
                        System.out.println("let me try again.");
                        ChatbotHandler.answerRecieved("ok, I get it, we're talking about " + contextString + "!");
                    }//end of else if
                    else
                    {
                        System.out.println("huh? (that was a yes or no question)");
                        ChatbotHandler.answerRecieved("huh? (that was a yes or no question)");
                        goOn = true;
                        //do nothing
                    }//end of else
                }//end of else
                i++;
            } while (goOn == false);
            //now we have the context (file) that the program can use to respond from.
            //now to respond......
        }//end of else
        return directoryList;
    }//end of processInput method

    /**
     * This method takes the sentence that some else says, and finds out what they are saying.
     *
     * @param statement     What the person said
     * @param containerList The list of of databases in the program
     */
    private static MethodReturnObject dissectStatement(String statement, DatabaseDirectoryList directoryList)
    {
        //How old are you?
        //look throught the statement and look to see if one of the statments from the databases is in the current question
        //go through each of the databases looking to see if their contents match any part of the question.
        ArrayList<DatabaseContainer> containerList = directoryList.getDatabaseContainerList();
        ArrayList<String> contextList = new ArrayList<>(10);
        MethodReturnObject returnObject = new MethodReturnObject();
        ArrayList<OwnershipContainer> ownershipContainerList = directoryList.getOwnershipContainerList();

        for (OwnershipContainer currentOwnershipContainer : ownershipContainerList)
        {
            int currentElementIndex = 0;
            for (String currentElement : currentOwnershipContainer.getContents())
            {
                if (statement.contains(currentElement))
                {


                    //the element is in the statement
                    //identify WHO they are talking about
                    //get who the person was speaking about by checking what the reference is through the index
                    ArrayList<Integer> referenceArray = currentOwnershipContainer.getReferenceList().get(currentElementIndex);
                    //now I have the proper references to pull from and I can get who the person was speaking about from this.
                    if (referenceArray.size() > 1)
                    {
                        //for a single reference
                        switch (referenceArray.get(0))
                        {
                            case 1:
                            {
                                //this will be a personnal reference - I
                                System.out.println("ownership recognition: I");
                            }//end of case
                            case 2:
                            {
                                //you reference
                                System.out.println("ownership recognition: you");
                            }//end of case
                            case 3:
                            {
                                //group reference on inclusive (they, them)
                                System.out.println("ownership recognition: they");
                            }//end of case
                            case 4:
                            {
                                //group reference inclusive (we, us)
                                System.out.println("ownership recognition: we");
                            }//end of case
                        }//end of switch
                    }//end of if
                    else
                    {
                        //more than one element for the current word in the array
                    }//end of else
                    currentElementIndex++;
                }//end of if
            }//end of for
        }//end of for

        for (DatabaseContainer currentContainer : containerList)
        {
            ArrayList<String> databaseContents = currentContainer.getContents();
            for (String currentElement : databaseContents)
            {

                if (statement.contains(currentElement.toLowerCase()) || statement.contains(currentElement.toUpperCase()))
                {
                    //get the context of the statement by getting the name of the database
                    String context = currentContainer.getDatabaseName();
                    contextList.add(context);
                    break;
                }//end of if
            }//end of for
        }//end of for
        //if I have multiple contexts identified, then I need to resolve this by either answering both contexts simultaneously, one at time, or pick one?
        //ASK WHAT CONTEXT!!!!!!!
        //did you mean.......
        String response = "";
        for (int i = 0; i < contextList.size(); i++)
        {
            if (i > 0)
            {
                response = response.concat(" , " + contextList.get(i));

            }//end of if
            else if (i == contextList.size())
            {
                response = response.concat(" , " + contextList.get(i) + ".");
            }//end of else if
            else
            {
                response = response.concat(" " + contextList.get(i));
            }//end of else
        }//end of for
        if (response == "")
        {
            System.out.println("let me think about it...");

            returnObject.setStringHolder("I still don't understand what you mean, sorry");
            returnObject.setBooleanHolder(false);
            return returnObject;
        }//end of if
        else
        {
            //TODO take the last portion of the file off to make more sense to the human interfacer
            returnObject.setStringHolder(response);
            returnObject.setIntHolder(contextList.size());
            returnObject.setBooleanHolder(true);
            return returnObject;
        }//end of else
    }//end of dissectStatement method

    /**
     * This method identifies what context to use and how the program will respond. THIS METHOD MAY
     * CHANGE THE DATABASE FILES
     *
     * @param inputReader   This is the scanner by which the program reads in the information from
     *                      the console
     * @param containerList The list of database files
     * @param newChatBot    The ChatBot core characteristics associated with this program
     * @return ArrayList<DatabaseContainer> containerList - This is the list of database files that
     *         the program already has loaded.
     */
    private static DatabaseDirectoryList identifyContext(Scanner inputReader, DatabaseDirectoryList directoryList, ChatBot_CoreCharacteristics newChatBot)
    {
        System.out.println("What are you talking about?");
        ChatbotHandler.answerRecieved("What are you talking about?");
        String contextName = getUserInput();
        ArrayList<DatabaseContainer> containerList = directoryList.getDatabaseContainerList();
        boolean contextFound = false;
        //if the program contains the context that the person is speaking about
        //search for the context
        for (DatabaseContainer currentContainer : containerList)
        {
            if (currentContainer.getDatabaseName().equalsIgnoreCase(contextName))
            {
                //found the context, now respond using this context
                contextFound = true;
                return directoryList;
            }//end of if
        }//end of for
        //if the program does NOT contain the context that the person is speaking about
        if (contextFound == false);
        {
            //make a new context
            Context newContext = new Context(contextName);
            //to find good answeres: find what questions that the bot currently has that most closely match the new question. 
            for (DatabaseContainer currentContainer : containerList)
            {
                for (String currentElement : currentContainer.getContents())
                {
                    //split up the content of the current element and see what words match to the new database name
                    currentElement.contains(contextName);
                }//end of for
            }//end of for
//            System.out.println("making new context");
            writeFileContents(newContext, newChatBot);
//            System.out.println("clearing databases");
            clearDatabase(containerList);
            //now reload the new data
            directoryList = uploadFilesToDatabase();
//            containerList = directoryList.getDatabaseContainerList();
//            System.out.println("reloading database");
            //finding the questions
            return directoryList;
            //Then take the answer(s) to that question and make that/those answers the answers based off of that context
        }//end of if
        //now I have the context of what the person said, now I need to respond according to that context
    }//end of identifyContext method

    private static void writeFileContents(Context context, ChatBot_CoreCharacteristics newChatBot)
    {
        PrintWriter fileWriter = null;
        try
        {
            String fileName = context.getContextName();
            File newFile = new File("DatabaseFiles/ChatBot_" + newChatBot.getName() + "_" + fileName + ".txt");
            fileWriter = new PrintWriter(newFile);
            fileWriter.println(fileName);
            fileWriter.println(fileName + "?");
            fileWriter.close();
        }//end of try 
        catch (IOException ex)
        {
//            Logger.getLogger(ChatBot_InputOutput.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR IN writeFilesContents method!!!");
            File returnFile = new File("ChatBot_" + newChatBot.getName() + "_" + "ERROR" + ".txt");
        }//end of catch
    }//end of writeFileContents method

    /**
     * This method goes through and clears the database files from the program.
     *
     * @param databaseList The list of database files
     */
    private static ArrayList<DatabaseContainer> clearDatabase(ArrayList<DatabaseContainer> databaseList)
    {
        //go through and set them all to null
        for (DatabaseContainer currentContainer : databaseList)
        {
            ArrayList<String> tempList = new ArrayList<>(10);
            currentContainer.setContents(tempList);
        }//end of for
        return databaseList;
    }//end of clearDatabase method

    public static String getUserInput()
    {
        while (!hasQuestion)
        {
        }

        hasQuestion = false;
        return question;
    }

    public void resetQuestion()
    {
        question = "";
    }

    public void setQuestionHandler(BotCompiler compiler)
    {
        this.compiler = compiler;
    }

    public void exit()
    {
        ChatbotHandler.removeListener(this);
        this.exitCue = true;
    }

    /**
     * Start InputListener methods
     */
    @Override
    public void inputSubmitted(String s)
    {
        question = s;
        hasQuestion = true;
    }

    @Override
    public void onExit(int code)
    {
        System.exit(code);
    }

    @Override
    public void inputTypeChanged(int inputType)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}//end of class
