package top.totoro.file.core;

import top.totoro.file.util.Disk;

/**
 * �ļ�������ʼ��
 * 
 * @author ������ˮ
 *
 * @param <TFile>
 */
interface FileInitial {
	// ѡ��洢�̷�
	TFile toDisk(Disk disk);

	// ��pathת��Ϊ���л����µ�ȫ·��,ͳһʹ�÷ָ���������
	TFile toPath(String path);

	// ��pathsת��Ϊ���ȫ·��������ȫ·���÷ָ���������������ȫ·���÷ָ�����|��
	TFile toPaths(String paths);

	// ���õ����ļ���
	TFile toName(String name);

	// ���ö���ļ���
	TFile toNames(String... names);

	// ����һ���ļ�����
	TFile toFile();

	// ���ɶ���ļ�����
	TFile toFiles();

	// makeһ��ȫ·��
	TFile mkdirs();

	// make���ȫ·�������û������toPaths(String paths)Ĭ��ִ��mkdirs()
	TFile mkdirsAll();

	// ������Ӧ���ļ�
	TFile create();

	// ���������toNames(String... names)���򴴽�names.size()���ļ�
	TFile createAll();

	// ��ն�Ӧ���ļ�
	TFile clear();

	// ���������toNames(String... names)�������names.size()���ļ�
	TFile clearAll();

	// ɾ����Ӧ�ļ�
	TFile delete();

	// ���������toNames(String... names)����ɾ��names.size()���ļ�
	TFile deleteAll();

	// �ļ�������
	TFile rename(String name);

	// Recycle������Դ������
	TFile recycle();
}
