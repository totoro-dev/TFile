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
			TException.pathException("������������ǰ��δָ���ļ�").printStackTrace();
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
				TException.pathException("windows������������������ʱ���ļ������ڣ�\n��ȷ���ڵ���setHide����ǰ�ļ��ѱ����������Ե���createFile��createFiles")
						.printStackTrace();
			}
		} else if (os.toLowerCase().contains("linux")) {
			if (file.exists() && file.isFile()) {
				// �ǰ�׿������Linuxϵͳ���ļ��ѱ�����
				mFileProperty.setName("/." + mFileProperty.getName());
				mFileProperty.setFile(new File(mFileProperty.getPath() + mFileProperty.getName()));
				file.renameTo(mFileProperty.getFile());
			} else {
				// �ǰ�׿������Linuxϵͳ���ļ�δ������
				if (mFileProperty.getName() != null) { // �ļ�����
					mFileProperty.setName("." + mFileProperty.getName());
					mFileProperty.setFile(new File(mFileProperty.getPath() + mFileProperty.getName()));
					file.renameTo(mFileProperty.getFile());
				} else if (mFileProperty.getPath() != null) { // �ļ�������
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
			TException.pathException("�Ƴ���������ǰ��δָ���ļ�").printStackTrace();
		}
		if (os.toLowerCase().contains("win")) {
			if (file.exists() && file.isFile()) {
				try {
					Runtime.getRuntime().exec("attrib -H " + "\"" + file.getAbsolutePath() + "\"");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				TException.pathException("windows�������Ƴ���������ʱ���ļ������ڣ�\n��ȷ���ڵ���setHide����ǰ�ļ��ѱ����������Ե���createFile��createFiles")
						.printStackTrace();
			}
		} else if (os.toLowerCase().contains("linux")) {
			if (file.exists() && file.isFile()) {
				// Linuxϵͳ���ļ��ѱ�����
				if (file.getName().startsWith(".")) {// �ļ�����
					mFileProperty.setName(file.getName().substring(1));
					mFileProperty.setFile(new File(mFileProperty.getPath() + mFileProperty.getName()));
					file.renameTo(mFileProperty.getFile());
				} else {
					TException.fileNameException("�Ƴ�" + mFileProperty.getName() + "�������ԣ��ļ�������");
				}
			} else if (file.exists() && file.isDirectory()) {
				if (mFileProperty.getPath() != null) { // �ļ�������
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
