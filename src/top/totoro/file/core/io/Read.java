package top.totoro.file.core.io;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;

/**
 * 获取文件内容方法
 * 
 * @author 黄龙三水
 *
 */
public interface Read {

	// ----------------------length-------------------------- //
	long lengthByFile();

	/**
	 * 使用方法{@see top.totoro.file.core.TFile#toFiles()}设置的所有存在的文件名为Map的key
	 * 
	 * @return 将文件内容长度作为Map值返回
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
	 * 如果一个文件存储了多行数据，且每行数据是一个布尔值 则可以使用该方法获取所有的布尔值 
	 * 布尔值将被存放在LinkedList<Boolean>中，索引值是数据所在文件中的行号
	 */
	LinkedList<Boolean> getBooleanByFile();

	Map<String, LinkedList<Boolean>> getBooleanByFiles();

	LinkedList<Boolean> getBooleanByFlag(String flag);

	Map<String, LinkedList<Boolean>> getBooleanByFlags(String[] flags);

	// ----------------------Number-------------------------- //
	/**
	 * 如果一个文件存储了多行数据，且每行数据是一个数 则可以使用该方法获取所有的数值 
	 * 数值将被存放在LinkedList<N extends Number>中，索引值是数据所在文件中的行号
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
