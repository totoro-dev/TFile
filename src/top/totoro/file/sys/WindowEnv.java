package top.totoro.file.sys;

import java.io.File;

import top.totoro.file.util.TException;

public final class WindowEnv {
	public WindowEnv() {
		SystemProperties window = SystemProperties.getTestSys("window");
		if (window.isWindows()) {
			// ���ز��Ի�������windows���������ϵͳ����
			return;
		}
		if (window.isLinuxButAndroid()) {
			// ���ز��Ի�����Linux������Androidϵͳ
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
			// ���ز��Ի�����Androidϵͳ
			String tmp = "/sdcard/C:/Users/root/AppData/Local/Temp/";
			String root = "/sdcard/C:/User/root";
			window.getProperties().put(window.OS_NAME, "windows");
			window.getProperties().put(window.IO_TMPDIR, tmp);
			window.getProperties().put(window.ROOT_DIR, root);
			window.getProperties().put(window.NO_ROOT, "/sdcard/");
			File file = new File(tmp);
			if (!file.mkdirs()) {
				TException.envException("android�����£�����Windows���Ի���ʱ��ϵͳĬ����ʱ�ļ�·������ʧ�ܣ�����δ���ô洢���");
			}
			file = new File(root);
			if (!file.mkdirs()) {
				TException.envException("android�����£�����Windows���Ի���ʱ��ϵͳ��Ŀ¼����ʧ�ܣ�����δ���ô洢���");
			}
		}
	}
}
