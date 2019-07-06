package top.totoro.file.util;

import top.totoro.file.sys.SystemProperties;

public class DiskChose {
	public static String disk(Disk d) {
		SystemProperties sys = SystemProperties.getSystem();
		if (d == null) {
			return sys.getRootDir();
		}
		String disk;
		switch (d) {
		case SD:
			if (!sys.isAndroid()) {
				disk = sys.getRootDir() + "sdcard";
			} else {
				disk = sys.getNoRoot();
			}
			break;
		case A:
			if (sys.isWindows()) {
				disk = sys.getNoRoot() + "A:";
			}else {
				disk = sys.getNoRoot()+"A";
			}
			break;
		case B:
			if (sys.isWindows()) {
				disk = sys.getNoRoot() + "B:";
			}else {
				disk = sys.getNoRoot()+"B";
			}
			break;
		case C:
			if (sys.isWindows()) {
				disk = sys.getNoRoot() + "C:";
			}else {
				disk = sys.getNoRoot()+"C";
			}
			break;
		case D:
			if (sys.isWindows()) {
				disk = sys.getNoRoot() + "D:";
			}else {
				disk = sys.getNoRoot()+"D";
			}
			break;
		case E:
			if (sys.isWindows()) {
				disk = sys.getNoRoot() + "E:";
			}else {
				disk = sys.getNoRoot()+"E";
			}
			break;
		case F:
			if (sys.isWindows()) {
				disk = sys.getNoRoot() + "F:";
			}else {
				disk = sys.getNoRoot()+"F";
			}
			break;
		case G:
			if (sys.isWindows()) {
				disk = sys.getNoRoot() + "G:";
			}else {
				disk = sys.getNoRoot()+"G";
			}
			break;
		// ......
		case TMP: // ¡Ÿ ±ª∫¥Ê
			disk = sys.getTmpDir();
			break;
		case ROOT:
			disk = sys.getRootDir();
			break;
		default:
			disk = "";
			break;
		}
		String separator = sys.getProperties().get(sys.FILE_SEPARATOR);
		if (!disk.endsWith(separator)) {
			disk += separator;
		}
		return disk;
	}
}
