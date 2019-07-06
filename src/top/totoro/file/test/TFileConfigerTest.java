package top.totoro.file.test;

import top.totoro.file.TException;
import top.totoro.file.common.TFileConfiger;
import top.totoro.file.common.TFileInitialer;
import top.totoro.file.common.TFileProperty;
import top.totoro.file.sys.LinuxEnv;
import top.totoro.file.util.Disk;

public class TFileConfigerTest {
	public static void main(String[] args) {
//		try {
//			new LinuxEnv();
//		} catch (TException e) {
//			e.printStackTrace();
//		}
		TFileProperty property = new TFileProperty();
		TFileInitialer initialer = new TFileInitialer(property);
		TFileConfiger configer = new TFileConfiger(property);
		initialer.toDisk(Disk.C);
		initialer.toPath("config,test,t1");
		initialer.createDir();
		initialer.createFile("totoro1.txt");
		configer.setHide();
		configer.setOnlyRead();
		configer.removeOnlyRead();
		configer.removeHide();
	}
}
