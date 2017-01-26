package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.data.Level;

public class SokoDisplay extends Canvas {

	//private LevelReadOnly levelReadOnly;
	private Level level;


	private HashMap<Character, Image> objectsImage;

	public SokoDisplay()
	{
		setLevel(null);
	}


	public SokoDisplay(Level level) {
		setLevel(level);
	}

	public void setLevel(Level level) {
		this.level = level;
		redraw();
	}

	public Level getLevel() {
		return level;
	}


	public void redraw(){
		if(this.level!=null && !this.level.validateLevel())
		{
			System.out.println("Invalid level");
			return;
		}
		try {
			Image wall=new Image(new FileInputStream(".//resources//wall.jpg"));
			Image player=new Image(new FileInputStream(".//resources//player.png"));
			Image box=new Image(new FileInputStream(".//resources//box.png"));
			Image target=new Image(new FileInputStream(".//resources//targrt.png"));
			Image boxNtarget= new Image(new FileInputStream(".//resources//boxNtarget.png"));
			Image playerNtarget=new Image(new FileInputStream(".//resources//playerNtarget.png"));
			Image floor=new Image(new FileInputStream(".//resources//floor.png"));

			objectsImage= new HashMap<Character,Image>();
			objectsImage.put('@', box);
			objectsImage.put('A', player);
			objectsImage.put('o', target);
			objectsImage.put(' ',floor);
			objectsImage.put('#', wall);
			objectsImage.put('!', playerNtarget);
			objectsImage.put('$', boxNtarget);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		char key;
		if(level==null)
			return;
		int longestrow=0;
		for(int i=0;i<level.getMatrix().length;i++)
			if(level.getMatrix()[i].length>longestrow){
				longestrow=level.getMatrix()[i].length;
			}

		double W = getWidth();
		double H = getHeight();
		double w=W/this.level.GetXmax(this.level.getLevel());
		double h=H/this.level.GetYmax(this.level.getLevel());

		/*double w =W/this.level.GetXmax(this.level.getLevel());
		double h =H/this.level.GetYmax(this.level.getLevel());*/
		//System.out.println(this.level.GetXmax(this.level.getLevel());
		//double size = W / Math.max(this.level.getMatrix().length, this.level.getMatrix()[0].length);

		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, W, H);
		gc.setFill(Color.WHITESMOKE);
		gc.fillRect(0, 0, W, H);
//		System.out.println(this.level.getMatrix().length);
	//	System.out.println(this.level.getMatrix()[0].length);
		for(int i=0;i<this.level.GetYmax(this.level.getLevel());i++){
			for(int j=0;j<this.level.GetXmax(this.level.getLevel());j++){
				if(this.level.getMatrix()[j][i]!=null){
					key=this.level.getMatrix()[j][i].getSymbol();
					gc.drawImage(this.objectsImage.get(key),j*w, i*h,w,h);

				}
			}
		}
	}
}
//Scanner scanner=new Scanner(this.level.getLevel());
//		int x=0;
//		int y=0;
//
//		double W = getWidth();
//		double H = getHeight();
//		double w =W/this.level.GetXmax(this.level.getLevel());
//		double h =H/this.level.GetYmax(this.level.getLevel());
//		GraphicsContext gc = getGraphicsContext2D();
//		gc.clearRect(0, 0, W, H);
//		gc.setFill(Color.WHITESMOKE);
//		gc.fillRect(0, 0, W, H);
//
//		while(scanner.hasNextLine())
//			{
//			String line=scanner.nextLine();
//			Scanner lineScaner=new Scanner(line);
//			while(lineScaner.hasNext())
//			{
//				key=this.level.getMatrix()[x][y].getSymbol();
//				System.out.print(key);
//				gc.drawImage(this.objectsImage.get(key), x, y);
//				x++;
//			}
//			System.out.println();
//			y++;
//			x=0;
//			}

