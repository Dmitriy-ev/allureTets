package ru.appline.framework.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.appline.framework.basetestsclass.BaseTests;

public class FirstTest extends BaseTests {

    @Test()
    @DisplayName("Оформление программы ипотека на готовое жилье")
    public void startTest() {
        app.getStartPage()
                .selectBaseMenu("Ипотека")
                .selectSubMenu("Ипотека на готовое жильё")
                .filling("Стоимость недвижимости", "5180000")
                .filling("Первоначальный взнос", "3058000")
                .filling("Срок кредита", "30")
                .optionalServices("Скидка 0,3% при покупке квартиры на ДомКлик", "false")
                .optionalServices("Страхование жизни и здоровья", "false")
                .optionalServices("Молодая семья", "true")
                .optionalServices("Электронная регистрация сделки", "false")
                .checkFinalSum("Сумма кредита", "2122000")
                .checkFinalSum("Ежемесячный платеж", "16922")
                .checkFinalSum("Необходимый доход", "21784")
                .checkFinalSum("Процентная ставка", "11");
    }
}
