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
					TException.pathException("�ļ�Ŀ¼����ʧ��");
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
						TException.pathException("�ļ�Ŀ¼����ʧ��:���ܴ���" + DiskChose.disk(mFileProperty.getDisk()) + "������");
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
				TException.fileNameException(name + "�ļ�����ʧ�ܣ��ļ��Ѵ���");
				return mTFile;
			}
			if (!name.startsWith(".")) {
				if ((new File(file.getPath() + mSeparator + "." + name)).exists()) {
					TException.fileNameException(name + "�ļ�����ʧ�ܣ��ļ��ѱ�����");
					return mTFile;
				}
			} else {
				TException.fileNameException(name + "�ļ�����ʧ�ܣ��ļ���������'.'��ͷ");
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
						System.out.println(file + "Ŀ¼�����ɹ�");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			TException.fileNameException("�ļ�����ʧ�ܣ�δָ���ļ�");
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
				TException.fileNameException("����ļ�ʧ�ܣ��ļ�" + file.getName() + "������");
		} else {
			TException.fileNameException("����ļ�ʧ�ܣ�δָ���ļ�");
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
				TException.pathException("�������ļ�ʧ�ܣ�ԭ�ļ�������");
			}
		} else {
			TException.pathException("�������ļ�ʧ�ܣ�δָ���ļ�");
		}
		return mTFile;
	}

	@Override
	public TFile recycle() {
		mFileProperty.recycleProperty();
		return mTFile;
	}

}
