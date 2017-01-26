package model.data;

import java.io.InputStream;
 

public  abstract class GeneralLevelLoader implements LevelLoader {

	Level level;
	InputStream inputStream;
	public abstract Level loadLevel();
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
		

}
