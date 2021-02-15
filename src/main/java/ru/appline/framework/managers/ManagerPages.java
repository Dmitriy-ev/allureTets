package ru.appline.framework.managers;

import ru.appline.framework.pages.CreditCalculator;
import ru.appline.framework.pages.StartPage;

/**
 * Класс для управления страничками
 */
public class ManagerPages {

	/**
	 * Менеджер страничек
	 */
	private static ManagerPages managerPages;

	/**
	 * Стартовая страничка
	 */
	StartPage startPage;

	/**
	 * Страничка калькулятора для ипотеки
	 */

	CreditCalculator creditPage;

	/**
	 * Конструктор специально запривейтили (синглтон)
	 */
	private ManagerPages() {
	}

	/**
	 * Ленивая инициализация ManagerPages
	 */
	public static ManagerPages getManagerPages() {
		if (managerPages == null) {
			managerPages = new ManagerPages();
		}
		return managerPages;
	}

	/**
	 * Ленивая инициализация {@link ru.appline.framework.pages.StartPage}
	 */
	public StartPage getStartPage() {
		if (startPage == null) {
			startPage = new StartPage();
		}
		return startPage;
	}

	public CreditCalculator getCreditCalculator() {
		if(creditPage == null) {
			creditPage = new CreditCalculator();
		}
		return creditPage;
	}
}
