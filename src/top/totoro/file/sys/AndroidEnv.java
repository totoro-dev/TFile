package top.totoro.file.sys;

import java.io.File;

public final class AndroidEnv {
	public AndroidEnv(){
		SystemProperties android = SystemProperties.getTestSys("android");
		if (android.isAndroid()) {
			// ���ز��Ի�������Androidϵͳ���������ϵͳ����
			return;
		}
		String tmp = "",root = "",noRoot = "";
		if (android.isWindows()) {
			// ���ز��Ի�����windowsϵͳ
			tmp = android.getTmpDir() + "data\\user\\0\\cache\\";
			root = android.getRootDir() + "sdcard\\";
			noRoot = root;
		}else if (android.isLinuxButAndroid()) {
			// ���ز��Ի�����Linuxϵͳ,������Androidϵͳ
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
