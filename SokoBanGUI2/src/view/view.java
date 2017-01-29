package view;

import commands.Command;
import common.LevelReadOnly;
import model.data.Level;

public interface view {

	public void display(Level level);

	public void start();

	public void moveMade();

	public void levelFinshed();

	public void displayMessage(String msg);

	//public void setLevel(LevelReadOnly level);
}
