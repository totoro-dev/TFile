package top.totoro.file.sys;

import java.io.File;

import top.totoro.file.TException;

public class LinuxEnv {
	public LinuxEnv() throws TException {
		SystemProperties linux = SystemProperties.getTestSys("linux");
		if (linux.isLinuxButAndroid()) {
			// ���ز��Ի�������Linuxϵͳ,������Androidϵͳ���������ϵͳ����
			return;
		}
		if (linux.isWindows()) {
			// ���ز��Ի�����Windowsϵͳ
			String tmp = linux.getTmpDir() + "tmp\\";
			String root = linux.getRootDir() + "root\\";
			linux.getProperties().put(linux.OS_NAME, "linux");
			linux.getProperties().put(linux.IO_TMPDIR, tmp);
			linux.getProperties().put(linux.ROOT_DIR, root);
			linux.getProperties().put(linux.NO_ROOT, linux.getRootDir());
			File file = new File(tmp);
			file.mkdirs();
			return;
		}
		if (linux.isAndroid()) {
			// ���ز��Ի�����Androidϵͳ
			String tmp = "/sdcard/data/user/0/cache/";
			String root = "/sdcard/root/";
			linux.getProperties().put(linux.IO_TMPDIR, tmp);
			linux.getProperties().put(linux.ROOT_DIR, root);
			File file = new File(tmp);
			if (!file.mkdirs()) {
				throw TException.envException("android�����£�����Windows���Ի���ʱ��ϵͳĬ����ʱ�ļ�·������ʧ�ܣ�����δ���ô洢���");
			}
			file = new File(root);
			if (!file.mkdirs()) {
				throw TException.envException("android�����£�����Windows���Ի���ʱ��ϵͳ��Ŀ¼����ʧ�ܣ�����δ���ô洢���");
			}
		}
	}
}
