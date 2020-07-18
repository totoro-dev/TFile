package top.totoro.file.util;

import top.totoro.file.sys.SystemProperties;

public final class DiskChose {
	
	private static SystemProperties sys = SystemProperties.getSystem();
	private static String separator = sys.getProperties().get(sys.FILE_SEPARATOR);

	public static String disk(Disk d) {
		if (d == null) {
			return sys.getRootDir();
		}
		String disk = "";
		switch (d) {
		case SD:
			disk = inAndroid();
			break;
		case LINUX:
			disk = inLinux();
			break;
		case A:
		case B:
		case C:
		case D:
		case E:
		case F:
		case G:
			// ......
			disk = inWinDisk(d);
			break;
		case TMP: // 临时缓存路径
			disk = sys.getTmpDir();
			break;
		case ROOT: // 根目录
			disk = sys.getRootDir();
			break;
		}
		if (!disk.endsWith(separator)) {
			disk += separator;
		}
		return disk;
	}

	private static String inAndroid() {
		String disk;
		if (!sys.isAndroid()) {
			disk = sys.getRootDir() + "sdcard";
		} else {
			disk = sys.getNoRoot();
		}
		return disk;
	}

	private static String inLinux() {
		String d;
		String os =System.getProperty("os.name").toLowerCase(); // 运行时真实环境
		if (os.contains("win")) { // 真实环境：windows
			String root = sys.getRootDir().substring(0, sys.getRootDir().lastIndexOf(separator));
			if (!sys.isWindows()) {
				d = root.substring(0, root.lastIndexOf(separator));
			} else {
				d = root;
			}
		} else if (os.contains("linux")) { 
			d = sys.getNoRoot();
		} else {
			d = separator;
		}
		return d;
	}
	
	private static String inWinDisk(Disk disk) {
		String d;
		if (sys.isWindows()) {
			// 如果真实环境时Windows则是真正的系统盘符，否则是在真实环境的起始目录下创建disk盘符
			d = sys.getNoRoot() + disk.toString() + ":";
		} else {
			// 非Windows测试环境，盘符建立在真实环境的根目录下
			d = sys.getRootDir() + disk.toString();
		}
		return d;
	}
}
