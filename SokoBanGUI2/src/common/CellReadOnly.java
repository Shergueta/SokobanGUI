package common;
import java.io.Serializable;

import model.data.Cell;
import model.data.GeneralObject;

public class CellReadOnly implements Serializable{

	private static final long serialVersionUID = 1L;

	public CellReadOnly() {
	}
	private GeneralObject[] array;
	private char symbol;

	public CellReadOnly(Cell myCell) {

		array=myCell.getCell().toArray(new GeneralObject[myCell.getCell().size()]);
		symbol=myCell.getSymbol();
	}

	public GeneralObject[] getArray() {
		return array;
	}

	public void setArray(GeneralObject[] array) {
		this.array = array;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}


}

