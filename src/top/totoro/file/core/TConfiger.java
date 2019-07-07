package top.totoro.file.core;

import java.io.File;
import java.io.IOException;

import top.totoro.file.sys.SystemProperties;
import top.totoro.file.util.TException;

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
			TException.pathException("������������ʧ�ܣ�������������ǰ��δָ���ļ�");
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
				TException.pathException("������������ʧ�ܣ�windows��������������ʱ���ļ�������;��ȷ���ڵ���setHide����ǰ�ļ��ѱ�����");
			}
		} else if (os.toLowerCase().contains("linux")) {
			String name = file.getName();
			String path = file.getPath().substring(0, file.getPath().lastIndexOf(separator) + 1);
			// Linuxϵͳ���ļ��ѱ�����
			if (file.exists() && file.isFile() && path != null && name != null && !name.startsWith(".")) {// �ļ�����
				mFileProperty.setName("." + name);
				mFileProperty.setFile(new File(path + separator + mFileProperty.getName()));
				file.renameTo(mFileProperty.getFile());
			} else if (file.exists() && file.isDirectory() && path != null) {// �ļ�������
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
			TException.pathException("�Ƴ���������ǰ��δָ���ļ�");
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
				TException.pathException("windows�������Ƴ���������ʱ���ļ������ڣ���ȷ���ڵ���setHide����ǰ�ļ��ѱ����������Ե���createFile��createFiles");
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
				TException.fileNameException("�Ƴ�" + name + "�ļ���������ʧ�ܣ��ļ�������Ϊ���Ҳ�����'.'��ͷ");
				return mTFile;
			}
			// Linuxϵͳ���ļ��ѱ�����
			if (file.exists() && file.isFile()) {// �ļ�ȡ������
				mFileProperty.setName(file.getName().substring(1));
				mFileProperty.setFile(new File(path + separator + mFileProperty.getName()));
				file.renameTo(mFileProperty.getFile());
			} else if (file.exists() && file.isDirectory()) { // �ļ���ȡ������
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
	public TFile setFlagFile(String flag) {
		mFileProperty.setFlagFile(flag);
		return mTFile;
	}

	@Override
	public File getFlagFile(String flag) {
		return mFileProperty.getFlagFile(flag);
	}

	@Override
	public void removeFlagFile(String flag) {
		mFileProperty.removeFlagFile(flag);
	}

	@Override
	public TFile recycle() {
		mFileProperty.recycleProperty();
		return mTFile;
	}

}
