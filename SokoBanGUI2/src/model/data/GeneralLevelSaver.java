package model.data;

import java.io.OutputStream;

public abstract class GeneralLevelSaver implements LevelSaver {

	public Level level;
	public String fileName;
	@Override
	public abstract void saveLevel();

}
