package top.totoro.file.common;

import top.totoro.file.util.Disk;

/**
 * 文件操作初始化
 * @author 黄龙三水
 *
 * @param <FileProperty>
 */
public interface FileInitial {
	// 选择存储盘符
	FileProperty toDisk(Disk disk);
	// 将path转化为运行环境下的全路径,统一使用分隔符“，”
	FileProperty toPath(String path);
	// 将paths转化为多个全路径，单个全路径用分隔符“，”；各个全路径用分隔符“|”
	FileProperty toPaths(String paths);
	// make一个全路径
	FileProperty createDir();
	// make多个全路径，如果没有设置toPaths(String paths)默认执行createDir()
	FileProperty createDirs();
	// 创建对应path的文件
	FileProperty createFile(String name);
	// 如果设置了toPaths(String paths)，则创建names对应paths的文件；否则在path下创建names对应文件
	FileProperty createFiles(String... names);
	// 如果设置了toPaths(String paths)，则清空names对应paths的文件；否则清空path下所有名为name的文件
	FileProperty clearFile(String name);
	// 如果设置了toPaths(String paths)，则清空names对应paths的文件；否则在path下清空names对应文件
	FileProperty clearFiles(String... names);
	// 文件重命名
	FileProperty rename(String name);
}
