package top.totoro.file.core;

import top.totoro.file.util.Disk;

/**
 * 文件操作初始化
 * 
 * @author 黄龙三水
 *
 * @param <TFile>
 */
interface FileInitial {
	// 选择存储盘符
	TFile toDisk(Disk disk);

	// 将path转化为运行环境下的全路径,统一使用分隔符“，”
	TFile toPath(String path);

	// 将paths转化为多个全路径，单个全路径用分隔符“，”；各个全路径用分隔符“|”
	TFile toPaths(String paths);

	// 设置单个文件名
	TFile toName(String name);

	// 设置多个文件名
	TFile toNames(String... names);

	// 生成一个文件对象
	TFile toFile();

	// 生成多个文件对象
	TFile toFiles();

	// 给创建的文件设置一个标签，该标签会被存储到系统中，以后只需要使用这个标签就可以找到对应文件
	TFile setFlag(String flag);

	// @see{FileProperty#removeFlagFile(String flag)}
	void removeFlag(String flag);

	// Recycle所有资源及配置
	TFile recycle();
}
