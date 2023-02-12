import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{ 
	
	private String color;
	private int [] point1 = new int[2];
	private int [] point2 = new int[2];
	
	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("MyShape.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("MyShape"); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	
	public static void main(String[] args) { 
		launch(args); 
		System.out.println();
	}
}
