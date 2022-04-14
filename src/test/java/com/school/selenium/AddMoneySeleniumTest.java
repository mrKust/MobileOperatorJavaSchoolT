package com.school.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Ignore
public class AddMoneySeleniumTest {

    private WebDriver browser;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        browser = new ChromeDriver();
    }

    @Test
    public void addMoneyPositiveTest1() {
        browser.get("http://localhost:8081/login");
        browser.findElement(By.id("username")).sendKeys("vova@mail.ru");
        browser.findElement(By.id("password")).sendKeys("qwerty");

        browser.findElement(By.className("btn")).click();

        browser.findElement(By.id("clientUpdateClient")).click();
        browser.findElement(By.id("addMoneyButton")).click();

        browser.findElement(By.id("money")).sendKeys("100");
        browser.findElement(By.id("cardNumber")).sendKeys("4276120034879512");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        browser.findElement(By.id("datefield")).sendKeys(dtf.format(now));
        browser.findElement(By.id("firstName")).sendKeys("Vova");
        browser.findElement(By.id("surname")).sendKeys("Mashulin");
        browser.findElement(By.id("cvc")).sendKeys("658");

        browser.findElement(By.className("btn")).click();

        String message = browser.findElement(By.className("alert")).getText();
        Assert.assertEquals("Money added successfully", message);
    }

    @After
    public void destroy() {
        browser.close();
    }
}
