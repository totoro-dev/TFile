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

	// ���������ļ�����һ����ǩ���ñ�ǩ�ᱻ�洢��ϵͳ�У��Ժ�ֻ��Ҫʹ�������ǩ�Ϳ����ҵ���Ӧ�ļ�
	TFile setFlag(String flag);

	// @see{FileProperty#removeFlagFile(String flag)}
	void removeFlag(String flag);

	// Recycle������Դ������
	TFile recycle();
}
