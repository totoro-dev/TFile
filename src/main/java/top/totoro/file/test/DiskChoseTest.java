package top.totoro.file.test;

import java.util.Scanner;

import top.totoro.file.sys.AndroidEnv;
import top.totoro.file.sys.LinuxEnv;
import top.totoro.file.sys.WindowEnv;
import top.totoro.file.util.Disk;
import top.totoro.file.util.DiskChose;

public class DiskChoseTest {
	public static void main(String[] args) {
		top.totoro.file.util.TException.setDebug(true);
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String system = (String) scanner.next();
			if (system.equals("w")) {
				new WindowEnv();
			} else if (system.equals("l")) {
				new LinuxEnv();
			} else if (system.equals("a")) {
				new AndroidEnv();
			}
			Disk disk = null;
			switch (scanner.nextInt()) {
			case 0:
				disk = null;
				break;
			case 1:
				disk = Disk.E;
				break;
			case 2:
				disk = Disk.SD;
				break;
			case 3:
				disk = Disk.TMP;
				break;
			case 4:
				disk = Disk.ROOT;
				break;
			case 5:
				disk = Disk.LINUX;
				break;
			default:
				break;
			}
			System.out.println(DiskChose.disk(disk));
		}
		scanner.close();
	}
}
