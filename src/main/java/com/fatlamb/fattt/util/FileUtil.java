package com.fatlamb.fattt.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

public class FileUtil {

	/**
	 * 判断文件夹是否存在,如果不存在则创建文件夹
	 * 
	 * @param path
	 *            文件夹路径
	 */
	public static void createDirsIfNotExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 判断是否存在文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExistFile(String path) {
		if (new File(path).exists())
			return true;
		else
			return false;
	}

	/**
	 * 将HttpEntity中的数据读取到filename文件中
	 * 
	 * @param entity
	 * @param filename
	 * @throws Exception
	 */
	public static void readFileFromEntity(HttpEntity entity, String filename) throws Exception {
		byte[] body = EntityUtils.toByteArray(entity);
		File file = new File(filename);
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		try {
			writer.write(body);
		} catch (Exception e) {

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static String read(String path) throws IOException {
		return read(path, "utf-8");
	}

	public static void wirte(String filename, byte[] filebuffer) {
		try {
			File file = new File(filename);
			BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
			try {
				writer.write(filebuffer);
			} catch (Exception e) {

			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String read(String path, String charSetName) throws IOException {
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			File file = new File(path);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSetName));
			String line = null;
			while ((line = reader.readLine()) != null)
				stringBuilder.append(line + "\r\n");
		} catch (IOException e) {
			throw e;

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return stringBuilder.toString();
	}

	public static void write(String path, String content) throws IOException {
		write(path, content, "utf-8", false);
	}

	public static void write(String path, String content, String charSetName, boolean append) throws IOException {
		BufferedWriter writer = null;
		try {
			File file = new File(path);
			File parent = file.getParentFile();
			if (parent != null && parent.exists() == false) {
				if (parent.mkdirs() == false) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), charSetName));
			writer.write(content + "\n");
			writer.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
