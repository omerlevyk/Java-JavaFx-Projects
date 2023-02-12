import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MyCalendarController {
	
	@FXML void text0_1(MouseEvent event) {}
    @FXML private ChoiceBox<String> MyChoiceBoxMonth;
    @FXML private ChoiceBox<Integer> MyChoiceBoxYear;
    @FXML private GridPane grid;
    
    HashMap <String, String> HashMapOfMeeting = new HashMap<String, String>();
    
    private Calendar today;
    private Button [] btns;
    
    private String [] allMonthArray = { "January", "February",
    		"March", "April", "May", "June", "July", "August",
    		"September", "October", "November", "December" };
    
    private Integer[] allYearsArray = {1990,1991,1992,1993,1994,1995,1996,
    		1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,
    		2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,
    		2021,2022,2023,2024,2025,2026,2027,2028,2029,2030};
    // this button is for going to the asked date
    @FXML void GoToDate(ActionEvent event) {
    	grid.getChildren().clear();
    	fillGrid(getFirstDayOfMonth(),getMonthSize());
    }
    // initialize
    public void initialize() {
    	today = Calendar.getInstance();
    	DrowChoiceboxes();
    	fillGrid(getFirstDayOfMonth(),getMonthSize());
    }
    // set the Choice boxes and set the default value (witch is the current date)
    public void DrowChoiceboxes() {
    	MyChoiceBoxMonth.getItems().addAll(allMonthArray);
    	MyChoiceBoxYear.getItems().addAll(allYearsArray);
    	MyChoiceBoxMonth.setValue(allMonthArray[today.get(Calendar.MONTH)]);
    	MyChoiceBoxYear.setValue(allYearsArray[today.get(Calendar.YEAR)-allYearsArray[0]]);
    }
    // returns the amount of days in the month (also checks if the year is leap year)
    public int getMonthSize() {
    	if (getMonthNum() == 2) {
    		if (MyChoiceBoxYear.getValue()%4 == 0) {
    	    	return 29;
    	    } else {
    	    	return 28;
    	    }
    	} else if (getMonthNum() == 4 || getMonthNum() == 6 || getMonthNum() == 9 || getMonthNum() == 11) {
    	    return 30;
    	} else {
    		return 31;
    	}
    }
    // return the month number (for example: April will return 4)
    public int getMonthNum() {
    	for (int i = 0; i < allMonthArray.length; i++) {
    		if (allMonthArray[i] == MyChoiceBoxMonth.getValue()) {
    			return i+1;
    		}
    	}
    	return 0;
    }
	// fills the grid with buttons numbered 1 to 28-31 (depend of the month)
	public void fillGrid(int firstDayWeek, int ChoiceMonthSize) {
    	int jdex = 0, index = firstDayWeek-1;
    	btns = new Button [ChoiceMonthSize];
    	for (int i = 0; i < btns.length; i++) {
    		btns[i] = new Button("" + (i+1));
    		btns[i].setPrefSize(grid.getPrefWidth(), grid.getPrefHeight());
    		grid.add(btns[i], index, jdex);
    		int btnLoc = i;
    		
    		btns[i].setOnAction(new EventHandler<ActionEvent>() {
    			@Override public void handle (ActionEvent event) {
    				writeInDate(btnLoc+1);
    			}
    		});
    		
    		if (index == 6) {
    			index = 0;
    			jdex ++;
    		} else {
    			index++;
    		}
    	}
    }
    // Manage the hash map: fill meetings in selected dates, show, edit or remove existing meetings
    public void writeInDate(int dayNum) {
    	String meetingText = null;
    	
    	String date = MyChoiceBoxYear.getValue() + " " + dayNum + " " + MyChoiceBoxMonth.getValue();
    	if (null == HashMapOfMeeting.get(date)) {
    		HashMapOfMeeting.put(date, JOptionPane.showInputDialog("Enter Meeting"));
    	} else {
    		meetingText = JOptionPane.showInputDialog(
    				"your meeting is: '" + HashMapOfMeeting.get(date) +
    				"' To edit, fill in the text line and click OK, to delete "
    				+ "enter OK with an empty line and to return click Cancel");
    		if (meetingText.length() == 0 || null == meetingText) {
    			HashMapOfMeeting.remove(date);
    		} else {
    			HashMapOfMeeting.put(date,meetingText);
    		}
    	}
    }
    // returns the day in the first week the month start with
    public int getFirstDayOfMonth() {
    	Calendar date = Calendar.getInstance();
    	date.set(MyChoiceBoxYear.getValue(), getMonthNum()-1, 1);
    	return date.get(Calendar.DAY_OF_WEEK);
    }
}

