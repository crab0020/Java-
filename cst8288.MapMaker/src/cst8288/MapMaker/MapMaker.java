package cst8288.MapMaker;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MapMaker extends Application {
	
	
	@Override
	public void init() throws Exception {
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane mapMakerWindow = new BorderPane();
		mapMakerWindow.setCenter(MapArea.getMapArea());
		mapMakerWindow.setTop(Menus.getMenuBar());
		mapMakerWindow.setLeft(ToolBars.getToolsBar());
		mapMakerWindow.setBottom(ToolBars.getStatusBar());
		mapMakerWindow.setRight(DetailsEditor.getRightPanel());
		
		Scene scene = new Scene(mapMakerWindow, 1050, 650);
		scene.getStylesheets().add(new File("resources/css/style.css").toURI().toString());
		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, 
				e -> {
						if(e.getCode() == KeyCode.ESCAPE)
						primaryStage.hide();
					});
		primaryStage.setScene(scene);
		primaryStage.setTitle("Map Maker");
		primaryStage.show();
	}
		
	@Override
	public void stop() throws Exception {
			super.stop();
	}
	
	public static void main (String [] args) {
		launch(args);
	}
}
