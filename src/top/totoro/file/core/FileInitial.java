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

	// make一个全路径
	TFile mkdirs();

	// make多个全路径，如果没有设置toPaths(String paths)默认执行mkdirs()
	TFile mkdirsAll();

	// 创建对应的文件
	TFile create();

	// 如果设置了toNames(String... names)，则创建names.size()个文件
	TFile createAll();

	// 清空对应的文件
	TFile clear();

	// 如果设置了toNames(String... names)，则清空names.size()个文件
	TFile clearAll();

	// 删除对应文件
	TFile delete();

	// 如果设置了toNames(String... names)，则删除names.size()个文件
	TFile deleteAll();

	// 文件重命名
	TFile rename(String name);

	// Recycle所有资源及配置
	TFile recycle();
}
