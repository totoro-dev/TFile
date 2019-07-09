package top.totoro.file.core;

/**
 * �ļ���һЩ��������
 * 
 * @author ������ˮ
 *
 * @param <TFile>
 *            ���յ��ļ�����
 */
interface FileConfig {
	// �����ļ���������
	TFile setHide();

	// �Ƴ���������
	TFile removeHide();

	// ����ֻ������
	TFile setOnlyRead();

	// �Ƴ�ֻ������
	TFile removeOnlyRead();

	// ������Դ
	TFile recycle();
}