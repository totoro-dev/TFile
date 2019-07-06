package top.totoro.file.common;

import top.totoro.file.TException;

/**
 * �ļ���һЩ��������
 * @author ������ˮ
 *
 * @param <FileProperty> ���յ��ļ�����
 */
public interface FileConfig {
	// �����ļ���������
	FileProperty setHide() throws TException;
	// �Ƴ���������
	FileProperty removeHide() throws TException;
	// ����ֻ������
	FileProperty setOnlyRead();
	// �Ƴ�ֻ������
	FileProperty removeOnlyRead();
	// ���������ļ�����һ����ǩ���ñ�ǩ�ᱻ�洢��ϵͳ�У��Ժ�ֻ��Ҫʹ�������ǩ�Ϳ����ҵ���Ӧ�ļ�
	FileProperty setFlag(String flag);
}