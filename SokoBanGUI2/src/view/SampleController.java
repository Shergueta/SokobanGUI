package view;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import commands.ExitReciver;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Level;

public class SampleController extends Observable implements view {


	private String command;
	ExitReciver exit;

	private int timercount;
	private int steps=0;


	@FXML
	Text timer;

	@FXML
	Text stepscount;


	private StringProperty timerCounter;
	private IntegerProperty stepsCounter;

	@FXML
	SokoDisplay sokoDisplayer;

	private String mp3path= "./music/start.mp3";
	private String finishpath="./music/finished.mp3";

	private Media mp3=new Media(new File(mp3path).toURI().toString());
	private Media finish=new Media(new File(finishpath).toURI().toString());
	private MediaPlayer player=new MediaPlayer(mp3);
	private MediaPlayer finished=new MediaPlayer(finish);



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

	    timerCounter=new SimpleStringProperty();
        stepsCounter=new SimpleIntegerProperty();

        timer.textProperty().bind(timerCounter);
        stepscount.textProperty().bind(stepsCounter.asString());

		setTimer(0);
		steps=0;
		this.stepsCounter.set(steps);
		sokoDisplayer.clearCanvas();

		FileChooser fc = new FileChooser();
		fc.setTitle("open level file");
		fc.setInitialDirectory(new File("./resources"));
		File choosen = fc.showOpenDialog(null);
		if (choosen != null) {
			LinkedList<String> list = new LinkedList<String>();
			list.add("load");
			list.add(choosen.getPath());
			setChanged();
			notifyObservers(list);
			finished.stop();
			player.play();
			startTimer();
			sokoDisplayer.requestFocus();

		}

	}

	public void saveFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("save level file");
		fc.setInitialDirectory(new File("./"));
		File choosen = fc.showSaveDialog(null);
		if (choosen != null) {
			sokoDisplayer.requestFocus();
			LinkedList<String> list = new LinkedList<String>();
			list.add("save");
			list.add(choosen.toString());
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
		Platform.exit();
		System.exit(0);
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


	public void playMusic()
	{
		player.play();
	}
	public void stopMusic()
	{
		player.stop();
		finished.stop();
	}

	private  void startTimer()
	{
		Timer t=new  Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				setTimer(getTimer()+1);
			}
		}, 0, 1000);
	}

	private void setTimer(int num) {
		this.timercount = num;
		this.timerCounter.set(""+num);
	}
	private int getTimer(){return this.timercount;}



	@Override
	public void displayMessage(String msg) {
		System.out.println(msg);
		System.out.flush();
	}

	@Override
	public void start() {
		//this.sokoDisplayer.redraw();
	}


	@Override
	public void moveMade() {

		steps++;
		this.stepsCounter.set(steps);
	}




}




