/**
 * Maman 13 Question 1
 * Author name - Omer Levy
 * Author ID - 209009117
 * Instructor - Roni Ben Ishay
 * Corse Nuber - 20554
 */

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class hangManController {
	
	private final int MAX_MISTAKE_SIZE = 6; // the amount of mistakes to lose the game.
    
    private GraphicsContext gc;
    private StringBuilder secretWord = new StringBuilder(); // the word that the user need to find (shown as "_ _ ...").
    
    private String [] WordList = {"break", "job", "dog", "cat", "leo", "book", "math", "keyboard", "controller", "phone", "pizza", "landlord",}; // list of words.
    
    private String usedCharList; // holds the chars that been used by the user.
    private String word; // the word that the user need to find.
    
    private int won_Counter = 0; // counter for the number of the games won.
    private int losed_Counter = 0; // counter for the number of the games lost.
    private int round_Counter = 1; // counter for the total number of rounds.
    private int mistakeCounter; // counter for the number of mistakes the user did.
	
	@FXML private Canvas canv;
    
	@FXML private Text endGameText;
	@FXML private Text G;
	@FXML private Text textForWord;
    @FXML private Text usedChars;
    @FXML private Text wonCounter;
    @FXML private Text losedCounter;
    @FXML private Text roundCounter;
    
    @FXML private TextField guess;

    @FXML private Button buttom1;
    @FXML private Button buttom2;
    @FXML private Button buttom3;
       
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    	wonCounter.setText(String.valueOf(won_Counter));
    	losedCounter.setText(String.valueOf(losed_Counter));
    	roundCounter.setText(String.valueOf(round_Counter));
    	setGame();
    }
    
    @FXML void getTextInput(ActionEvent event) {
    		playTurn();
    		guess.clear();
    }
    
    @FXML void newRound(ActionEvent event) {
    	roundCounter.setText(String.valueOf(++round_Counter));
    	setGame();
    }

    @FXML void stopGame(ActionEvent event) {
    	stopGame(3);
    }
    
    // set the beard of the start of the game.
    public void setGame() {
    	word = null;
        secretWord.setLength(0);
        mistakeCounter = 0;
        
        usedChars.setText(String.valueOf(""));
    	usedCharList = "";
        
        gc.clearRect( 0, 0, canv.getWidth(), canv.getHeight());
        roundCounter.setText(String.valueOf(round_Counter));
        
        word = getRandomString();
		setUpWord();
		drow();
		guess.clear();
		
		buttom1.setDisable(false);
		buttom1.setVisible(true);
    	buttom2.setDisable(false);
    	buttom2.setVisible(true);
    	
    	endGameText.setVisible(false);
    }
    
    // stop the game (if won, lost or ended). 
    public void stopGame(int action) {
    	
    	textForWord.setText(String.valueOf(word));
    	
    	switch (action) {
    	case 1: // won
    		endGameText.setText("YOU WON !!");
    		endGameText.setFill(Color.BLUE);
    		endGameText.setVisible(true);
    		wonCounter.setText(String.valueOf(++won_Counter));
    		break;
    		
    	case 2: // lose
    		endGameText.setText("YOU LOST");
    		endGameText.setFill(Color.FIREBRICK);
    		endGameText.setVisible(true);
    		losedCounter.setText(String.valueOf(++losed_Counter));
    		break;
    	
    	case 3: // stopped
    		losedCounter.setText(String.valueOf(++losed_Counter));
    		break;
    		
    	}
    	buttom1.setDisable(true);
    	buttom1.setVisible(false);
    	buttom2.setDisable(true);
    	buttom2.setVisible(false);
    }
    
    // set the secret word for the current round.
    public void setUpWord() {
    	for(int i = 0; i < word.length(); i++) {
    		secretWord.append("_");
    	}
    	printSecretWord();
    }
    
    // print the secret word in the form of "_".
    public void printSecretWord() {
    	String tempWord = "";
    	for (int i = 0; i < secretWord.length(); i++) {
    		tempWord += secretWord.charAt(i) + " ";
    	}
    	textForWord.setText(String.valueOf(tempWord));
    }
    
    //adds a char to the list o words used by the user is the current round.
    public void addUsedChar(char ch) {
    	for (int i = 0; i < usedCharList.length(); i++) {
    		if (ch == usedCharList.charAt(i)) {
    			return;
    		}
    	}
    	if ("" != usedCharList) {
    		usedCharList += ",";
    	}
    	usedCharList += ch;
    	usedChars.setText(String.valueOf(usedCharList));
    }
    
    // get a random word for the current round.
    public String getRandomString() {
    	Random random = new Random();
    	return WordList[random.nextInt(WordList.length)];
    }
    
    // check the char input by the user and perceive the round.
    public void playTurn() {
    	String guess = this.guess.getText();
    	if (null == guess) {
    		return;
    	}
    	guess.toLowerCase();
    	
    	char [] wordChars = word.toCharArray();
    	char charGuess = guess.charAt(0);
    	
    	if (word.equals(guess)) { // won
    		stopGame(1);
    		return;
    	} else if (word.contains(guess)) { // found a char
    		for (int i = 0; i < word.length(); i++) {
    			if (charGuess == wordChars[i]) {
    				secretWord.setCharAt(i, charGuess);
    			}
    		}
    		printSecretWord();
    	} else { 
    		if (MAX_MISTAKE_SIZE >= mistakeCounter) { // mistake
    			mistakeCounter++;
        		addUsedChar(guess.charAt(0));
        		drow();
    		}
    		if (MAX_MISTAKE_SIZE == mistakeCounter) { // lose
    			stopGame(2);
    		}
    	}
    	if (-1 == secretWord.indexOf("_")) {
    		stopGame(1);
    		return;
    	}
    }
    
    // Draw the hang man in the canvas.
    public void drow() {
    	switch(mistakeCounter) {
    	case 0:
    		// drew the hang tree
    		gc.strokeLine(0, 320, 300, 320);
    		gc.strokeLine(200, 320, 200, 30);
    		gc.strokeLine(200, 30, 100, 30);
    		gc.strokeLine(100, 30, 100, 80);
    		break;
    	
    	case 1:
    		gc.strokeOval(85, 80, 30, 30); // head
    		break;
    	
    	case 2:
    		gc.strokeLine(100, 110, 100, 200); // body
    		break;
    	
    	case 3:
    		gc.strokeLine(100, 120, 70, 150); // left hand
    		break;
    	
    	case 4:
    		gc.strokeLine(100, 120, 130, 150); // right hand
    		break;
    	
    	case 5:
    		gc.strokeLine(100, 200, 70, 250); // left leg
    		break;
    	
    	case 6:
    		gc.strokeLine(100, 200, 130, 250); // right leg
    		break;
    	}
    }

}





