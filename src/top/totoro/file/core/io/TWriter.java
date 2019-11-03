package top.totoro.file.core.io;

import java.io.FileWriter;
import java.io.IOException;

import top.totoro.file.core.FileProperty;

public class TWriter implements Write{
	
	private final FileProperty property;
	
	private FileWriter writer;
	
	public TWriter(FileProperty property) {
		this.property = property;
	}

	@Override
	public boolean write(String string) {
		try {
			writer = new FileWriter(property.getFile().getAbsolutePath());
//			BufferedWriter bw = new BufferedWriter(writer);
			writer.write(string);
//			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean write2JSONObject(String object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean write2JSONArray(String[] items) {
		// TODO Auto-generated method stub
		return false;
	}

}
