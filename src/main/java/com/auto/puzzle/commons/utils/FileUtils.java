package com.auto.puzzle.commons.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.auto.puzzle.configuration.Config;

public class FileUtils {

	public static List<String> readFileLineByLineFromResources(String path) throws IOException {
		Path filePath = Paths.get(Config.MAIN_RESOURCE_PATH + path);

		createFileIfDoesNotExist(filePath);

		List<String> result = new ArrayList<>();

		try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

//		Class<StartGame> clazz = StartGame.class;
//		InputStream inputStream = clazz.getResourceAsStream(path);
//
////		StringBuilder resultStringBuilder = new StringBuilder();
//
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
//			String line;
//
//			while ((line = br.readLine()) != null) {
//				result.add(line);
////				resultStringBuilder.append(line).append("\n");
//			}
//		}

		return result;
//		return resultStringBuilder.toString();
	}

	public static void writeContentToResourceFile(String path, String content) throws IOException {
		Path filePath = Paths.get(Config.MAIN_RESOURCE_PATH + path);

		createFileIfDoesNotExist(filePath);

		content = "\n" + content;

		try {
            Files.write(filePath, content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void createFileIfDoesNotExist(Path filePath) throws IOException {
		if (Files.notExists(filePath))
			try {
				Files.createFile(filePath);
			} catch (IOException e) {
				File file = new File(filePath.toAbsolutePath().toString());

				if (! file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
			}
	}
}
