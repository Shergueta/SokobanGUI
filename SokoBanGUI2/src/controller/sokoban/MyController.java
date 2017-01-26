package controller.sokoban;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import commands.Command;
import commands.Display;
import commands.Exit;
import commands.Load;
import commands.Move;
import controller.general.Controller;
import model.Model;
import view.view;

public class MyController implements Observer {

	Model m;
	view v;
	private Controller controller;
	private HashMap<String, Command> commands;


	public void start(){

	}

	public MyController(Model m, view v) {
		this.m=m;
		this.v=v;
		controller= new Controller();
		controller.start();

		initCommands();
	}

	public void initCommands(){
		commands=new HashMap<String,Command>();
		commands.put("LOAD", new Load(m));
		commands.put("MOVE", new Move(m));
		commands.put("DISPLAY", new Display(v,m));
		commands.put("EXIT", new Exit(v,m));
	}

	@Override
	 public void update(Observable o, Object arg) {
        LinkedList<String> params = (LinkedList<String>) arg;
        for (String string : params) {
		}
        String commandKey = params.removeFirst();
        Command c = commands.get(commandKey.toUpperCase());
        if (c == null) {
            v.displayMessage("Error");
            return;
        }
        else if(commandKey=="EXIT")
    		//System.out.println("im here");

        //(c.equals("EXIT"))
        	controller.stop();
        c.setParams(params);
        controller.insertCommand(c);

    }

	}


