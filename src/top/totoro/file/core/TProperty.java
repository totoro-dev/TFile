package top.totoro.file.core;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import top.totoro.file.util.Disk;

public final class TProperty implements FileProperty {

	private Disk disk;
	private String path;
	private LinkedList<String> paths;
	private String name;
	private LinkedList<String> names;
	private File file;
	private LinkedList<File> files;
	private final Map<String, File> flagFiles = new HashMap<>();

	@Override
	public void recycleProperty() {
		recycle();
	}

	@Override
	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	@Override
	public Disk getDisk() {
		return disk;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPaths(LinkedList<String> paths) {
		this.paths = paths;
	}

	@Override
	public LinkedList<String> getPaths() {
		return paths;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setNames(LinkedList<String> names) {
		this.names = names;
	}

	@Override
	public LinkedList<String> getNames() {
		return names;
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void setFiles(LinkedList<File> files) {
		this.files = files;
	}

	@Override
	public LinkedList<File> getFiles() {
		return files;
	}

	@Override
	public void setFlagFile(String flag) {
		if (flag != null && file != null) {
			this.flagFiles.put(flag, file);
		}
	}
	
	@Override
	public File getFlagFile(String flag) {
		return flagFiles.get(flag);
	}
	
	@Override
	public void removeFlagFile(String flag) {
		flagFiles.remove(flag);
	}
	
	@Override
	public void mkdirs() {
		if (file != null) {
			file.mkdirs();
		}
	}

	@Override
	public boolean exists() {
		return file != null && file.exists();
	}

	private void recycle() {
		disk = null;
		path = null;
		paths = null;
		name = null;
		names = null;
		file = null;
		files = null;
		flagFiles.clear();
	}
}
