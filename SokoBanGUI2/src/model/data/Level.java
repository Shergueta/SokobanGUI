package model.data;

import java.io.Serializable;
import java.util.ArrayList;

import model.policy.Policy;

public class Level implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cell[][] matrix;
	private ArrayList<Player> players= new ArrayList<Player>();
	private Policy policy;
	private int numOfBoxes;
	private int numOfTargets;
	private String level;
	public int steps;
	public int secondsCount;


	public Level() {
	}
	public Policy getPolicy() {
		return policy;
	}


	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getSecondsCount() {
		return secondsCount;
	}
	public void setSecondsCount(int secondsCount) {
		this.secondsCount = secondsCount;
	}

	public Level(String level,Policy policy) {
		this.policy=policy;
		this.level=level;
		int xMax=GetXmax(level);
		int yMax=GetYmax(level);
		matrix= new Cell[xMax][yMax];
		String [] levelRows = level.split("\n");
		for(int i=0;i<levelRows.length;i++)
		{
			for(int j=0;j<levelRows[i].length();j++)
			{
				matrix[j][i]=new Cell();
				switch(levelRows[i].charAt(j))
				{
				case '\n':
				case '\r':
					break;
				case ' ':
					matrix[j][i].cell.add(new Path(new Point(j,i)));
					matrix[j][i].setSymbol(' ');
					break;
				case '#':
					matrix[j][i].cell.add(new Wall(new Point(j,i)));
					matrix[j][i].setSymbol('#');
					break;

				case '@':
					matrix[j][i].cell.add(new Box(new Point(j,i)));
					matrix[j][i].setSymbol('@');

					numOfBoxes++;
					break;

				case 'o':
					matrix[j][i].cell.add(new Target(new Point(j,i)));
					matrix[j][i].setSymbol('o');
					numOfTargets++;
					break;

				case 'A':
					matrix[j][i].cell.add(new Player(new Point(j,i)));
					players.add(new Player(new Point(j,i)));
					matrix[j][i].setSymbol('A');
					break;

				case '$':
					matrix[j][i].cell.add((new Box(new Point(j,i))));
					matrix[j][i].cell.add(new Target(new Point(j,i)));
					matrix[j][i].setSymbol('$');
					numOfBoxes++;
					numOfTargets++;
					break;

				case '!':
					matrix[j][i].cell.add((new Player(new Point(j,i))));
					matrix[j][i].cell.add(new Target(new Point(j,i)));
					players.add(new Player(new Point(j,i)));
					matrix[j][i].setSymbol('!');
					numOfTargets++;
					break;
				default:

				}
			}
		}
	}

	public boolean validateLevel()
	{
		if(numOfBoxes!=numOfTargets)
			return false;
		return true;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}


	public void printToScreen(){

		System.out.println(level);
	}


	public Point PointAfter(Point dest,String direction)
	{
		switch(direction){
		case "up":
			return new Point(dest.getX(),dest.getY()-1);
		case"down":
			return new Point(dest.getX(),dest.getY()+1);
		case "right":
			return new Point(dest.getX()+1,dest.getY());
		case "left":
			return new Point(dest.getX()-1,dest.getY());
		}
		return null;
	}


	public char getSymbolByPoint(Point dest) {
		return(matrix[dest.x][dest.y].getSymbol());
	}

	public int getPositionInArray(ArrayList<GeneralObject> generalObjects,char symbol) {

		for(int i=0;i<generalObjects.size();i++)
		{
			if(generalObjects.get(i).getSymbol()==symbol)
				return i;


		}
		return -1;

	}


	public int getNumOfBoxes() {
		return numOfBoxes;
	}


	public void setNumOfBoxes(int numOfBoxes) {
		this.numOfBoxes = numOfBoxes;
	}


	public int getNumOfTargets() {
		return numOfTargets;
	}


	public void setNumOfTargets(int numOfTargets) {
		this.numOfTargets = numOfTargets;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public void updateString() {
		String newString=new String();
		int x=0,y=0;
		for(int i=0;i<this.level.length();i++)
		{
			if(level.charAt(i)!='\n')
				newString+=getSymbolByPoint(new Point(x++,y));
			else
			{
				newString+='\n';
				x=0;
				y++;
			}
		}
		this.level=newString;
	}

	public int GetXmax(String string){
		int xMax=0;
		int count=0;
		for(int i=0;i<string.length();i++)
		{
			if(string.charAt(i)!='\n')
				count++;
			else
			{
				if(count>xMax)
				{
					xMax=count;
				}
				count=0;
			}
		}
		return xMax;

	}

	public int GetYmax(String string){
		int yMax=0;
		for(int i=0;i<string.length();i++){
			if(string.charAt(i)=='\n')
				yMax++;
		}
		return yMax;

	}


	public Cell[][] getMatrix() {
		return matrix;
	}


	public void setMatrix(Cell matrix[][]) {
		this.matrix = matrix;
	}
}



