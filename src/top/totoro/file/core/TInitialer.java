package top.totoro.file.core;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import top.totoro.file.sys.SystemProperties;
import top.totoro.file.util.Disk;
import top.totoro.file.util.DiskChose;
import top.totoro.file.util.TException;

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

	public FileProperty getmFileProperty() {
		return mFileProperty;
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
			} else {
				mFileProperty.setFile(new File(path));
			}
		} else {
			TException.pathException("�ļ�·��δָ��");
		}
		return mTFile;
	}

	@Override
	public TFile toFiles() {
		LinkedList<String> paths = mFileProperty.getPaths();
		LinkedList<String> names = mFileProperty.getNames();
		LinkedList<File> files = new LinkedList<>();
		File old = mFileProperty.getFile();
		if (paths != null && paths != null) {
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
				TException.fileNameException("��ָ���ļ�·���͸�����Ӧ���ļ���");
			}
		} else if (mFileProperty.getPath() != null && names != null) {
			String path = mFileProperty.getPath() + mSeparator;
			for (int i = 0; i < names.size(); i++) {
				files.add(new File(path + names.get(i)));
			}
		}
		mFileProperty.setFile(old);
		mFileProperty.setFiles(files);
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
		if (mFileProperty.getFile() != null) {
			if (mFileProperty.getFile().exists()) {
				String path = mFileProperty.getFile().getAbsolutePath();
				path = path.substring(0, path.lastIndexOf(mSeparator) + 1);
				mFileProperty.getFile().renameTo(new File(path + name));
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

	/**
	 * ����·��
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
