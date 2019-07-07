package top.totoro.file.core;

import java.io.File;

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

	// ���������ļ�����һ����ǩ���ñ�ǩ�ᱻ�洢��ϵͳ�У��Ժ�ֻ��Ҫʹ�������ǩ�Ϳ����ҵ���Ӧ�ļ�
	TFile setFlagFile(String flag);
	
	// @see{FileProperty#getFlagFile(String flag)}
	File getFlagFile(String flag);
	
	// @see{FileProperty#removeFlagFile(String flag)}
	void removeFlagFile(String flag);

	// ������Դ
	TFile recycle();
}