package ru.appline.framework.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для управления пропертями
 */
public class TestPropManager {

	/**
	 * Переменна для хранения считанных из файла пропертей
	 */
	private final Properties properties = new Properties();

	/**
	 * Переменна для хранения объекта TestPropManager
	 */
	private static TestPropManager INSTANCE = null;

	/**
	 * Конструктор специально запривейтили (синглтон)
	 * Происходит загрузка содержимого файла application.properties
	 */
	private TestPropManager() {
		try {
			properties.load(new FileInputStream(
					new File("src/main/resources/" +
							System.getProperty("env", "application") + ".properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Метод ленивой инициализации TestPropManager
	 */
	public static TestPropManager getTestPropManager() {
		if (INSTANCE == null) {
			INSTANCE = new TestPropManager();
		}
		return INSTANCE;
	}

	/**
	 * Метод возвращает либо значение записанное в ключе в переменной 
	 * либо дефолтное значение при отсутствие ключа в переменной 
	 */
	public String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	/**
	 * Метод возвращает значения записанное в ключе в переменной {@link #properties}, если нет перемнной вернет null
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
