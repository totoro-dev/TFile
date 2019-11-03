package top.totoro.file.test;

import java.io.File;

import top.totoro.file.core.TCommoner;
import top.totoro.file.core.TProperty;
import top.totoro.file.util.Disk;

public class TCommonerTest {

	public static void main(String[] args) {
		top.totoro.file.util.TException.setDebug(true);
		TProperty property = new TProperty();
		TCommoner commoner = new TCommoner(property);
		property.setDisk(Disk.C);
		property.setPath("C:/tfile/test/main/");
		property.setName("t1.t");
		property.setFile(new File(property.getPath() + property.getName()));
		if (!property.exists()) {
			commoner.create();
			System.out.println(property.toString() + "\n\n文件不存在,创建文件:" + property.getFile());
		} else {
			System.out.println("\n文件已存在,清空文件:" + property.getFile());
			commoner.clear();
			if (property.getFile().length() == 0) {
				System.out.println("\n文件已清空,重命名文件:" + property.getFile());
				commoner.rename("t2.t");
				if (property.getFile().getName().equals("t2.t")) {
					System.out.println("\n文件已重命名，删除文件：" + property.getFile());
					commoner.delete();
					if (!property.exists()) {
						System.out.println("\n文件已删除，创建初始文件：" + property.getFile());
						property.setName("t1.t");
						property.setFile(new File(property.getPath()+property.getName()));
						commoner.create();
						System.out.println("\n测试结束：" + property.toString());
					}
				}
			}
		}
	}

}
