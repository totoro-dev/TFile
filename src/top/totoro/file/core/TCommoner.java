package top.totoro.file.core;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import top.totoro.file.sys.SystemProperties;
import top.totoro.file.util.DiskChose;
import top.totoro.file.util.TException;

public class TCommoner implements FileCommon{
	
	private FileProperty mFileProperty;
	private TFile mTFile;
	private String mSeparator = SystemProperties.getSystem().getFileSeparator();
	
	public TCommoner(FileProperty property) {
		this(property, null);
	}

	public TCommoner(FileProperty fileProperty, TFile t) {
		mFileProperty = fileProperty;
		mTFile = t;
	}
	
	@Override
	public TFile mkdirs() {
		if (mFileProperty.getPath() != null) {
			File dir = new File(mFileProperty.getPath());
			if (!dir.isDirectory()) {
				if (!dir.mkdirs()) {
					TException.pathException("文件目录创建失败");
				}
			}
			// mFileProperty.setFile(dir);
		}
		return mTFile;
	}

	@Override
	public TFile mkdirsAll() {
		LinkedList<String> paths = mFileProperty.getPaths();
		LinkedList<File> files = new LinkedList<>();
		if (paths != null) {
			for (String path : paths) {
				File dir = new File(path);
				if (!dir.isDirectory()) {
					if (!dir.mkdirs()) {
						TException.pathException("文件目录创建失败:可能磁盘" + DiskChose.disk(mFileProperty.getDisk()) + "不存在");
					}
				}
				files.add(dir);
			}
		} else {
			mkdirs();
		}
		// mFileProperty.setFiles(files);
		return mTFile;
	}
	
	@Override
	public TFile create() {
		File file = mFileProperty.getFile();
		if (file != null) {
			String name = file.getName();
			if (file.exists()) {
				TException.fileNameException(name + "文件创建失败：文件已存在");
				return mTFile;
			}
			if (!name.startsWith(".")) {
				if ((new File(file.getPath() + mSeparator + "." + name)).exists()) {
					TException.fileNameException(name + "文件创建失败：文件已被隐藏");
					return mTFile;
				}
			} else {
				TException.fileNameException(name + "文件创建失败：文件名不能以'.'开头");
				return mTFile;
			}
			String path = file.getPath().substring(0, file.getPath().lastIndexOf(mSeparator) + 1);
			File p = new File(path);
			if (!p.exists()) {
				mFileProperty.setPath(path + mSeparator);
				mkdirs();
				mFileProperty.setFile(file);
			}
			try {
				if (name != null) {
					if (!file.createNewFile() && file.exists()) {
						System.out.println(file + "目录创建成功");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			TException.fileNameException("文件创建失败：未指定文件");
		return mTFile;
	}

	@Override
	public TFile createAll() {
		LinkedList<File> files = mFileProperty.getFiles();
		if (files != null) {
			File old = mFileProperty.getFile();
			for (File file : files) {
				mFileProperty.setFile(file);
				create();
			}
			mFileProperty.setFile(old);
		}
		return mTFile;
	}

	@Override
	public TFile clear() {
		File file = mFileProperty.getFile();
		if (file != null) {
			if (file.exists()) {
				try {
					file.delete();
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else
				TException.fileNameException("清空文件失败：文件" + file.getName() + "不存在");
		} else {
			TException.fileNameException("清空文件失败：未指定文件");
		}
		return mTFile;
	}

	@Override
	public TFile clearAll() {
		LinkedList<File> files = mFileProperty.getFiles();
		File old = mFileProperty.getFile();
		for (File file : files) {
			mFileProperty.setFile(file);
			clear();
		}
		mFileProperty.setFile(old);
		return mTFile;
	}

	@Override
	public TFile delete() {
		File file = mFileProperty.getFile();
		if (file != null) {
			if (file.exists()) {
				file.delete();
			}
		}
		return mTFile;
	}

	@Override
	public TFile deleteAll() {
		LinkedList<File> files = mFileProperty.getFiles();
		File old = mFileProperty.getFile();
		for (File file : files) {
			mFileProperty.setFile(file);
			delete();
		}
		mFileProperty.setFile(old);
		return mTFile;
	}

	@Override
	public TFile rename(String name) {
		File file = mFileProperty.getFile();
		if (file != null) {
			if (file.exists()) {
				String path = file.getAbsolutePath();
				path = path.substring(0, path.lastIndexOf(mSeparator) + 1);
				mFileProperty.setName(name);
				mFileProperty.setFile(new File(path + name));
				file.renameTo(mFileProperty.getFile());
			} else {
				TException.pathException("重命名文件失败：原文件不存在");
			}
		} else {
			TException.pathException("重命名文件失败：未指定文件");
		}
		return mTFile;
	}

	@Override
	public TFile recycle() {
		mFileProperty.recycleProperty();
		return mTFile;
	}

}
