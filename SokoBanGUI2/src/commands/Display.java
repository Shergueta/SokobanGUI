package commands;

import model.Model;
import model.data.Level;
import view.view;

public class Display extends Command {
	Model m;
	view v;
	Level level;

	public Display(view v, Model m) {
		this.m=m;
		this.v=v;
	}
	public void setState(String s) {

	}
	@Override

	public void execute() {
		this.level=m.getLevel();
		v.display(this.level);
	}

}
