package top.totoro.file.test;

import top.totoro.file.core.TInitialer;
import top.totoro.file.core.TProperty;
import top.totoro.file.util.Disk;

public class TFileInitialerTest {

	public static void main(String[] args) {
		top.totoro.file.util.TException.setDebug(true);
		new top.totoro.file.sys.LinuxEnv();
		TInitialer tfi = new TInitialer(new TProperty());
		tfi.toDisk(Disk.C);
		System.out.println(tfi.toPath("initial,test,tfi"));
		tfi.toName("totoro3.txt");
		tfi.toFile();
		tfi.create();
//		tfi.rename("totoro1.txt");
//		System.out.println(tfi.toPaths("initial,test,t1").getPaths());
//		tfi.toNames("totoro1.txt", "totoro2.txt");
//		tfi.toFiles();
//		tfi.createAll();
//		tfi.clear();
//		tfi.deleteAll();
//		System.out.println(tfi.getmFileProperty().getFile());
//		tfi.recycle();
//		System.out.println(tfi.getmFileProperty().getFile());
	}

}
