package commands;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import model.Model;
import model.data.Level;


public class Save  extends Command{

	private Model m;
	String fileName;
	String fileType;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Save(Model model) {
		this.m=model;
	}

	@Override
	public void execute() {

		this.m.save(fileName);
	}

	@Override
	public void setState(String fileName) {
		this.fileType= fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());

	}}


