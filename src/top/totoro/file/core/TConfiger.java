package top.totoro.file.core;

import java.io.File;
import java.io.IOException;

import top.totoro.file.sys.SystemProperties;
import top.totoro.file.util.TException;

/**
 * 给一个文件设置一些属性
 * 比如：隐藏属性（hide able），只读属性（readable），文件标志（flag）
 * 1. 同时可以移除这些属性
 * 2. 通过文件标志直接拿到指定文件而不需要使用时再次构建该文件相关信息
 * 
 * 注：不同环境下的隐藏属性设置方式不同（Windows/Linux（包括Android））
 * 
 * @author 黄龙三水
 *
 */
public final class TConfiger implements FileConfig {

	private FileProperty mFileProperty;
	private TFile mTFile;
	private String separator = SystemProperties.getSystem().getFileSeparator();

	public TConfiger(FileProperty property) {
		this(property, null);
	}

	public TConfiger(FileProperty fileProperty, TFile t) {
		mFileProperty = fileProperty;
		mTFile = t;
	}

	@Override
	public TFile setHide() {
		String os = SystemProperties.getSystem().getOsName();
		File file = mFileProperty.getFile(), old = file;
		if (file == null) {
			TException.pathException("设置隐藏属性失败：设置隐藏属性前，未指定文件");
			return mTFile;
		}
		if (os.toLowerCase().contains("win")) {
			if (file.exists()) {
				try {
					Runtime.getRuntime().exec(new StringBuilder("attrib +H ").append("\"")
							.append(file.getAbsolutePath()).append("\"").toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				TException.pathException("设置隐藏属性失败：windows下设置隐藏属性时，文件不存在;请确保在调用setHide方法前文件已被创建");
			}
		} else if (os.toLowerCase().contains("linux")) {
			String name = file.getName();
			String path = file.getPath().substring(0, file.getPath().lastIndexOf(separator) + 1);
			// Linux系统，文件已被创建
			if (file.exists() && file.isFile() && path != null && name != null && !name.startsWith(".")) {// 文件隐藏
				mFileProperty.setName("." + name);
				mFileProperty.setFile(new File(path + separator + mFileProperty.getName()));
				file.renameTo(mFileProperty.getFile());
			} else if (file.exists() && file.isDirectory() && path != null) {// 文件夹隐藏
				StringBuilder builder = new StringBuilder(path).append(".").append(name).append(separator);
				mFileProperty.setPath(builder.toString());
				mFileProperty.setFile(new File(builder.toString()));
				file.renameTo(mFileProperty.getFile());
			}
			mFileProperty.setFile(old);
		}
		return mTFile;
	}

	@Override
	public TFile removeHide() {
		String os = SystemProperties.getSystem().getOsName();
		File file = mFileProperty.getFile();
		if (file == null) {
			TException.pathException("移除隐藏属性前，未指定文件");
			return mTFile;
		}
		if (os.toLowerCase().contains("win")) {
			if (file.exists()) {
				try {
					Runtime.getRuntime().exec(new StringBuilder("attrib -H ").append("\"")
							.append(file.getAbsolutePath()).append("\"").toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				TException.pathException("windows环境下移除隐藏属性时，文件不存在：请确保在调用setHide方法前文件已被创建，可以调用createFile或createFiles");
			}
		} else if (os.toLowerCase().contains("linux")) {
			String name = file.getName();
			String path = file.getPath().substring(0, file.getPath().lastIndexOf(separator) + 1);
			if (name != null && !name.startsWith(".") && path != null) {
				file = new File(path + "." + name);
				if (!file.exists()) {
					return mTFile;
				}
			} else {
				TException.fileNameException("移除" + name + "文件隐藏属性失败：文件名不能为空且不能以'.'开头");
				return mTFile;
			}
			// Linux系统，文件已被创建
			if (file.exists() && file.isFile()) {// 文件取消隐藏
				mFileProperty.setName(file.getName().substring(1));
				mFileProperty.setFile(new File(path + separator + mFileProperty.getName()));
				file.renameTo(mFileProperty.getFile());
			} else if (file.exists() && file.isDirectory()) { // 文件夹取消隐藏
				StringBuilder builder = new StringBuilder(path).append(separator).append(name).append(separator);
				mFileProperty.setPath(builder.toString());
				mFileProperty.setFile(new File(builder.toString()));
				file.renameTo(mFileProperty.getFile());
			}
		}
		return mTFile;
	}

	@Override
	public TFile setOnlyRead() {
		File file = mFileProperty.getFile();
		if (file != null) {
			file.setReadOnly();
		}
		return mTFile;
	}

	@Override
	public TFile removeOnlyRead() {
		File file = mFileProperty.getFile();
		if (file != null) {
			file.setWritable(true);
		}
		return mTFile;
	}

	@Override
	public TFile recycle() {
		mFileProperty.recycleProperty();
		return mTFile;
	}

}
