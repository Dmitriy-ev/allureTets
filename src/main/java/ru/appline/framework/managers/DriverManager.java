package ru.appline.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static ru.appline.framework.utils.PropConst.*;

/**
 * Класс для управления веб драйвером
 */
public class DriverManager {

	private static WebDriver driver;

	private static TestPropManager props = TestPropManager.getTestPropManager();

	private DriverManager() {
	}

	private static void initDriver() {
		switch (props.getProperty(TYPE_BROWSER)) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", props.getProperty(PATH_GEKO_DRIVER));
			driver = new FirefoxDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER));
			driver = new ChromeDriver();
		}
	}

	/**
	 * Метод ленивой инициализации веб драйвера
	 * @return WebDriver - возвращает веб драйвер
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			initDriver();
		}
		return driver;
	}

	/**
	 * Метод для закрытия сессии драйвера и браузера
	 * @see WebDriver#quit()
	 */
	public static void quitDriver() {
		driver.quit();
		driver = null;
	}
}
