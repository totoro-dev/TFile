package top.totoro.file.sys;

import java.io.File;

public final class AndroidEnv {
	public AndroidEnv(){
		SystemProperties android = SystemProperties.getTestSys("android");
		if (android.isAndroid()) {
			// 本地测试环境既是Android系统，无需更改系统特性
			return;
		}
		String tmp = "",root = "",noRoot = "";
		if (android.isWindows()) {
			// 本地测试环境是windows系统
			tmp = android.getTmpDir() + "data\\user\\0\\cache\\";
			root = android.getRootDir() + "sdcard\\";
			noRoot = root;
		}else if (android.isLinuxButAndroid()) {
			// 本地测试环境是Linux系统,但不是Android系统
			tmp = android.getTmpDir() + "data/user/0/cache";
			root = android.getRootDir() + "sdcard/";
			noRoot = "/sdcard/";
		}
		android.getProperties().put(android.OS_NAME, "linux");
		android.getProperties().put(android.IO_TMPDIR, tmp);
		android.getProperties().put(android.ROOT_DIR, root);
		android.getProperties().put(android.NO_ROOT, noRoot);
		File file = new File(tmp);
		file.mkdirs();
		file = new File(root);
		file.mkdirs();
	}
}
