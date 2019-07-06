package top.totoro.file.common;

import java.io.File;
import java.io.IOException;

import top.totoro.file.TException;
import top.totoro.file.sys.SystemProperties;

public class TFileConfiger implements FileConfig {

	private FileProperty mFileProperty;

	public TFileConfiger(FileProperty fileProperty) {
		mFileProperty = fileProperty;
	}

	@Override
	public FileProperty setHide() {
		String os = SystemProperties.getSystem().getOsName();
		File file = mFileProperty.getFile();
		if (file == null) {
			TException.pathException("设置隐藏属性前，未指定文件").printStackTrace();
			return mFileProperty;
		}
		if (os.toLowerCase().contains("win")) {
			if (file.exists() && file.isFile()) {
				try {
					Runtime.getRuntime().exec("attrib +H " + "\"" + file.getAbsolutePath() + "\"");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				TException.pathException("windows环境下设置隐藏属性时，文件不存在：\n请确保在调用setHide方法前文件已被创建，可以调用createFile或createFiles")
						.printStackTrace();
			}
		} else if (os.toLowerCase().contains("linux")) {
			if (file.exists() && file.isFile()) {
				// 非安卓环境的Linux系统，文件已被创建
				mFileProperty.setName("/." + mFileProperty.getName());
				mFileProperty.setFile(new File(mFileProperty.getPath() + mFileProperty.getName()));
				file.renameTo(mFileProperty.getFile());
			} else {
				// 非安卓环境的Linux系统，文件未被创建
				if (mFileProperty.getName() != null) { // 文件隐藏
					mFileProperty.setName("." + mFileProperty.getName());
					mFileProperty.setFile(new File(mFileProperty.getPath() + mFileProperty.getName()));
					file.renameTo(mFileProperty.getFile());
				} else if (mFileProperty.getPath() != null) { // 文件夹隐藏
					String separator = SystemProperties.getSystem().getFileSeparator();
					String path = mFileProperty.getPath().substring(0, mFileProperty.getPath().length() - 1);
					String end = path.substring(path.lastIndexOf(separator) + 1);
					path = path.substring(0, path.lastIndexOf(separator) + 1);
					StringBuilder builder = new StringBuilder(path);
					builder.append(".");
					builder.append(end);
					builder.append(separator);
					mFileProperty.setPath(builder.toString());
					mFileProperty.setFile(new File(builder.toString()));
					file.renameTo(mFileProperty.getFile());
				}
			}
		}
		return mFileProperty;
	}

	@Override
	public FileProperty removeHide() {
		String os = SystemProperties.getSystem().getOsName();
		File file = mFileProperty.getFile();
		if (file == null) {
			TException.pathException("移除隐藏属性前，未指定文件").printStackTrace();
		}
		if (os.toLowerCase().contains("win")) {
			if (file.exists() && file.isFile()) {
				try {
					Runtime.getRuntime().exec("attrib -H " + "\"" + file.getAbsolutePath() + "\"");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				TException.pathException("windows环境下移除隐藏属性时，文件不存在：\n请确保在调用setHide方法前文件已被创建，可以调用createFile或createFiles")
						.printStackTrace();
			}
		} else if (os.toLowerCase().contains("linux")) {
			if (file.exists() && file.isFile()) {
				// Linux系统，文件已被创建
				if (file.getName().startsWith(".")) {// 文件隐藏
					mFileProperty.setName(file.getName().substring(1));
					mFileProperty.setFile(new File(mFileProperty.getPath() + mFileProperty.getName()));
					file.renameTo(mFileProperty.getFile());
				} else {
					TException.fileNameException("移除" + mFileProperty.getName() + "隐藏属性：文件已隐藏");
				}
			} else if (file.exists() && file.isDirectory()) {
				if (mFileProperty.getPath() != null) { // 文件夹隐藏
					String separator = SystemProperties.getSystem().getFileSeparator();
					String path = mFileProperty.getPath().substring(0, mFileProperty.getPath().length() - 1);
					String end = path.substring(path.lastIndexOf(separator) + 1);
					if (end.startsWith(".")) {
						path = path.substring(0, path.lastIndexOf(separator) + 1);
						StringBuilder builder = new StringBuilder(path);
						builder.append(end.substring(1));
						builder.append(separator);
						mFileProperty.setPath(builder.toString());
						mFileProperty.setFile(new File(builder.toString()));
						file.renameTo(mFileProperty.getFile());
					}
				}
			}
		}
		return null;
	}

	@Override
	public FileProperty setOnlyRead() {
		mFileProperty.getFile().setReadOnly();
		return null;
	}

	@Override
	public FileProperty removeOnlyRead() {
		mFileProperty.getFile().setWritable(true);
		return null;
	}

	@Override
	public FileProperty setFlag(String flag) {
		// TODO Auto-generated method stub
		return null;
	}

}
