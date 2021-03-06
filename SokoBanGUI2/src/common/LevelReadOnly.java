package common;
import java.io.Serializable;
import model.data.Level;

public class LevelReadOnly implements Serializable {

	private static final long serialVersionUID = 1L;

	public LevelReadOnly() {
	}

	private char[][] matrixRO;
	private int stepsCount;
	private int secondsCount;

	public int getStepsCount() {
		return stepsCount;
	}


	public void setStepsCount(int stepsCount) {
		this.stepsCount = stepsCount;
	}


	public int getSecondsCount() {
		return secondsCount;
	}


	public void setSecondsCount(int secondsCount) {
		this.secondsCount = secondsCount;
	}

	public LevelReadOnly(Level level){


		int x= level.getMatrix()[0].length;
		int y=level.getMatrix().length;
		System.out.println("x= "+x);
		System.out.println("y= "+y);

		matrixRO= new char[x][y];

		for(int i=0;i<y;i++){
			for(int j=0;j<x;j++){
				matrixRO[i][j]=level.getMatrix()[i][j].getSymbol();
			}
		}
	}


	public char[][] getMatrixRO() {
		return matrixRO;
	}

	public void setMatrixRO(char[][] matrixRO) {
		this.matrixRO = matrixRO;
	}




















	/*
	private static final long serialVersionUID = 1L;
public LevelReadOnly() {
}
	private CellReadOnly[][] matrixReadOnly;
	public CellReadOnly[][] getMatrixReadOnly() {
		return matrixReadOnly;
	}


	public void setMatrixReadOnly(CellReadOnly[][] matrixReadOnly) {
		this.matrixReadOnly = matrixReadOnly;
	}


	public int getStepsCount() {
		return stepsCount;
	}


	public void setStepsCount(int stepsCount) {
		this.stepsCount = stepsCount;
	}


	public int getSecondsCount() {
		return secondsCount;
	}


	public void setSecondsCount(int secondsCount) {
		this.secondsCount = secondsCount;
	}
	private int stepsCount;
	private int secondsCount;


	public LevelReadOnly(Level level) {
		this.stepsCount=level.steps;
		this.secondsCount=level.secondsCount;
		int x= level.getMatrix().length;
		int y= level.getMatrix()[0].length;
		matrixReadOnly= new CellReadOnly[x][y];

		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++)
			{
				matrixReadOnly[i][j]=new CellReadOnly(level.getMatrix()[i][j]);

			}
		}

	}
	 */

}
