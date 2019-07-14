package top.totoro.file.core.io;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;

/**
 * ��ȡ�ļ����ݷ���
 * 
 * @author ������ˮ
 *
 */
public interface Read {

	// ----------------------length-------------------------- //
	long lengthByFile();

	/**
	 * ʹ�÷���{@see top.totoro.file.core.TFile#toFiles()}���õ����д��ڵ��ļ���ΪMap��key
	 * 
	 * @return ���ļ����ݳ�����ΪMapֵ����
	 */
	Map<String, Long> lengthByFiles();

	long lengthByFlag(String flag);

	Map<String, Long> lengthByFlags(String[] flags);

	// ----------------------String-------------------------- //
	String getStringByFile();

	Map<String, String> getStringByFiles();

	String getStringByFlag(String flag);

	Map<String, String> getStringByFlags(String[] flags);

	// ----------------------SplitString-------------------------- //
	String[] getSplitStringByFile(String fix, int... limit);

	Map<String, String[]> getSplitStringByFiles(String fix, int... limit);

	String[] getSplitStringByFlag(String flag, String fix, int... limit);

	Map<String, String[]> getSplitStringByFlags(String[] flags, String fix, int... limit);

	// ----------------------Byte-------------------------- //
	LinkedList<byte[]> getByteByFile(int length);

	Map<String, LinkedList<byte[]>> getByteByFiles(int length);

	LinkedList<byte[]> getByteByFlag(String flag, int length);

	Map<String, LinkedList<byte[]>> getByteByFlags(String[] flags, int length);

	// ----------------------Boolean-------------------------- //
	/**
	 * ���һ���ļ��洢�˶������ݣ���ÿ��������һ������ֵ �����ʹ�ø÷�����ȡ���еĲ���ֵ 
	 * ����ֵ���������LinkedList<Boolean>�У�����ֵ�����������ļ��е��к�
	 */
	LinkedList<Boolean> getBooleanByFile();

	Map<String, LinkedList<Boolean>> getBooleanByFiles();

	LinkedList<Boolean> getBooleanByFlag(String flag);

	Map<String, LinkedList<Boolean>> getBooleanByFlags(String[] flags);

	// ----------------------Number-------------------------- //
	/**
	 * ���һ���ļ��洢�˶������ݣ���ÿ��������һ���� �����ʹ�ø÷�����ȡ���е���ֵ 
	 * ��ֵ���������LinkedList<N extends Number>�У�����ֵ�����������ļ��е��к�
	 */
	<N extends Number> LinkedList<N> getNumberByFile(Class<N> n);

	<N extends Number> Map<String, LinkedList<N>> getNumberByFiles(Class<N> n);

	<N extends Number> LinkedList<N> getNumberByFlag(Class<N> n, String flag);

	<N extends Number> Map<String, LinkedList<N>> getNumberByFlags(Class<N> n, String[] flags);

	// ----------------------InputStream-------------------------- //
	InputStream getInputStreamByFile();

	Map<String, InputStream> getInputStringByFiles();

	InputStream getInputStreamByFlag(String flag);

	Map<String, InputStream> getInputStreamByFlags(String[] flags);
}
