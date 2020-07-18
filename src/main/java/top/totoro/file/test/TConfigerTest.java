package top.totoro.file.test;

import java.io.File;

import top.totoro.file.core.TConfiger;
import top.totoro.file.core.TProperty;
import top.totoro.file.util.Disk;

public class TConfigerTest {
	public static void main(String[] args) {
		top.totoro.file.util.TException.setDebug(true);
		TProperty property = new TProperty();
		TConfiger configer = new TConfiger(property);
		property.setDisk(Disk.C);
		property.setPath("C:/tfile/test/main/");
		property.setName("t1.t");
		property.setFile(new File(property.getPath() + property.getName()));
		// configer.setHide();
		// configer.removeHide();
		System.out.println("property: " + property.toString());
		configer.setOnlyRead();
		System.out.println("\nsetOnlyRead()\n\tcanWrite: " + property.getFile().canWrite());
		configer.removeOnlyRead();
		System.out.println("\nremoveOnlyRead()\n\tcanWrite: " + property.getFile().canWrite());
	}
}
