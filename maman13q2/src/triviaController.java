import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class triviaController {

    @FXML
    private RadioButton Answer_A;
    @FXML
    private RadioButton Answer_B;
    @FXML
    private RadioButton Answer_C;
    @FXML
    private RadioButton Answer_D;
    ToggleGroup group = new ToggleGroup();
    
    @FXML
    private Button nextQ;
    @FXML
    private Button enterQ;
    @FXML
    private Button skipQ;
    @FXML
    private Button endQ;

    @FXML
    
    private Text theQuestion;
    @FXML
    private Text trueQ;
    @FXML
    private Text falseQ;
    @FXML
    private Text totalQ;
    
    @FXML
    private int trueQCounter = 0;
    @FXML
    private int falseQCounter = 0;
    @FXML
    private int totalQCounter = 0;
    
    private int currectAnswerLine = 0;
    int [] QuestionLineArray = {0, 5, 10, 15};
    
    public void initialize() {
    	
    	setGame();
    	
    	Answer_A.setToggleGroup(group);
    	Answer_B.setToggleGroup(group);
    	Answer_C.setToggleGroup(group);
    	Answer_D.setToggleGroup(group);
    	
    	setQuestion(getRendomQuestion());
    }

    @FXML
    void enterQ(ActionEvent event) {

    }

    @FXML
    void nextQ(ActionEvent event) {
    	setQuestion(getRendomQuestion());
    }

    @FXML
    void skipQ(ActionEvent event) {
    	stopGame(getAnswer());
    	setQuestion(getRendomQuestion());
    }
    
    @FXML 
    void endQ(ActionEvent event) {
    	stopGame(0);
    	enterQ.setDisable(true);
    	nextQ.setDisable(true);
    	skipQ.setDisable(true);
    }
    
    public void setGame() {
  	
    	enterQ.setVisible(true);
    	nextQ.setVisible(false);
    }
    
    public void setQuestion(int lineNumber) {
    	Random randem = new Random();
    	int [] orderArray = {1,2,3,4};
    	for (int i = 0; i < orderArray.length; i++) {
			int randomIndexToSwap = randem.nextInt(orderArray.length);
			int temp = orderArray[randomIndexToSwap];
			orderArray[randomIndexToSwap] = orderArray[i];
			orderArray[i] = temp;
			if (1 == orderArray[i]) {
				currectAnswerLine = i;
			}
		}
    	
    	theQuestion.setText(getScanLine(lineNumber));
    	Answer_A.setText(getScanLine(lineNumber + orderArray[0]));
    	Answer_A.setTextFill(Color.BLACK);
    	Answer_A.setSelected(false);
    	Answer_B.setText(getScanLine(lineNumber + orderArray[1]));
    	Answer_B.setTextFill(Color.BLACK);
    	Answer_B.setSelected(false);
    	Answer_C.setText(getScanLine(lineNumber + orderArray[2]));
    	Answer_C.setTextFill(Color.BLACK);
    	Answer_C.setSelected(false);
    	Answer_D.setText(getScanLine(lineNumber + orderArray[3]));
    	Answer_D.setTextFill(Color.BLACK);
    	Answer_D.setSelected(false);
    	
    	enterQ.setVisible(true);
    	nextQ.setVisible(false);
    	
    }
    
    public int getRendomQuestion() {
    	
    	Random randem = new Random();
    	int index = randem.nextInt(QuestionLineArray.length);
    	int tempRetrunVal = QuestionLineArray[index];
    	int [] otherArray = new int[QuestionLineArray.length - 1];
    	for (int i = 0, k = 0; i < QuestionLineArray.length; i++) {
    		if (i == index) {
    			continue;
    		}
    		otherArray[k++] = QuestionLineArray[i];
    	}
    	QuestionLineArray = otherArray; 
    	return tempRetrunVal;
    }
    
    public void stopGame(int action) {
    	String str = "the Correct Answer is";
    	switch(currectAnswerLine) {
    	case 1:
    		str += "A";
    		break;
    	case 2:
    		str += "B";
    		break;
    	case 3:
    		str += "C";
    		break;
    	case 4:
    		str += "D";
    		break;
    	}
    	theQuestion.setText(str);
    	
    	if (currectAnswerLine == action) {
    		Answer_A.setTextFill(Color.GREEN);
    	} else {
    		Answer_A.setTextFill(Color.FIREBRICK);
    	}
    	if (currectAnswerLine == action) {
    		Answer_B.setTextFill(Color.GREEN);
    	} else {
    		Answer_B.setTextFill(Color.FIREBRICK);
    	}
    	if (currectAnswerLine == action) {
    		Answer_C.setTextFill(Color.GREEN);
    	} else {
    		Answer_C.setTextFill(Color.FIREBRICK);
    	}
    	if (currectAnswerLine == action) {
    		Answer_D.setTextFill(Color.GREEN);
    	} else {
    		Answer_D.setTextFill(Color.FIREBRICK);
    	}
    	
    	enterQ.setVisible(false);
    	nextQ.setVisible(true);
    }
    
    public int getAnswer() {
    	if (Answer_A.isSelected()) {
    		return 1;
    	} else if (Answer_B.isSelected()) {
    		return 2;
    	} else if (Answer_C.isSelected()) {
    		return 3;
    	} else if (Answer_D.isSelected()) {
    		return 4;
    	} else {
    		return 0;
    	}
    }
    
    public void gameScore(int answer) {
    	if (answer == currectAnswerLine) {
    		trueQ.setText(String.valueOf(++trueQCounter));
    	} else {
    		falseQ.setText(String.valueOf(++falseQCounter));
    	}
    	totalQ.setText(String.valueOf(totalQCounter));
    }
    
    public String getScanLine(int lineNumber) {
    	//Scanner input = new Scanner(new File("trivia.txt"));
    	
    	return "";
    }
}
