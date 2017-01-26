package commands;

import model.Model;

import view.view;

public class Exit extends Command {

	Model model;
	view view;

	public Exit(view view, Model model) {
		this.view=view;
		this.model=model;
	}

	@Override
	public void execute() {
		this.model.exit();
	}

	@Override
	public void setState(String s) {



	}

}
