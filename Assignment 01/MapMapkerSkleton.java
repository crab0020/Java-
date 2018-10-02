import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MapMapkerSkleton extends Application{

	public static final String CSS_PATH = "resources/css/style.css";
	public static final String INFO_PATH = "resources/icons/info.txt";
	public static final String HELP_PATH = "resources/icons/help.txt";
	public static final String CREDITS_PATH = "resources/icons/credits.txt";

	@Override
	public void init() throws Exception{
		super.init();
	}

	@Override
	public void start( Stage primaryStage) throws Exception{
		BorderPane rootPane = new BorderPane();

		MenuBar menuBar = new MenuBar(
				new Menu( "File", null,
						createMenuItem( "Save", ( e) -> {}),
						createMenuItem( "New", ( e) -> {}),
						new SeparatorMenuItem(),
						createMenuItem( "Exit", ( e) -> {})),
				new Menu( "Help", null,
						createMenuItem( "Credit", ( e) -> displayCredit()),
						createMenuItem( "Info", ( e) -> {}),
						new SeparatorMenuItem(),
						createMenuItem( "Help", ( e) -> {})));

		rootPane.setTop( menuBar);

		Scene scene = new Scene( rootPane, 800, 600);
		scene.getStylesheets().add( new File( CSS_PATH).toURI().toString());
		primaryStage.addEventHandler( KeyEvent.KEY_RELEASED,
				e -> {
					if( e.getCode() == KeyCode.ESCAPE)
						primaryStage.hide();
				});
		primaryStage.setScene( scene);
		primaryStage.setTitle( "Map Maker Skeleton");
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception{
		super.stop();
	}

	private MenuItem createMenuItem( String name, EventHandler< ActionEvent> handler){
		Label icon = new Label();
		icon.setId( name + "-icon");
		MenuItem item = new MenuItem( name, icon);
		item.setId( name);
		item.setOnAction( handler);
		return item;
	}

	private void displayAlert( String title, String header, String content){
		Alert alert = new Alert( AlertType.INFORMATION);
		alert.setTitle( title);
		alert.setHeaderText( header);
		alert.setContentText( content);
		alert.show();
	}

	private String loadFile( String path){
		String str = "";
		try{
			// str = Files.lines(Paths.get(path)).reduce(str,(String a, String b)-> {return a+b;});
			str = Files.lines( Paths.get( path)).reduce( str, ( a, b) -> a + System.lineSeparator() + b);
		}catch( IOException e){
			e.printStackTrace();
		}
		return str;
	}

	private void displayCredit(){
		displayAlert( "Credit", "Resource Credits", loadFile( CREDITS_PATH));
	}

	public static void main( String[] args){
		launch( args);
	}
}
