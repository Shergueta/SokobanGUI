package model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Cell implements Serializable{

	private static final long serialVersionUID = 1L;


	public ArrayList<GeneralObject> cell;
	public char symbol;


	public Cell() {
		this.cell = new ArrayList<GeneralObject>();

	}

	public ArrayList<GeneralObject> getCell() {
		return cell;
	}

	public void setCell(ArrayList<GeneralObject> cell) {
		this.cell = cell;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getPositionInArray(ArrayList<GeneralObject> cell,char symbol) {
		for(int i=0;i<cell.size();i++)
		{
			if(cell.get(i).getSymbol()==symbol)
				return i;
		}
		return -1;

	}


}
