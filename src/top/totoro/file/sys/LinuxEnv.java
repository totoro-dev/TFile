package top.totoro.file.sys;

import java.io.File;

import top.totoro.file.TException;

public class LinuxEnv {
	public LinuxEnv() throws TException {
		SystemProperties linux = SystemProperties.getTestSys("linux");
		if (linux.isLinuxButAndroid()) {
			// 本地测试环境既是Linux系统,但不是Android系统，无需更改系统特性
			return;
		}
		if (linux.isWindows()) {
			// 本地测试环境是Windows系统
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
			// 本地测试环境是Android系统
			String tmp = "/sdcard/data/user/0/cache/";
			String root = "/sdcard/root/";
			linux.getProperties().put(linux.IO_TMPDIR, tmp);
			linux.getProperties().put(linux.ROOT_DIR, root);
			File file = new File(tmp);
			if (!file.mkdirs()) {
				throw TException.envException("android环境下，创建Windows测试环境时，系统默认临时文件路径创建失败，可能未设置存储许可");
			}
			file = new File(root);
			if (!file.mkdirs()) {
				throw TException.envException("android环境下，创建Windows测试环境时，系统根目录创建失败，可能未设置存储许可");
			}
		}
	}
}
