package top.totoro.file.core;

import java.io.File;

import top.totoro.file.util.Disk;

public final class TFile implements FileInitial,FileConfig{
	
	private static final TFile mTFile = new TFile();
	private static final TProperty property = new TProperty();
	private static final TInitialer initialer = new TInitialer(property,mTFile);
	private static final TConfiger configer = new TConfiger(property,mTFile);
	private TFile() {
	}
	
	public static TFile builder() {
		return mTFile;
	}
	
	public static TProperty getProperty() {
		return property;
	}

	@Override
	public TFile setHide() {
		return configer.setHide();
	}

	@Override
	public TFile removeHide() {
		return configer.removeHide();
	}

	@Override
	public TFile setOnlyRead() {
		return configer.setOnlyRead();
	}

	@Override
	public TFile removeOnlyRead() {
		return configer.removeOnlyRead();
	}

	@Override
	public TFile setFlagFile(String flag) {
		return configer.setFlagFile(flag);
	}

	@Override
	public File getFlagFile(String flag) {
		return configer.getFlagFile(flag);
	}

	@Override
	public void removeFlagFile(String flag) {
		configer.removeFlagFile(flag);
	}

	@Override
	public TFile toDisk(Disk disk) {
		return initialer.toDisk(disk);
	}

	@Override
	public TFile toPath(String path) {
		return initialer.toPath(path);
	}

	@Override
	public TFile toPaths(String paths) {
		return initialer.toPaths(paths);
	}

	@Override
	public TFile toName(String name) {
		return initialer.toName(name);
	}

	@Override
	public TFile toNames(String... names) {
		return initialer.toNames(names);
	}

	@Override
	public TFile toFile() {
		return initialer.toFile();
	}

	@Override
	public TFile toFiles() {
		return initialer.toFiles();
	}

	@Override
	public TFile mkdirs() {
		return initialer.mkdirs();
	}

	@Override
	public TFile mkdirsAll() {
		return initialer.mkdirsAll();
	}

	@Override
	public TFile create() {
		return initialer.create();
	}

	@Override
	public TFile createAll() {
		return initialer.createAll();
	}

	@Override
	public TFile clear() {
		return initialer.clear();
	}

	@Override
	public TFile clearAll() {
		return initialer.clearAll();
	}

	@Override
	public TFile delete() {
		return initialer.delete();
	}

	@Override
	public TFile deleteAll() {
		return initialer.deleteAll();
	}

	@Override
	public TFile rename(String name) {
		return initialer.rename(name);
	}

	@Override
	public TFile recycle() {
		property.recycleProperty();;
		return mTFile;
	}
}
