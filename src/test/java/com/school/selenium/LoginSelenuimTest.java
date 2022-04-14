package com.school.selenium;

import  org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSelenuimTest {

    private WebDriver browser;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        browser = new ChromeDriver();
    }

    @Test
    public void loginPositiveTest1() {
        browser.get("http://localhost:8081/login");
        browser.findElement(By.id("username")).sendKeys("vova@mail.ru");
        browser.findElement(By.id("password")).sendKeys("qwerty");

        browser.findElement(By.className("btn")).click();

        Assert.assertEquals("http://localhost:8081/", browser.getCurrentUrl());
    }

    @Test
    public void loginNegativeTest1() {
        browser.get("http://localhost:8081/login");
        browser.findElement(By.id("username")).sendKeys("biba@gmail.com");
        browser.findElement(By.id("password")).sendKeys("boba");

        browser.findElement(By.className("btn")).click();

        Assert.assertEquals("http://localhost:8081/login?error", browser.getCurrentUrl());
    }

    @After
    public void destroy() {
        browser.close();
    }
}
