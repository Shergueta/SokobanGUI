package commands;
import model.Model;


public class Load extends Command{

	private String string;
	private Model m;

	public Load(Model m) {
		this.m=m;
	}

	public String getType() {
		return string;
	}

	public void setType(String type) {
		this.string = type;
	}


	@Override
	public void execute() {
		this.m.load(this.params.get(0));
	}

	@Override
	public void setState(String s) {

	}


}


