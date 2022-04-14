package com.school.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Ignore
public class ChangePasswordSelenuimTest {

    private WebDriver browser;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        browser = new ChromeDriver();
    }

    @Test
    public void changePasswordPositiveTest1() {
        browser.get("http://localhost:8081/login");
        browser.findElement(By.id("username")).sendKeys("vova@mail.ru");
        browser.findElement(By.id("password")).sendKeys("qwerty");

        browser.findElement(By.className("btn")).click();

        browser.findElement(By.id("clientUpdateClient")).click();
        browser.findElement(By.id("changePasswordButton")).click();

        browser.findElement(By.id("passwordString")).sendKeys("qwerty");
        browser.findElement(By.id("passwordString2")).sendKeys("qwerty");

        browser.findElement(By.className("btn")).click();

        String message = browser.findElement(By.className("alert")).getText();
        Assert.assertEquals("Client updated successfully", message);
    }

    @Test
    public void changePasswordPositiveTest2() {
        browser.get("http://localhost:8081/login");
        browser.findElement(By.id("username")).sendKeys("vova@mail.ru");
        browser.findElement(By.id("password")).sendKeys("qwerty");

        browser.findElement(By.className("btn")).click();

        browser.findElement(By.id("clientUpdateClient")).click();
        browser.findElement(By.id("changePasswordButton")).click();

        browser.findElement(By.id("passwordString")).sendKeys("biba");
        browser.findElement(By.id("passwordString2")).sendKeys("boba");

        browser.findElement(By.className("btn")).click();

        String message = browser.findElement(By.className("alert")).getText();
        Assert.assertEquals("Can't update password. New passwords doesn't match", message);
    }

    @After
    public void destroy() {
        browser.close();
    }
}
