package top.totoro.file.util;

import java.util.HashMap;
import java.util.Map;


/**
 * ����������ص��쳣
 * 
 * @author ������ˮ
 *
 */
public class TException extends Exception {

	private static final long serialVersionUID = 1L;

	private volatile static String mNewMsg;
	private static TException mTException;
	private static final Map<String, TException> mExptionMap = new HashMap<>();
	private static boolean debug = false;

	private TException() {
		super();
		mNewMsg = null;
	}

	private TException(String msg) {
		super(msg);
		mNewMsg = msg;
	}

	private static TException instance(String exit) {
		mNewMsg = exit;
		if (mNewMsg != null) {
			if ((mTException = mExptionMap.get(mNewMsg)) != null) {
			} else {
				if (mExptionMap.size() > 9) { // ����10����ͬ�쳣ʱ���
					mExptionMap.clear();
				}
				mTException = new TException(mNewMsg);
				mExptionMap.put(mNewMsg, mTException);
			}
		} else {
			mTException = new TException();
		}
		return mTException;
	}
	
	public static void setDebug(boolean debug) {
		TException.debug = debug;
	}
	
	public static void pathException(String msg) {
		print(msg);
	}
	
	public static void fileNameException(String msg) {
		print(msg);
	}
	
	public static void envException(String msg) {
		print(msg);
	}

	public static String getExceptionMsg() {
		return mNewMsg;
	}
	
	private static void print(String msg){
		if (debug) {
			instance(msg).printStackTrace();
		}
	}

}
