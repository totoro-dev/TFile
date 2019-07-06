package top.totoro.file.common;

import top.totoro.file.TException;

/**
 * 文件的一些基础属性
 * @author 黄龙三水
 *
 * @param <FileProperty> 最终的文件操作
 */
public interface FileConfig {
	// 设置文件隐藏属性
	FileProperty setHide() throws TException;
	// 移除隐藏属性
	FileProperty removeHide() throws TException;
	// 设置只读属性
	FileProperty setOnlyRead();
	// 移除只读属性
	FileProperty removeOnlyRead();
	// 给创建的文件设置一个标签，该标签会被存储到系统中，以后只需要使用这个标签就可以找到对应文件
	FileProperty setFlag(String flag);
}