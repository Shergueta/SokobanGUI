package commands;

import model.data.Box;
import model.data.Level;
import model.data.Path;
import model.data.Player;
import model.data.Point;

public class MoveReciever extends Receiver{

	public String direction;
	public Level level;
	boolean done=false;
	int boxNtarget;

	public boolean levelIsFinished(){

		if(level.getNumOfBoxes()==this.boxNtarget)
			return true;
		return false;
	}

	public boolean getDone(){
		return this.done;
	}

	public MoveReciever(String direction,Level level){
		this.direction=direction;
		this.level=level;
	}
	public Point getSourcePoint(){

		return level.getPlayers().get(0).getP();

	}

	public Point getDestinationPoint()	{
		Point destPoint;
		int x;
		int y;
		switch (this.direction){
		case "up":
			y=this.getSourcePoint().getY();
			y--;
			destPoint= new Point(getSourcePoint().getX(),y);
			return destPoint;

		case "down":
			y=this.getSourcePoint().getY();
			y++;
			destPoint= new Point(getSourcePoint().getX(),y);
			return destPoint;

		case "right":

			x=this.getSourcePoint().getX();
			x++;
			destPoint= new Point(x,this.getSourcePoint().getY());
			return destPoint;

		case "left":
			x=this.getSourcePoint().getX();
			x--;
			destPoint= new Point(x,this.getSourcePoint().getY());
			return destPoint;
		}
		return null;
	}

	public boolean checkBoxMove(Point newDest){
		char newDestSymbol = level.getSymbolByPoint(newDest);
		if(newDestSymbol=='$'||newDestSymbol=='@'||newDestSymbol=='!'||newDestSymbol=='A'||newDestSymbol=='#')
			return level.getPolicy().pushBlockedBox();
		return true;
	}

	public boolean checkMove(Point source,Point dest,String direction){
		char destSymbol = level.getSymbolByPoint(dest);
		if(destSymbol=='#')
			return this.level.getPolicy().passThroughWall();

		if((destSymbol=='@')||(destSymbol=='$'))
		{
			switch(direction){
			case "up":
				return checkBoxMove(new Point(dest.getX(),dest.getY()-1));
			case "down":
				return checkBoxMove(new Point(dest.getX(),dest.getY()+1));
			case "right":
				return checkBoxMove(new Point(dest.getX()+1,dest.getY()));
			case "left":
				return checkBoxMove(new Point(dest.getX()-1,dest.getY()));
			}

		}
		if(destSymbol=='!'||destSymbol=='A')
		{

			return false;
		}
		return true;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction=direction;
	}
	public Level getLevel() {
		return this.level;
	}

	public void setLevel(Level level) {
		this.level=level;
	}

	@Override
	public void action() {

		boolean check;
		check=checkMove(this.getSourcePoint(),getDestinationPoint(),this.direction);
		if (check)
			moveItem(this.getSourcePoint(),this.direction, getDestinationPoint());
	}

	public void  moveItem(Point currentPoint, String direction,Point newPoint) {
		this.boxNtarget=this.level.getNumBoxNtarget();
		done=true;
		int numOfSteps= level.getSteps();
		level.setSteps(++numOfSteps);

		Point pointAfter = this.level.PointAfter(newPoint, direction);
		char currentChar = level.getSymbolByPoint(currentPoint);
		char newChar = level.getSymbolByPoint(newPoint);
		int x=currentPoint.getX();
		int y=currentPoint.getY();
		int newX=newPoint.getX();
		int newY=newPoint.getY();
		int xAfter=pointAfter.getX();
		int yAfter=pointAfter.getY();


		switch(currentChar){
		case '!':
			this.level.getMatrix()[x][y].getCell().remove(level.getPositionInArray(level.getMatrix()[x][y].getCell(), 'A'));
			this.level.getMatrix()[x][y].getCell().add(new Path(new Point(x,y)));
			this.level.getMatrix()[x][y].setSymbol('o');
			this.level.getPlayers().get(0).setP(new Point(newX,newY));
			currentPoint.set(newPoint);
			break;
		case 'A':
			this.level.getMatrix()[x][y].getCell().remove(level.getPositionInArray(level.getMatrix()[x][y].getCell(), 'A'));
			this.level.getMatrix()[x][y].getCell().add(new Path(new Point(x,y)));
			this.level.getMatrix()[x][y].setSymbol(' ');
			this.level.getPlayers().get(0).setP(new Point(newX,newY));
			currentPoint.set(newPoint);

			break;
		}
		switch(newChar){
		case '$':
			this.level.getMatrix()[newX][newY].getCell().remove(level.getPositionInArray(level.getMatrix()[newX][newY].getCell(), '@'));
			this.level.getMatrix()[xAfter][yAfter].getCell().add(new Box(new Point(xAfter,yAfter)));
			this.level.getMatrix()[xAfter][yAfter].setSymbol('@');
			this.level.setNumBoxNtarget(--this.boxNtarget);
			System.out.println("NumBoxNtarget"+this.boxNtarget);


			this.level.getMatrix()[newX][newY].getCell().add(new Player(new Point(newX,newY)));
			this.level.getMatrix()[newX][newY].setSymbol('!');
			break;
		case '@':
			this.level.getMatrix()[newX][newY].getCell().remove(level.getPositionInArray(level.getMatrix()[newX][newY].getCell(), '@'));
			this.level.getMatrix()[xAfter][yAfter].getCell().add(new Box(new Point(xAfter,yAfter)));
			if(this.level.getMatrix()[xAfter][yAfter].getSymbol()=='o')
			{
				this.level.getMatrix()[xAfter][yAfter].setSymbol('$');
				this.level.setNumBoxNtarget(++this.boxNtarget);
				System.out.println("NumBoxNtarget"+this.boxNtarget);


			}
			else
				this.level.getMatrix()[xAfter][yAfter].setSymbol('@');
			this.level.getMatrix()[newX][newY].getCell().add(new Player(new Point(newX,newY)));
			this.level.getMatrix()[newX][newY].setSymbol('A');
			break;
		case 'o':
			this.level.getMatrix()[newX][newY].getCell().add(new Player(new Point(newX,newY)));
			this.level.getMatrix()[newX][newY].setSymbol('!');
			break;
		case ' ':
			this.level.getMatrix()[newX][newY].getCell().remove(level.getPositionInArray(level.getMatrix()[newX][newY].getCell(), ' '));
			this.level.getMatrix()[newX][newY].getCell().add(new Player(new Point(newX,newY)));
			this.level.getMatrix()[newX][newY].setSymbol('A');
			break;
		}
		this.level.updateString();
	}
}
