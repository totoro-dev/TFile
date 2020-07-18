package top.totoro.file.util;

public enum Disk {
	SD, // 安卓环境的内存卡
	LINUX, // Linux环境的起始目录“/”
	A, // A~G：Windows环境盘符
	B,
	C,
	D,
	E,
	F,
	G,
	TMP, // 当前环境的临时文件路径
	ROOT // 当前环境的根目录：user.home
}
