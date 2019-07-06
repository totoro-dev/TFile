package top.totoro.file.common;

import java.io.File;
import java.util.List;

import top.totoro.file.util.Disk;

public class TFileProperty implements FileProperty{
	
	private Disk disk;
	private String path;
	private List<String> paths;
	private String name;
	private List<String> names;
	private File file;
	private List<File> files;

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
	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	@Override
	public List<String> getPaths() {
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
	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public List<String> getNames() {
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
	public void setFiles(List<File> files) {
		this.files = files;
	}

	@Override
	public List<File> getFiles() {
		return files;
	}

	@Override
	public void mkdirs() {
		if (file != null) {
			file.mkdirs();
		}
	}
}
