package top.totoro.file.core;

import top.totoro.file.util.Disk;

public final class TFile implements FileInitial,FileCommon,FileConfig{
	
	private static final TFile mTFile = new TFile();
	private static final TProperty property = new TProperty();
	private static final TInitialer initialer = new TInitialer(property,mTFile);
	private static final TCommoner commoner = new TCommoner(property,mTFile);
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
	public TFile setFlag(String flag) {
		return initialer.setFlag(flag);
	}

	@Override
	public void removeFlag(String flag) {
		initialer.removeFlag(flag);
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
		return commoner.mkdirs();
	}

	@Override
	public TFile mkdirsAll() {
		return commoner.mkdirsAll();
	}

	@Override
	public TFile create() {
		return commoner.create();
	}

	@Override
	public TFile createAll() {
		return commoner.createAll();
	}

	@Override
	public TFile clear() {
		return commoner.clear();
	}

	@Override
	public TFile clearAll() {
		return commoner.clearAll();
	}

	@Override
	public TFile delete() {
		return commoner.delete();
	}

	@Override
	public TFile deleteAll() {
		return commoner.deleteAll();
	}

	@Override
	public TFile rename(String name) {
		return commoner.rename(name);
	}

	@Override
	public TFile recycle() {
		property.recycleProperty();;
		return mTFile;
	}
}
