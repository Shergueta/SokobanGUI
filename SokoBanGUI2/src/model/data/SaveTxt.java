package model.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveTxt extends GeneralLevelSaver {



	public SaveTxt(Level level, String fileName) {
		this.level=level;
		this.fileName=fileName;

	}
	@Override
	public void saveLevel() {
		try {
			OutputStream o= new FileOutputStream(new File(fileName));
			o.write(level.getLevel().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("dont forget to close the file!");

	}

}
