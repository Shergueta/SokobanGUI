package model.data;


import java.io.Serializable;


public class Target extends GeneralObject implements Serializable{

	private static final long serialVersionUID = 1L;

	public Target() {
	}
	public Target(Point p) {

		this.p=p;
	}

	int numOfTargets;

	public int getNumOfTargets() {
		return numOfTargets;
	}

	public void setNumOfTargets(int numOfTargets) {
		this.numOfTargets = numOfTargets;
	}


	@Override
	public char getSymbol() {

		return 'o';
	}




}
