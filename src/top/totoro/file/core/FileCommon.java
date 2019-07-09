package top.totoro.file.core;

interface FileCommon {
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
