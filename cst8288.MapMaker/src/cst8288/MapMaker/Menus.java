package cst8288.MapMaker;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Menus {
	
	public static final String CSS_PATH = "resources/css/style.css";
	public static final String INFO_PATH = "resources/icons/info.txt";
	public static final String HELP_PATH = "resources/icons/help.txt";
	public static final String CREDITS_PATH = "resources/icons/credits.txt";
	
	private static void displayCredit() {
		displayAlert("Credit", loadFile(CREDITS_PATH));
	}
	
	private static void displayInfo() {
		displayAlert("Info", loadFile(INFO_PATH));
	}
	
	private static void displayHelp() {
		displayAlert("Info", loadFile(HELP_PATH));
	}
	
	private static String loadFile(String path) {
		String message = "";
		try {
			message = Files.lines(Paths.get(path)).reduce(message, (a,b) -> a+System.lineSeparator() + b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	static MenuItem createMenuItem(String name, EventHandler<ActionEvent> handler) {
		Label icon = new Label();
		icon.setId(name+"-icon");
		MenuItem item = new MenuItem(name, icon);
		item.setOnAction(handler);
		item.setId(name);
		return item;
	}
	
	private static void displayAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.show();
	}
	
	public static MenuBar getMenuBar() {
		MenuBar menuBar = new MenuBar(
				new Menu("File", null, createMenuItem("New", (e) -> {}),
										createMenuItem("Save", (e) -> {}),
										new SeparatorMenuItem(),
										createMenuItem("Exit", (e) -> {})),
				new Menu("Help", null, createMenuItem("Credit", (e) -> displayCredit()),
										createMenuItem("Info", (e) -> displayInfo()),
										new SeparatorMenuItem(),
										createMenuItem("Help", (e) -> displayHelp())
				));
		return menuBar;
		}

	}
