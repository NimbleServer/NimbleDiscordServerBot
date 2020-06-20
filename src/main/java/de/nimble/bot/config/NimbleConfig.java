package de.nimble.bot.config;

import java.io.*;
import java.util.HashMap;

public class NimbleConfig {

	private String path;
	private File file;
	private BufferedReader reader;

	public NimbleConfig(String path) {
		this.path = path;
		this.file = new File(path);
	}

	public HashMap<String, String> read() {
		try {
			this.reader = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HashMap<String, String> keyValue = new HashMap<>();
		String line = "";
		try {
			while((line = reader.readLine()) != null) {
				String[] split = line.split(":");
				keyValue.put(split[0], split[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return keyValue;
	}

	public void setFile() {
		this.file = new File(path);
	}

	public File geFile() {
		return this.file;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

}
