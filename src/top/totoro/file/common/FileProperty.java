package top.totoro.file.common;

import java.io.File;
import java.util.List;

import top.totoro.file.util.Disk;

public interface FileProperty {
	
	void setDisk(Disk disk);
	
	Disk getDisk();
	
	void setPath(String path);
	
	String getPath();
	
	void setPaths(List<String> paths);
	
	List<String> getPaths();
	
	void setName(String name);
	
	String getName();
	
	void setNames(List<String> names);
	
	List<String> getNames();
	
	void setFile(File file);
	
	File getFile();
	
	void setFiles(List<File> files);
	
	List<File> getFiles();
	
	void mkdirs();
}
