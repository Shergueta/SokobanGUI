package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;

import commands.Command;
import common.LevelReadOnly;
import model.data.Level;

public class MyView extends Observable implements view  {


	private BufferedReader reader;
	private PrintWriter writer;
	private String exitString;

	public MyView(BufferedReader reader, PrintWriter writer, String exitString) {
		this.reader = reader;
		this.writer = writer;
		this.exitString = exitString;
	}



	@Override
	public void displayMessage(String msg) {
		System.out.println("Error: " + msg);
	}

	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String commandLine = "";
				do {
					writer.print("Enter command: \n");
					writer.flush();
					try {
						commandLine = reader.readLine();
						String[] arr = commandLine.split(" ");
						LinkedList<String> params = new LinkedList<String>();
						for (String param : arr) {
							params.add(param);
						}
						setChanged();
						notifyObservers(params);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (!commandLine.equals(exitString));
			}
		}).start();
	}





	@Override
	public void display(Level level) {
		// TODO Auto-generated method stub

	}



	@Override
	public void moveMade() {
		// TODO Auto-generated method stub

	}








	}





