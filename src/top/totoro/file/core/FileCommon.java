package top.totoro.file.core;

interface FileCommon {
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
