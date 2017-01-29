package model.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveObj extends GeneralLevelSaver {


	public SaveObj(Level level,String fileName) {
		this.fileName=fileName;
		this.level=level;
	}
	@Override
	public void saveLevel() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(this.fileName)));
			out.writeObject(level);
			out.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}


}
