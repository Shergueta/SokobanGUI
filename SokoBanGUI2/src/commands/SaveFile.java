package commands;

import java.io.OutputStream;
import java.util.HashMap;

import model.data.GeneralLevelSaver;
import model.data.Level;
import model.data.SaveObj;
import model.data.SaveTxt;
import model.data.SaveXml;

public class SaveFile extends Receiver {

	private Level level;
	//private OutputStream outputStream;
	private String fileName;
	private String type;

	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SaveFile(Level level,String fileName) {

		this.level=level;
		this.fileName=fileName;

	}
	@Override
	public void action() {
		setState(fileName);
		HashMap<String, GeneralLevelSaver> fileTypes=new HashMap<String, GeneralLevelSaver>();
		fileTypes.put("txt", new SaveTxt(this.level,this.fileName));
		fileTypes.put("xml", new SaveXml(this.level,this.fileName));
		fileTypes.put("obj", new SaveObj(this.level,this.fileName));
		GeneralLevelSaver gls=fileTypes.get(type);
		gls.saveLevel();
	}

	public void setState(String fileName) {
		this.type= fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
	}}
