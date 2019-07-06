package top.totoro.file.test;

import top.totoro.file.TException;
import top.totoro.file.common.TFileInitialer;
import top.totoro.file.common.TFileProperty;
import top.totoro.file.sys.LinuxEnv;
import top.totoro.file.util.Disk;

public class TFileInitialerTest {

	public static void main(String[] args) {
		try {
			new LinuxEnv();
		} catch (TException e) {
			e.printStackTrace();
		}
		TFileInitialer tfi = new TFileInitialer(new TFileProperty());
		tfi.toDisk(Disk.C);
		System.out.println(tfi.toPath("initial,test,tfi").getPath());
		tfi.createDir();
		tfi.createFile("totoro0.txt");
		tfi.toDisk(Disk.D);
		System.out.println(tfi.toPaths("initial,test,t1;initial,test,t2").getPaths());
		tfi.createDirs();
		tfi.createFiles("totoro1.txt","totoro2.txt");
	}

}
