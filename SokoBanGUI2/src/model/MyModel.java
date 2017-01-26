package model;

import java.util.LinkedList;
import java.util.Observable;

import commands.ExitReciver;
import commands.LoadFile;
import commands.MoveReciever;
import model.data.Level;


public class MyModel extends Observable implements Model {

	private Level level;
	private LoadFile loader;
	private MoveReciever mover;

	@Override
	public Level getLevel() {///להמיר פה את השלב לריד אונלי
		return this.level;
	}

	@Override
	public void move(String direction) {
		mover=new MoveReciever(direction,this.level);
		mover.action();
		this.level=mover.getLevel();
		LinkedList<String> params= new LinkedList<String>();
		params.add("DISPLAY");
		setChanged();
		notifyObservers(params);

	}

	@Override
	public void save() {
		//		LinkedList<String> params=new LinkedList<String>();
		//		params.add("load");
		//		params.add("stuff.txt");
		//		setChanged();
		//		notifyObservers(params);
	}

	@Override
	public void load(String string) {
		loader= new LoadFile(string);
		loader.action();
		this.level=loader.getLevel();
		LinkedList<String> params= new LinkedList<String>();
		params.add("DISPLAY");
		setChanged();
		notifyObservers(params);
	}

	public void setLevel(Level level){
		this.level=level;
	}

	@Override
	public void exit() {
	/*	exit= new ExitReciver();
		exit.action();
		LinkedList<String> params= new LinkedList<String>();
		params.add("EXIT");
		setChanged();
		notifyObservers(params);
	}*/

}
}
