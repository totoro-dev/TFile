package top.totoro.file.core;

/**
 * 文件的一些基础属性
 * 
 * @author 黄龙三水
 *
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

	// 回收资源
	TFile recycle();
}