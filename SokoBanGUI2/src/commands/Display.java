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

		if(!params.isEmpty())
		{
			if(params.size()==1)
			{
				v.moveMade();
				v.display(level);
			}
			if(params.size()==2)
			{
				v.moveMade();
				v.display(level);
				v.levelFinshed();
			}

		}
		v.display(this.level);
	}

}
