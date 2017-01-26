package model;

import model.data.Level;


public interface Model {


	public Level getLevel();
	public void move(String Move);
	public void save();
	void load(String string);
	void setLevel(Level loadLevel);
	void exit();
}
