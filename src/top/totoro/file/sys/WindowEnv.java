package top.totoro.file.sys;

import java.io.File;

import top.totoro.file.util.TException;

public final class WindowEnv {
	public WindowEnv() {
		SystemProperties window = SystemProperties.getTestSys("window");
		if (window.isWindows()) {
			// 本地测试环境既是windows，无需更改系统特性
			return;
		}
		if (window.isLinuxButAndroid()) {
			// 本地测试环境是Linux但不是Android系统
			String tmp = "/C:/root/AppData/Local/Temp/";
			String root = "/C:/root";
			window.getProperties().put(window.OS_NAME, "windows");
			window.getProperties().put(window.IO_TMPDIR, tmp);
			window.getProperties().put(window.ROOT_DIR, root);
			File file = new File(tmp);
			file.mkdirs();
			return;
		}
		if (window.isAndroid()) {
			// 本地测试环境是Android系统
			String tmp = "/sdcard/C:/Users/root/AppData/Local/Temp/";
			String root = "/sdcard/C:/User/root";
			window.getProperties().put(window.OS_NAME, "windows");
			window.getProperties().put(window.IO_TMPDIR, tmp);
			window.getProperties().put(window.ROOT_DIR, root);
			window.getProperties().put(window.NO_ROOT, "/sdcard/");
			File file = new File(tmp);
			if (!file.mkdirs()) {
				TException.envException("android环境下，创建Windows测试环境时，系统默认临时文件路径创建失败，可能未设置存储许可");
			}
			file = new File(root);
			if (!file.mkdirs()) {
				TException.envException("android环境下，创建Windows测试环境时，系统根目录创建失败，可能未设置存储许可");
			}
		}
	}
}
