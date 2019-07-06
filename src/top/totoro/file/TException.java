package top.totoro.file;

import java.util.HashMap;
import java.util.Map;


/**
 * 定义所有相关的异常
 * 
 * @author 黄龙三水
 *
 */
public class TException extends Exception {

	private static final long serialVersionUID = 1L;

	private volatile static String mNewMsg;
	private static TException mTException;
	private static final Map<String, TException> mExptionMap = new HashMap<>();

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
				mTException = new TException(mNewMsg);
				mExptionMap.put(mNewMsg, mTException);
			}
		} else {
			mTException = new TException();
		}
		return mTException;
	}

	public static TException pathException(String msg) {
		return instance(msg);
	}
	
	public static TException fileNameException(String msg) {
		return instance(msg);
	}
	
	public static TException envException(String msg) {
		return instance(msg);
	}

	public static String getExceptionMsg() {
		return mNewMsg;
	}

}
