package bean;

import java.io.File;
import java.io.IOException;

public class Note {
	private String filePath = "E:\\作业文档\\";
	private String title;
	private File file = null;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Note(String title,String filePath) {
		this.filePath = filePath;
		this.title = title;
		file = new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("创建文件"+title+"失败");
			}
		}
	}
	public Note() {
		
	}
	@Override
	public String toString() {
		return " "+title;
	}
	
}
