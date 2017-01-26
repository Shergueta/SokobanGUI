package view;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;

import commands.Exit;
import commands.ExitReciver;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.data.Level;

public class SampleController extends Observable implements view {

	private String command;
	ExitReciver exit;
	@FXML
	SokoDisplay sokoDisplayer;

	public void setCommand(String command)
	{

		LinkedList<String> commands =new LinkedList<>();
		String[] arr= command.split(" ");
		
		Collections.addAll(commands,arr);
		this.setChanged();
		this.notifyObservers(commands);
	}


	public SampleController(){
		this.command="";
		sokoDisplayer = new SokoDisplay();
	}

	public SampleController(Level level) {
		this.command="";
		sokoDisplayer = new SokoDisplay(level);
		display(level);
	}

	public void openFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("open level file");
		fc.setInitialDirectory(new File("./resources"));
		// fc.setSelectedExtensionFilter(new ExtensionFilter(arg0, arg1));
		File choosen = fc.showOpenDialog(null);
		if (choosen != null) {
			LinkedList<String> list = new LinkedList<String>();
			list.add("load");
			list.add(choosen.getPath());
			setChanged();
			notifyObservers(list);

		}

	}

	public void exit() {

		exit= new ExitReciver();
		exit.action();
		LinkedList<String> params= new LinkedList<String>();
		params.add("EXIT");
		setChanged();
		notifyObservers(params);
	}

	@Override
	public void display(Level level) {
		sokoDisplayer.setLevel(level);
		sokoDisplayer.setFocusTraversable(true);
		sokoDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.UP)
					setCommand("move up");
				if(event.getCode()==KeyCode.DOWN)
					setCommand("move down");
				if(event.getCode()==KeyCode.RIGHT)
					setCommand("move right");
				if(event.getCode()==KeyCode.LEFT)
					setCommand("move left");

			}

		});

	}
	@Override
	public void displayMessage(String msg) {
		System.out.println(msg);
		System.out.flush();
	}

	@Override
	public void start() {
		//this.sokoDisplayer.redraw();
	}




}




