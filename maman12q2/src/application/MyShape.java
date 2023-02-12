package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class MyShape extends Application {
	
	private static final int BOARD_SIZE = 500;
    private static final int CELL_SIZE = BOARD_SIZE /10;
    private static final int SPACE_SIZE = BOARD_SIZE /CELL_SIZE;
	
	private String color;
	private int [] point1 = new int[2];
	private int [] point2 = new int[2];
	
	public static void main(String[] args) {
        launch(args);
    }
	 @Override
	    public void start(Stage stage) {
			/** Build the board */
			final Canvas canvas = new Canvas(BOARD_SIZE, BOARD_SIZE);
			int row = (int) Math.floor(BOARD_SIZE / CELL_SIZE);
	        int column = (int) Math.floor(BOARD_SIZE / CELL_SIZE);
	        
			/** Build the GUI */
			VBox root = new VBox(SPACE_SIZE);
			Scene scene = new Scene(root, BOARD_SIZE, BOARD_SIZE + CELL_SIZE);
			stage.setScene(scene);
			stage.show();
	        
	        GraphicsContext graphics = canvas.getGraphicsContext2D();
	        MyShape MyShape = new MyShape(color, this.point1[0], this.point1[1], this.point2[0], this.point2[1]);
	        MyShape.setBoard();
	    }
	
	public MyShape (String col, int x1, int y1, int x2, int y2) {
		this.color = col;
		this.point1[0] = x1;
		this.point2[0] = x2;
		this.point1[1] = y1;
		this.point2[1] = y2;
	}
	
	public String getColor () { 
		return color;
	}
	public int [] getPoint1 () {
		return point1;
	}
	public int [] getPoint2 () {
		return point2;
	}
	
	public void setColor (String c) {
		this.color = c;
	}
	public void setPoint1 (int x, int y) {
		this.point1[0] = x;
		this.point1[1] = y;
	}

	public void setPoint2 (int x, int y) {
		this.point2[0] = x;
		this.point2[1] = y;
	}
	
}

abstract class MyBoundedShape extends MyShape {
	
	private boolean fill;
	
	public MyBoundedShape (String c, boolean fill, int x1, int y1, int x2, int y2) {
		super(c, x1, y1, x2, y2);
		this.fill = fill;
	}
	
	public boolean getFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
}

class MyLine extends MyShape {
	public MyLine (String c, int x1, int y1, int x2, int y2) {
		super(c, x1, y1, x2, y2);
	}
	
	public boolean isEquals(MyLine line1, MyLine line2) {
		if (line2 != null) {
			if (line1.equals(line2)) {
				return true;
			}
		}
		return false;
	}
	//drew the line
}

class MyOval extends MyBoundedShape {
	public MyOval (String color, boolean fill, int x1, int y1, int x2, int y2) {
		super(color, fill, x1, y1, x2, y2);
	}
	//drew oval
}

class MyRectangle extends MyBoundedShape {
	public MyRectangle(String color, boolean fill, int x1, int y1, int x2, int y2){
		super(color, fill, x1, y1, x2, y2);
	}
	//drew rectangle
}