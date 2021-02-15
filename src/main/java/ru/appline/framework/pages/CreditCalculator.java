package ru.appline.framework.pages;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	 *iFrame 
	 */
	public CreditCalculator switchToData() {
		switchTo();
		scrollToElementJs(calculator);
		return this;
	}

	/**
	 *метод который проходится по первым данным 
	 */
	public CreditCalculator filling(String name, String data) {
		WebElement elementData;
		for (WebElement webElement : entryData) {
			elementData = webElement.findElement(By.xpath(".//div[@class='dc-input__label-4-9-1']"));
			if(elementData.getText().contains(name)) {
				WebElement inputData = webElement.findElement(By.xpath(".//../input"));
				inputData.sendKeys(Keys.CONTROL,"a");
				fillInputField(inputData, data);
			}
		}
		return this;
	}

	
	/**
	 *метод который проходится по вторым данным и делает клики
	 */
	public CreditCalculator optionalServices(String name){
		WebElement elementServices;
		for (WebElement webElement : services) {
			elementServices = webElement.findElement(By.xpath(".//span[@class='_1ZfZYgvLm4KBWPL41DOSO']"));
			if(elementServices.getText().contains(name)) {
				WebElement inputData = elementServices.findElement(By.xpath(".//../..//input"));
				scrollToElementJs(additionalInformation);
				checked(inputData);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	/**
	 *выполняем проверки 
	 */
	public CreditCalculator checkFinalSum(String name, String price) {
		scrollToElementJs(calculator);
		WebElement finalSumElement;
		for (WebElement webElement : finalSum) {
			finalSumElement = webElement.findElement(By.xpath(".//span"));
			if(finalSumElement.getText().contains(name)) {
				Assert.assertEquals("сумма поля: " + name + " не соответствует веденному числу",
						price, webElement.getAttribute("innerText").replaceAll("[^,.0-9]+", ""));
			}
		}
		return this;
	}

}
