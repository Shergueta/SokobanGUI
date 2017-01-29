package controller.general;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import commands.Command;

public  class Controller {

	private BlockingQueue<Command> q;
	private boolean isStoped=false;

	public Controller() {
		q= new ArrayBlockingQueue<Command>(10);
	}

	public void insertCommand(Command c){
		try {
			q.put(c);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start(){
		Thread thread= new Thread(new Runnable() {

			public void run() {
				while(!isStoped)
				{

					Command cmd;
					try {
						cmd = q.poll(1,TimeUnit.SECONDS);
						if(cmd!=null)
							cmd.execute();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}

			}
		});thread.start();
			}

	public void stop(){
		isStoped=true;
	}
}
