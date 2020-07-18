package top.totoro.file.test;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import top.totoro.file.core.TProperty;
import top.totoro.file.core.io.TReader;

public class TReaderTest {
	public static void main(String[] args) {
		String pre = "C:/tfile/io/";
		TProperty property = new TProperty();
		TReader reader = new TReader(property);
		System.out.println("------------by file------------");
		property.setFile(new File(pre + "string.t"));
		property.setFlagFile("string");
		System.out.println("string: " + Arrays.asList(reader.getSplitStringByFile("\n")));
		property.setFile(new File(pre + "byte.t"));
		property.setFlagFile("byte");
		LinkedList<byte[]> bytes = reader.getByteByFile(10);
		System.out.print("byte: ");
		Object[] all = bytes.toArray();
		for (Object b : all) {
			for (int i = 0; i < ((byte[]) b).length; i++) {
				System.out.print(((byte[]) b)[i]);
			}
		}
		System.out.println();
		property.setFile(new File(pre + "boolean.t"));
		property.setFlagFile("boolean");
		System.out.println("boolean: " + reader.getBooleanByFile());
		property.setFile(new File(pre + "number.t"));
		property.setFlagFile("number");
		System.out.println("number: " + reader.getNumberByFile(Double.class));
		System.out.println("------------by files------------");
		File[] files = { new File(pre + "string.t"), new File(pre + "byte.t"), new File(pre + "boolean.t"),
				new File(pre + "number.t") };
		LinkedList<File> fs = new LinkedList<>();
		fs.add(files[0]);
		fs.add(files[1]);
		fs.add(files[2]);
		fs.add(files[3]);
		property.setFiles(fs);
		Map<String, String[]> sm = reader.getSplitStringByFiles("\n", null);
		Set<String> keys = sm.keySet();
		System.out.println("files:");
		for (String key : keys) {
			System.out.println(key + ": " + Arrays.asList(sm.get(key)));
		}
		System.out.println("------------by flag------------");
		System.out.println("flag string:" + Arrays.asList(reader.getSplitStringByFlag("string", "\n")));
		System.out.println("flag byte:" + reader.getByteByFlag("byte", 10));
		System.out.println("flag boolean:" + reader.getBooleanByFlag("boolean"));
		System.out.println("flag number:" + reader.getNumberByFlag(Double.class, "number"));
		System.out.println("------------by flags------------");
		String[] flags = { "string", "byte" };
		System.out.println("flags string,byte:\n" + reader.getStringByFlags(flags));
	}
}
