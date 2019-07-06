package top.totoro.file.common;

import top.totoro.file.util.Disk;

/**
 * �ļ�������ʼ��
 * @author ������ˮ
 *
 * @param <FileProperty>
 */
public interface FileInitial {
	// ѡ��洢�̷�
	FileProperty toDisk(Disk disk);
	// ��pathת��Ϊ���л����µ�ȫ·��,ͳһʹ�÷ָ���������
	FileProperty toPath(String path);
	// ��pathsת��Ϊ���ȫ·��������ȫ·���÷ָ���������������ȫ·���÷ָ�����|��
	FileProperty toPaths(String paths);
	// makeһ��ȫ·��
	FileProperty createDir();
	// make���ȫ·�������û������toPaths(String paths)Ĭ��ִ��createDir()
	FileProperty createDirs();
	// ������Ӧpath���ļ�
	FileProperty createFile(String name);
	// ���������toPaths(String paths)���򴴽�names��Ӧpaths���ļ���������path�´���names��Ӧ�ļ�
	FileProperty createFiles(String... names);
	// ���������toPaths(String paths)�������names��Ӧpaths���ļ����������path��������Ϊname���ļ�
	FileProperty clearFile(String name);
	// ���������toPaths(String paths)�������names��Ӧpaths���ļ���������path�����names��Ӧ�ļ�
	FileProperty clearFiles(String... names);
	// �ļ�������
	FileProperty rename(String name);
}
