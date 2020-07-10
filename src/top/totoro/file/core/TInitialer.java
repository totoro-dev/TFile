package top.totoro.file.core;

import java.io.File;
import java.util.LinkedList;

import top.totoro.file.sys.SystemProperties;
import top.totoro.file.util.Disk;
import top.totoro.file.util.DiskChose;
import top.totoro.file.util.TException;

/**
 * 创建、删除、清空、重命名一个文件所要做的初始化和基本操作
 * 比如：指定文件所在盘符；文件目录；文件名。。。
 * @author 黄龙三水
 *
 */
public final class TInitialer implements FileInitial {

	private FileProperty mFileProperty;
	private TFile mTFile;
	private String mSeparator = SystemProperties.getSystem().getFileSeparator();

	public TInitialer(FileProperty property) {
		this(property, null);
	}

	public TInitialer(FileProperty fileProperty, TFile t) {
		mFileProperty = fileProperty;
		mTFile = t;
	}

	public FileProperty getFileProperty() {
		return mFileProperty;
	}

	@Override
	public TFile recycle() {
		mFileProperty.recycleProperty();
		return mTFile;
	}

	@Override
	public TFile toDisk(Disk disk) {
		mFileProperty.setDisk(disk);
		return mTFile;
	}

	@Override
	public TFile toPath(String path) {
		mFileProperty.setPath(DiskChose.disk(mFileProperty.getDisk()) + this.path(path, ","));
		// mFileProperty.setFile(new File(mFileProperty.getPath()));
		return mTFile;
	}

	@Override
	public TFile toPaths(String paths) {
		String[] ps = paths.split(";");
		LinkedList<String> mPathList = new LinkedList<>();
		for (int i = 0; i < ps.length; i++) {
			mPathList.add(DiskChose.disk(mFileProperty.getDisk()) + path(ps[i], ","));
		}
		mFileProperty.setPaths(mPathList);
		return mTFile;
	}

	@Override
	public TFile toName(String name) {
		mFileProperty.setName(name);
		return mTFile;
	}

	@Override
	public TFile toNames(String... names) {
		LinkedList<String> namesList = new LinkedList<>();
		for (String name : names) {
			namesList.add(name);
		}
		mFileProperty.setNames(namesList);
		return mTFile;
	}

	@Override
	public TFile toFile() {
		String path, name;
		if ((path = mFileProperty.getPath()) != null) {
			if ((name = mFileProperty.getName()) != null) {
				mFileProperty.setFile(new File(path + name));
				// since v1.0.2 解决前一次设置了文件名对之后创建文件夹时文件名依然存在，导致创建文件夹错误的问题。 issues:#1
				toName(null);
			} else {
				mFileProperty.setFile(new File(path));
			}
		} else {
			TException.pathException("文件路径未指定");
		}
		return mTFile;
	}

	@Override
	public TFile toFiles() {
		LinkedList<String> paths = mFileProperty.getPaths();
		LinkedList<String> names = mFileProperty.getNames();
		LinkedList<File> files = new LinkedList<>();
		File old = mFileProperty.getFile(); // 设置完成之后需要回复之前的文件
		if (paths != null && names != null) {
			// 目录和文件名一一对应
			if (names.size() > 0 && paths.size() >= names.size()) {
				for (int i = 0; i < names.size(); i++) {
					files.add(new File(paths.get(i) + names.get(i)));
				}
			} else if (paths.size() == 1 && names.size() > 0) {
				String path = paths.get(0);
				for (int i = 0; i < names.size(); i++) {
					files.add(new File(path + names.get(i)));
				}
			} else {
				TException.fileNameException("请指定文件路径和个数对应的文件名");
			}
		} else if (mFileProperty.getPath() != null && names != null) {
			String path = mFileProperty.getPath() + mSeparator;
			for (int i = 0; i < names.size(); i++) {
				files.add(new File(path + names.get(i)));
			}
		}
		// since v1.0.2 解决前一次设置了文件名对之后创建文件夹时文件名依然存在，导致创建文件夹错误的问题。 issues:#1
		toNames();
		mFileProperty.setFile(old);
		mFileProperty.setFiles(files);
		return mTFile;
	}
	
	@Override
	public TFile setFlag(String flag) {
		mFileProperty.setFlagFile(flag);
		return mTFile;
	}
	
	@Override
	public void removeFlag(String flag) {
		mFileProperty.removeFlagFile(flag);
	}

	/**
	 * 构建路径
	 */
	private String path(String p, String flag) {
		StringBuilder path = new StringBuilder();
		String[] ps = p.split(flag);
		for (int i = 0; i < ps.length; i++) {
			path.append(ps[i]);
			path.append(mSeparator);
		}
		return path.toString();
	}

}
