package model.data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class SaveXml extends GeneralLevelSaver {


	public SaveXml(Level level,String fileName) {
		this.level=level;
		this.fileName=fileName;

	}
	@Override

	public void saveLevel() {

		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(new File(this.fileName))));
			encoder.writeObject(this.level);
			encoder.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
