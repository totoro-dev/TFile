package top.totoro.file.core;

import java.io.File;
import java.util.LinkedList;

import top.totoro.file.util.Disk;

interface FileProperty {
	
	void setDisk(Disk disk);
	
	Disk getDisk();
	
	void setPath(String path);
	
	String getPath();
	
	void setPaths(LinkedList<String> paths);
	
	LinkedList<String> getPaths();
	
	void setName(String name);
	
	String getName();
	
	void setNames(LinkedList<String> names);
	
	LinkedList<String> getNames();
	
	void setFile(File file);
	
	File getFile();
	
	void setFiles(LinkedList<File> files);
	
	LinkedList<File> getFiles();
	
	void setFlagFile(String flag);
	
	File getFlagFile(String flag);
	
	void removeFlagFile(String flag);
	
	void mkdirs();
	
	boolean exists();
	
	void recycleProperty();
}
