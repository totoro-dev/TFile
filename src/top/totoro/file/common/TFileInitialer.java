package top.totoro.file.common;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import top.totoro.file.TException;
import top.totoro.file.sys.SystemProperties;
import top.totoro.file.util.Disk;
import top.totoro.file.util.DiskChose;

public class TFileInitialer implements FileInitial {
	private FileProperty mFileProperty;
	private String mSparator = SystemProperties.getSystem().getFileSeparator();

	public TFileInitialer(FileProperty tFile) {
		mFileProperty = tFile;
	}

	@Override
	public FileProperty toDisk(Disk disk) {
		mFileProperty.setDisk(disk);
		return mFileProperty;
	}

	@Override
	public FileProperty toPath(String path) {
		mFileProperty.setPath(DiskChose.disk(mFileProperty.getDisk()) + this.path(path, ","));
		mFileProperty.setFile(new File(mFileProperty.getPath()));
		return mFileProperty;
	}

	@Override
	public FileProperty toPaths(String paths) {
		String[] ps = paths.split(";");
		List<String> mPathList = new LinkedList<>();
		for (int i = 0; i < ps.length; i++) {
			mPathList.add(DiskChose.disk(mFileProperty.getDisk()) + path(ps[i], ","));
		}
		mFileProperty.setPaths(mPathList);
		return mFileProperty;
	}

	@Override
	public FileProperty createDir() {
		if (mFileProperty.getPath() != null) {
			File dir = new File(mFileProperty.getPath());
			if (!dir.isDirectory()) {
				if (!dir.mkdirs()) {
					TException.pathException("�ļ�Ŀ¼����ʧ��");
				}
			}
			mFileProperty.setFile(dir);
		}
		return mFileProperty;
	}

	@Override
	public FileProperty createDirs() {
		List<String> paths = mFileProperty.getPaths();
		List<File> files = new LinkedList<>();
		if (paths != null) {
			for (String path : paths) {
				File dir = new File(path);
				if (!dir.isDirectory()) {
					if (!dir.mkdirs()) {
						TException.pathException("�ļ�Ŀ¼����ʧ��:���ܴ���" + DiskChose.disk(mFileProperty.getDisk()) + "������")
								.printStackTrace();
					}
				}
				files.add(dir);
			}
		}
		mFileProperty.setFiles(files);
		return mFileProperty;
	}

	@Override
	public FileProperty createFile(String name) {
		mFileProperty.setName(name);
		mFileProperty.setFile(new File(mFileProperty.getPath() + name));
		try {
			if (mFileProperty.getFile().exists()) {
				TException.fileNameException(mFileProperty.getFile() + " exist").printStackTrace();
				return mFileProperty;
			} else if ((new File(mFileProperty.getPath() + "." + name)).exists()) {
				mFileProperty.setFile(new File(mFileProperty.getPath() + "." + name));
				TException.fileNameException(mFileProperty.getFile() + " exist").printStackTrace();
				return mFileProperty;
			}
			if (!mFileProperty.getFile().createNewFile()) {
				if (mFileProperty.getFile().exists()) {
					TException.fileNameException("�ļ�" + name + "����ʧ��:�ļ��Ѵ��ڣ���������Ϊ����").printStackTrace();
				} else if (!(new File(mFileProperty.getPath())).exists()) {
					TException.fileNameException("�ļ�" + name + "����ʧ��:�ļ����Ѵ��ڣ��ļ�Ŀ¼δ����").printStackTrace();
				} else {
					TException.fileNameException("�ļ�" + name + "����ʧ��:Ŀ¼�����ڣ�ԭ��δ֪").printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mFileProperty;
	}

	@Override
	public FileProperty createFiles(String... names) {
		List<String> paths = mFileProperty.getPaths();
		if (paths == null && mFileProperty.getPath() != null) {
			for (String name : names) {
				createFile(name);
			}
		} else if (paths.size() >= names.length) {
			List<File> files = new LinkedList<>();
			int i = 0;
			for (String name : names) {
				mFileProperty.setPath(paths.get(i));
				createFile(name);
				files.add(mFileProperty.getFile());
				i++;
			}
			mFileProperty.setFiles(files);
		} else {
			TException.pathException("����" + names.length + "���ļ�ʧ��:δָ�����Ӧ�ļ�Ŀ¼,�����ļ��ļ���������Ŀ¼����").printStackTrace();
			;
		}
		return mFileProperty;
	}

	@Override
	public FileProperty clearFile(String name) {
		// TODO Auto-generated method stub
		return mFileProperty;
	}

	@Override
	public FileProperty clearFiles(String... names) {
		// TODO Auto-generated method stub
		return mFileProperty;
	}

	@Override
	public FileProperty rename(String name) {
		// TODO Auto-generated method stub
		return mFileProperty;
	}

	/**
	 * ����·��
	 */
	private String path(String p, String flag) {
		StringBuilder path = new StringBuilder();
		String[] ps = p.split(flag);
		for (int i = 0; i < ps.length; i++) {
			path.append(ps[i]);
			path.append(mSparator);
		}
		return path.toString();
	}

}
