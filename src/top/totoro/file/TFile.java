package top.totoro.file;

public class TFile {

	private static Builder builder;
	
	public static Builder builder() {
		return builder;
	}
	interface FileDoMain {
		void rename(String newname);
		void setPath(String path);
		void setPaths(String paths);
		
	}
	class Builder{
		public void build() {
			// TODO ����TFile��ʹ�ö������һ���ļ�����File)
		}
	}
}
