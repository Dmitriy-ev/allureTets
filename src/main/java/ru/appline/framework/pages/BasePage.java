package ru.appline.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.ManagerPages;


import static ru.appline.framework.managers.DriverManager.getDriver;

/**
 * Базовый класс всех страничек
 */
public class BasePage {

	/**
	 * Менеджер страничек
	 */
	protected ManagerPages app = ManagerPages.getManagerPages();

	/**
	 * Объект для имитации реального поведения мыши или клавиатуры
	 */
	protected Actions action = new Actions(getDriver());

	/**
	 * Объект для выполнения любого js кода
	 */
	protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

	/**
	 * Объект явного ожидания
	 * При применении будет ожидать задонного состояния 10 секунд с интервалом в 1 секунду
	 */
	protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

	/**
	 * Конструктор позволяющий инициализировать все странички и их элементы помеченные анотацией 
	 */
	public BasePage() {
		PageFactory.initElements(getDriver(), this);
	}

	/**
	 * Функция позволяющая скролить до любого элемента с помощью js
	 */
	protected void scrollToElementJs(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Явное ожидание состояния кликабельности элемента
	 */
	protected WebElement elementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected WebElement elementToBeVisible(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void scrollWithOffset(WebElement element, int x, int y) {
		String code = "window.scroll(" + (element.getLocation().x + x) + ","
				+ (element.getLocation().y + y) + ");";
		((JavascriptExecutor) getDriver()).executeScript(code, element, x, y);
	}

	/**
	 * Общий метод по заполнению полей 
	 *
	 */
	protected void fillInputField(WebElement field, String value) {
		field.sendKeys(Keys.CONTROL + "a");
		field.sendKeys(value);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void switchOptionServices(WebElement element, String flag) {
		if (!element.getAttribute("aria-checked").equals(flag)) {
			element.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
