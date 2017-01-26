package commands;

import model.Model;

public class Move extends  Command {

	private String direction;
	private Model model;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Move(Model model){
		this.model=model;
	}


	public void execute() {
		this.direction= params.get(0);
		//System.out.println(direction);

		this.model.move(direction);
	}

	@Override
	public void setState(String s) {
		// TODO Auto-generated method stub

	}


}




