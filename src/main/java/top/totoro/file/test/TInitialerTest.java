package top.totoro.file.test;

import top.totoro.file.core.TInitialer;
import top.totoro.file.core.TProperty;
import top.totoro.file.util.Disk;

public class TInitialerTest {

	public static void main(String[] args) {
		top.totoro.file.util.TException.setDebug(true);
		new top.totoro.file.sys.LinuxEnv();
		TInitialer tfi = new TInitialer(new TProperty());
		tfi.toDisk(Disk.C);
		tfi.toPath("initial,test,tfi");
		tfi.toName("totoro3.txt");
		tfi.toFile();
		tfi.toNames("totoro1.txt", "totoro2.txt");
		tfi.toFiles();
		System.out.println("recycle之前：" + tfi.getFileProperty().toString());
		tfi.recycle();
		System.out.println("recycle之后：" + tfi.getFileProperty().toString());
	}

}
