package top.totoro.file.core.io;

public interface Write {
	
	boolean write(String string);
	boolean write2JSONObject(String object);
	boolean write2JSONArray(String[] items);

}
