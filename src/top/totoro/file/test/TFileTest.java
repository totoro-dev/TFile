package top.totoro.file.test;

import java.util.Scanner;

import top.totoro.file.core.TFile;
import top.totoro.file.util.Disk;

public class TFileTest {
	public static void main(String[] args) {
//		top.totoro.file.util.TException.setDebug(true);
//		new top.totoro.file.sys.AndroidEnv();
		TFile.builder()
		.toDisk(Disk.C)
		.toPath("tfile,test,main")
		.toFile()
		.mkdirs()
//		.toName("totoro.htm")
//		.toFile()
//		.create()
		.setFlagFile("totoro");
		TFile.builder()
		.toNames("t1.t","t2.t","t3.t")
		.toFiles()
		.createAll();
		String path = "tfile,test,main";
		TFile.builder()
		.toPaths(path+",a;"+path+",b;"+path+",c")
		.toNames("t4.t","t5.t","t6.t")
		.toFiles()
		.createAll();
//		TFile.builder().setOnlyRead();
//		TFile.builder().removeOnlyRead();
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String com = scanner.nextLine();
			if (com.equals("hide")) {
				TFile.builder().setHide();
			}else if (com.equals("remove hide")) {
				TFile.builder().removeHide();
			}else if (com.equals("delete")) {
				TFile.getProperty().getFlagFile("totoro").delete();
			}else if(com.equals("exit")){
				System.exit(1);
			}
		}
		scanner.close();
	}
}
