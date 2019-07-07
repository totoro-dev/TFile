package top.totoro.file.core;

import java.io.File;

/**
 * 文件的一些基础属性
 * 
 * @author 黄龙三水
 *
 * @param <TFile>
 *            最终的文件操作
 */
interface FileConfig {
	// 设置文件隐藏属性
	TFile setHide();

	// 移除隐藏属性
	TFile removeHide();

	// 设置只读属性
	TFile setOnlyRead();

	// 移除只读属性
	TFile removeOnlyRead();

	// 给创建的文件设置一个标签，该标签会被存储到系统中，以后只需要使用这个标签就可以找到对应文件
	TFile setFlagFile(String flag);
	
	// @see{FileProperty#getFlagFile(String flag)}
	File getFlagFile(String flag);
	
	// @see{FileProperty#removeFlagFile(String flag)}
	void removeFlagFile(String flag);

	// 回收资源
	TFile recycle();
}