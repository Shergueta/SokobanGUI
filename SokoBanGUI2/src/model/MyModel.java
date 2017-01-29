package model;

import java.util.LinkedList;
import java.util.Observable;

import commands.ExitReciver;
import commands.LoadFile;
import commands.MoveReciever;
import commands.SaveFile;
import model.data.Level;


public class MyModel extends Observable implements Model {

	private Level level;
	private LoadFile loader;
	private MoveReciever mover;
	private SaveFile saver;

	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public void move(String direction) {
		mover=new MoveReciever(direction,this.level);
		mover.action();
		this.level=mover.getLevel();
		LinkedList<String> params= new LinkedList<String>();
		params.add("DISPLAY");
		if(mover.getDone())
			params.add("done");
		//System.out.println("done");
		setChanged();
		notifyObservers(params);

	}

	@Override
	public void save(String fileName) {

		saver= new SaveFile(this.level, fileName);
		saver.action();
		this.level=saver.getLevel();
		LinkedList<String> params= new LinkedList<String>();
		params.add("SAVE");
		setChanged();
		notifyObservers(params);
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
