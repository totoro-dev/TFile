package top.totoro.file.core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import top.totoro.file.core.FileProperty;

public class TReader implements Read {

	private final FileProperty mProperty;

	public TReader(FileProperty property) {
		mProperty = property;
	}

	@Override
	public long lengthByFile() {
		return mProperty.getFile() == null ? 0 : mProperty.getFile().length();
	}

	@Override
	public Map<String, Long> lengthByFiles() {
		Map<String, Long> lm = new HashMap<>();
		for (File file : mProperty.getFiles()) {
			if (file != null) {
				lm.put(file.getName(), file.length());
			}
		}
		return lm;
	}

	@Override
	public long lengthByFlag(String flag) {
		return mProperty.getFlagFile(flag) == null ? 0 : mProperty.getFile().length();
	}

	@Override
	public Map<String, Long> lengthByFlags(String[] flags) {
		Map<String, Long> lm = new HashMap<>();
		for (String flag : flags) {
			File file = mProperty.getFlagFile(flag);
			if (file != null) {
				lm.put(file.getName(), file.length());
			}
		}
		return lm;
	}

	private synchronized String string(File file) {
		StringBuilder content = new StringBuilder();
		BufferedReader reader = null;
		try {
			if (file != null) {
				reader = new BufferedReader(new FileReader(file));
				if (reader != null) {
					String line = "";
					while ((line = reader.readLine()) != null) {
						content.append(line).append("\n");
					}
					reader.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	@Override
	public String getStringByFile() {
		return string(mProperty.getFile());
	}

	@Override
	public Map<String, String> getStringByFiles() {
		Map<String, String> sm = new HashMap<>();
		for (File file : mProperty.getFiles()) {
			sm.put(file.getName(), string(file));
		}
		return sm;
	}

	@Override
	public String getStringByFlag(String flag) {
		return string(mProperty.getFlagFile(flag));
	}

	@Override
	public Map<String, String> getStringByFlags(String[] flags) {
		Map<String, String> sm = new HashMap<>();
		for (String flag : flags) {
			sm.put(flag, string(mProperty.getFlagFile(flag)));
		}
		return sm;
	}

	private synchronized String[] splitString(File file, String fix, int... limit) {
		String[] split = null;
		if (file != null) {
			if (limit != null && limit.length == 1) {
				split = string(file).split(fix, limit[0]);
			} else {
				split = string(file).split(fix);
			}
		}
		return split;
	}

	@Override
	public String[] getSplitStringByFile(String fix, int... limit) {
		return splitString(mProperty.getFile(), fix, limit);
	}

	@Override
	public Map<String, String[]> getSplitStringByFiles(String fix, int... limit) {
		Map<String, String[]> ssm = new HashMap<>();
		for (File file : mProperty.getFiles()) {
			ssm.put(file.getName(), splitString(file, fix, limit));
		}
		return ssm;
	}

	@Override
	public String[] getSplitStringByFlag(String flag, String fix, int... limit) {
		return splitString(mProperty.getFlagFile(flag), fix, limit);
	}

	@Override
	public Map<String, String[]> getSplitStringByFlags(String[] flags, String fix, int... limit) {
		Map<String, String[]> ssm = new HashMap<>();
		for (String flag : flags) {
			ssm.put(flag, splitString(mProperty.getFlagFile(flag), fix, limit));
		}
		return ssm;
	}

	private synchronized LinkedList<byte[]> bytes(File file, int length) {
		byte[] bs = new byte[length];
		LinkedList<byte[]> bl = new LinkedList<>();
		try {
			InputStream input = new FileInputStream(file);
			while (input.read(bs) != -1) {
				bl.add(bs);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bl;
	}

	@Override
	public LinkedList<byte[]> getByteByFile(int length) {
		return bytes(mProperty.getFile(), length);
	}

	@Override
	public Map<String, LinkedList<byte[]>> getByteByFiles(int length) {
		Map<String, LinkedList<byte[]>> bm = new HashMap<>();
		for (File file : mProperty.getFiles()) {
			bm.put(file.getName(), bytes(file, length));
		}
		return bm;
	}

	@Override
	public LinkedList<byte[]> getByteByFlag(String flag, int length) {
		return bytes(mProperty.getFlagFile(flag), length);
	}

	@Override
	public Map<String, LinkedList<byte[]>> getByteByFlags(String[] flags, int length) {
		Map<String, LinkedList<byte[]>> bm = new HashMap<>();
		for (String flag : flags) {
			bm.put(flag, bytes(mProperty.getFlagFile(flag), length));
		}
		return bm;
	}

	private synchronized LinkedList<Boolean> booleans(File file) {
		LinkedList<Boolean> bl = new LinkedList<>();
		if (file != null) {
			String[] split = splitString(file, "\n", null);
			for (String s : split) {
				bl.add(Boolean.valueOf(s));
			}
		}
		return bl;
	}

	@Override
	public LinkedList<Boolean> getBooleanByFile() {
		return booleans(mProperty.getFile());
	}

	@Override
	public Map<String, LinkedList<Boolean>> getBooleanByFiles() {
		Map<String, LinkedList<Boolean>> bm = new HashMap<>();
		for (File file : mProperty.getFiles()) {
			bm.put(file.getName(), booleans(file));
		}
		return bm;
	}

	@Override
	public LinkedList<Boolean> getBooleanByFlag(String flag) {
		return booleans(mProperty.getFlagFile(flag));
	}

	@Override
	public Map<String, LinkedList<Boolean>> getBooleanByFlags(String[] flags) {
		Map<String, LinkedList<Boolean>> bm = new HashMap<>();
		for (String flag : flags) {
			bm.put(flag, booleans(mProperty.getFlagFile(flag)));
		}
		return bm;
	}

	@SuppressWarnings("unchecked")
	private <N extends Number> N number(Class<N> n, String line) {
		N number = null;
		if (n != null && n.isAssignableFrom(Short.class)) {
			number = (N) Short.valueOf(line);
		} else if (n.isAssignableFrom(Integer.class)) {
			number = (N) Integer.valueOf(line);
		} else if (n.isAssignableFrom(Long.class)) {
			number = (N) Long.valueOf(line);
		} else if (n.isAssignableFrom(Double.class)) {
			number = (N) Double.valueOf(line);
		} else if (n.isAssignableFrom(Float.class)) {
			number = (N) Float.valueOf(line);
		} else if (n.isAssignableFrom(Byte.class)) {
			number = (N) Byte.valueOf(line);
		}
		return number;
	}

	private synchronized <N extends Number> LinkedList<N> numbers(Class<N> n, File file) {
		LinkedList<N> numbers = new LinkedList<>();
		for (String line : splitString(file, "\n", null)) {
			numbers.add(number(n, line));
		}
		return numbers;
	}

	@Override
	public <N extends Number> LinkedList<N> getNumberByFile(Class<N> n) {
		return numbers(n, mProperty.getFile());
	}

	@Override
	public <N extends Number> Map<String, LinkedList<N>> getNumberByFiles(Class<N> n) {
		Map<String, LinkedList<N>> nm = new HashMap<>();
		for (File file : mProperty.getFiles()) {
			nm.put(file.getName(), numbers(n, file));
		}
		return nm;
	}

	@Override
	public <N extends Number> LinkedList<N> getNumberByFlag(Class<N> n, String flag) {
		return numbers(n, mProperty.getFlagFile(flag));
	}

	@Override
	public <N extends Number> Map<String, LinkedList<N>> getNumberByFlags(Class<N> n, String[] flags) {
		Map<String, LinkedList<N>> nm = new HashMap<>();
		for (String flag : flags) {
			nm.put(flag, numbers(n, mProperty.getFlagFile(flag)));
		}
		return null;
	}

	@Override
	public InputStream getInputStreamByFile() {
		InputStream input = null;
		try {
			input = new FileInputStream(mProperty.getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return input;
	}

	@Override
	public Map<String, InputStream> getInputStringByFiles() {
		Map<String, InputStream> inputs = new HashMap<>();
		for (File file : mProperty.getFiles()) {
			try {
				InputStream input = new FileInputStream(file);
				inputs.put(file.getName(), input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return inputs;
	}

	@Override
	public InputStream getInputStreamByFlag(String flag) {
		InputStream input = null;
		try {
			input = new FileInputStream(mProperty.getFlagFile(flag));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return input;
	}

	@Override
	public Map<String, InputStream> getInputStreamByFlags(String[] flags) {
		Map<String, InputStream> inputs = new HashMap<>();
		for (String flag : flags) {
			try {
				InputStream input = new FileInputStream(mProperty.getFlagFile(flag));
				inputs.put(flag, input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return inputs;
	}
}
