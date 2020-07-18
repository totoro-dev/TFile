package top.totoro.file.sys;

import java.util.HashMap;
import java.util.Map;

public final class SystemProperties {
	// 代码运行系统的一些必要信息;
	private final static Map<String, String> SYS_PROPERTIES = new HashMap<>();
	public final String FILE_SEPARATOR = "file.separator"; // 文件路径分隔符
	public final String PATH_SEPARATOR = "path.separator"; // 路径-路径分隔符
	public final String LINE_SEPARATOR = "line.separator"; // 行分隔符
	public final String IO_TMPDIR = "java.io.tmpdir"; // 系统默认临时文件路径
	public final String ROOT_DIR = "user.home"; // 系统根目录
	public final String OS_NAME = "os.name"; // 系统名称（Windows/Linux/Unix）
	public final String NO_ROOT = "no.root"; // 不指定根目录

	private SystemProperties() {
		if (SYS_PROPERTIES.isEmpty()) {
			SYS_PROPERTIES.put(FILE_SEPARATOR, System.getProperty(FILE_SEPARATOR));
			SYS_PROPERTIES.put(PATH_SEPARATOR, System.getProperty(PATH_SEPARATOR));
			SYS_PROPERTIES.put(LINE_SEPARATOR, System.getProperty(LINE_SEPARATOR));
			SYS_PROPERTIES.put(IO_TMPDIR, System.getProperty(IO_TMPDIR));
			SYS_PROPERTIES.put(ROOT_DIR, System.getProperty(ROOT_DIR));
			SYS_PROPERTIES.put(OS_NAME, System.getProperty(OS_NAME));
			if (isAndroid()) {
				SYS_PROPERTIES.put(NO_ROOT, "/sdcard/");
			}else {
				SYS_PROPERTIES.put(NO_ROOT, getFileSeparator());
			}
		}
	}

	public String getFileSeparator() {
		return SYS_PROPERTIES.get(FILE_SEPARATOR);
	}

	public String getPathSeparator() {
		return SYS_PROPERTIES.get(PATH_SEPARATOR);
	}

	public String getLineSeparator() {
		return SYS_PROPERTIES.get(LINE_SEPARATOR);
	}

	public String getOsName() {
		return SYS_PROPERTIES.get(OS_NAME);
	}
	
	public String getNoRoot() {
		return SYS_PROPERTIES.get(NO_ROOT);
	}
	
	public String getRootDir() {
		String root = SYS_PROPERTIES.get(ROOT_DIR);
		if (root.endsWith(getFileSeparator())) {
			return root;
		}
		root+=getFileSeparator();
		return root;
	}
	
	/**
	 * @return 获取当前运行系统的默认临时文件路径
	 */
	public String getTmpDir() {
		String tmp = SYS_PROPERTIES.get(IO_TMPDIR);
		if (tmp.endsWith(getFileSeparator())) {
			return tmp;
		}
		tmp += getFileSeparator();
		return tmp;
	}

	public boolean isAndroid() {
		String tmp = SYS_PROPERTIES.get(IO_TMPDIR);
		String os = SYS_PROPERTIES.get(OS_NAME).toLowerCase();
		if (!tmp.contains("tmp") && os.contains("linux")) {
			return true;
		}
		return false;
	}
	
	public boolean isLinuxButAndroid() {
		String tmp = SYS_PROPERTIES.get(IO_TMPDIR);
		String os = SYS_PROPERTIES.get(OS_NAME).toLowerCase();
		if (tmp.contains("tmp") && os.contains("linux")) {
			return true;
		}
		return false;
	}
	
	public boolean isWindows() {
		String tmp = SYS_PROPERTIES.get(IO_TMPDIR);
		String os = SYS_PROPERTIES.get(OS_NAME).toLowerCase();
		if (tmp.contains("AppData") && os.contains("win")) {
			return true;
		}
		return false;
	}

	/**
	 * @return 获取当前运行系统的路径相关信息集合
	 */
	public synchronized Map<String, String> getProperties() {
		return SYS_PROPERTIES;
	}

	static SystemProperties mSystemProperties;

	public static SystemProperties getSystem() {
		synchronized (SystemProperties.class) {
			if (mSystemProperties == null) {
				mSystemProperties = new SystemProperties();
			}
		}
		return mSystemProperties;
	}
	
	private static SystemProperties system;
	public static SystemProperties getTestSys(String sys) {
		if (sys.equals("window")) {
			system = new SystemProperties();
		}else if (sys.equals("linux")) {
			system = new SystemProperties();
		}else if (sys.equals("android")) {
			system = new SystemProperties();
		}
		return system;
	}
	
}
