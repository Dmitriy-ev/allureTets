package ru.appline.framework.pages;


import static ru.appline.framework.managers.DriverManager.getDriver;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import io.qameta.allure.Step;

public class CreditCalculator extends BasePage {

	/**
	 * пас на калькулятор
	 */
	@FindBy(xpath = "//div[@id='calculator-root']")
	WebElement calculator;

	/**
	 * пас на первые данные
	 */
	@FindBy(xpath = "//div[@class='_170Vqyw0UXnblvLWyTFULw']")
	List<WebElement> entryData;

	/**
	 * пас на вторые данные
	 */
	@FindBy(xpath = "//div[@class='_1SP5M8CKBcG5upuK036cJf']")
	List<WebElement> services;

	/**
	 * пас для скрола 
	 */
	@FindBy(xpath = "//div[@class='_1CyiyN1qJdh_dWG5pvvrcw'][text()='Дополнительные услуги, снижающие ставку по кредиту']")
	WebElement additionalInformation;

	
	/**
	 * пас для проверки правильности данных
	 */
	@FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']")
	List<WebElement> finalSum;

	
	
	
	/**
	 *метод который проходится по первым данным 
	 */
	@Step("Заполняем поле {name}")
	public CreditCalculator filling(String name, String data) {
		try {
			getDriver().switchTo().frame(0);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
		scrollToElementJs(calculator);
		WebElement elementData;
		for (WebElement webElement : entryData) {
			elementData = webElement.findElement(By.xpath(".//div[@class='dc-input__label-4-9-1']"));
			if(elementData.getText().contains(name)) {
				WebElement inputData = webElement.findElement(By.xpath(".//../input"));
				fillInputField(inputData, data);
			}
		}
		getDriver().switchTo().parentFrame();
		return this;
	}

	
	/**
	 *метод который проходится по вторым данным и делает клики
	 */
	@Step("Кликаем по {name}")
	public CreditCalculator optionalServices(String name, String flag){
		try {
			getDriver().switchTo().frame(0);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
		WebElement elementServices;
		for (WebElement webElement : services) {
			elementServices = webElement.findElement(By.xpath(".//span[@class='_1ZfZYgvLm4KBWPL41DOSO']"));
			if(elementServices.getText().contains(name)) {
				WebElement inputData = elementServices.findElement(By.xpath(".//../..//input"));
				scrollToElementJs(additionalInformation);
				switchOptionServices(inputData, flag);
			}
		}
		getDriver().switchTo().parentFrame();
		return this;
	}

	/**
	 *выполняем проверки 
	 */
	@Step("Выполняем проверку {name}")
	public CreditCalculator checkFinalSum(String name, String price) {
		try {
			getDriver().switchTo().frame(0);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
		scrollToElementJs(calculator);
		WebElement finalSumElement;
		for (WebElement webElement : finalSum) {
			finalSumElement = webElement.findElement(By.xpath(".//span"));
			if(finalSumElement.getText().contains(name)) {
				
				Assert.assertEquals("сумма поля: " + name + " не соответствует веденному числу",
						price, webElement.getAttribute("innerText").replaceAll("[^,.0-9]+", ""));
			}
		}
		getDriver().switchTo().parentFrame();
		return this;
	}

}
