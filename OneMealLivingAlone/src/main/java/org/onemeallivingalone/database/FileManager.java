package org.onemeallivingalone.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 파일 읽기/쓰기를 처리하는 클래스.
 */
public class FileManager {
	
	public static String RECORDS_DIR = "data";

	private transient final Logger logger = LoggerFactory.getLogger("omla-data-driver");

	/**
	 * 파일에서 모든 내용을 읽어옵니다.
	 * 
	 * @param fileName
	 *            파일 이름 또는 경로
	 * @return 성공할 경우 문자열을 반환하고, 실패할 경우 null을 반환합니다.
	 * @throws IOException
	 */
	public String readFromFile(String fileName) throws IOException {
		logger.debug("Starting reading the file: {}", fileName);
		
		StringBuilder sb = new StringBuilder();

		// 파일 읽기 전용으로 열기
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
			// 파일 내용 읽기
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}

		logger.debug("Done reading the file: {}; length: {}", fileName, sb.length());
		
		return sb.toString();
	}

	/**
	 * 파일을 주어진 문자열로 덮어씁니다.
	 * 
	 * @param fileName
	 *            파일 이름 또는 경로
	 * @param content
	 *            기록할 문자열
	 * @return 읽기 성공 여부
	 * @throws IOException
	 */
	public void writeToFile(String fileName, String content) throws IOException {
		logger.debug("Starting writing to the file: {}", fileName);
		
		// 파일 열기
		File file = new File(fileName);

		// 파일이 없으면 만듦 (디렉터리 생성)
		if (!file.exists()) {
			logger.debug("Creating the file to write: {}", fileName);
			
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw e;
			}
		}

		// 파일 쓰기 전용으로 열기
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			// 파일 내용 쓰기
			writer.write(content);
		} catch (IOException e) {
			throw e;
		}

		logger.debug("Done writing to the file: {}; length: {}", fileName, content.length());
	}

}
