package top.totoro.file.test;

import java.io.File;

import top.totoro.file.core.TConfiger;
import top.totoro.file.core.TProperty;
import top.totoro.file.util.Disk;

public class TFileConfigerTest {
	public static void main(String[] args) {
		top.totoro.file.util.TException.setDebug(true);
		new top.totoro.file.sys.LinuxEnv();
		TProperty property = new TProperty();
		TConfiger configer = new TConfiger(property);
		property.setDisk(Disk.C);
		property.setPath("C:\\Users\\黄龙三水\\root\\C\\config\\test\\t1\\");
		property.setName("totoro1.txt");
		property.setFile(new File(property.getPath()+property.getName()));
//		configer.setHide();
		configer.setOnlyRead();
		configer.removeOnlyRead();
		configer.removeHide();
//		configer.setFlagFile("t1");
		System.out.println(property.getFlagFile("t1"));
	}
}
