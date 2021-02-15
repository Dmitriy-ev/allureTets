package ru.appline.framework.managers;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.managers.DriverManager.getDriver;
import static ru.appline.framework.managers.DriverManager.quitDriver;
import static ru.appline.framework.utils.PropConst.*;

/**
 * Класс для инициализации фреймворка
 */
public class InitManager {

	/**
	 * Менеджер пропертей
	 */
	public static TestPropManager props = TestPropManager.getTestPropManager();

	/**
	 * Инициализация фреймворка и запуск браузера со страницей приложения
	 */
	public static void initFramework() {
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
		getDriver().get(props.getProperty(APP_URL));
	}

	/**
	 * Завершения работы фреймворка - гасит драйвер и закрывает сессию с браузером
	 */
	public static void quitFramework() {
		quitDriver();
	}
}
