/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihm.netsuite;

import java.awt.GraphicsEnvironment;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Abstract Netsuite test class.
 *
 * @author Christopher Towner
 */
public abstract class NetsuiteTest {

    protected static WebDriver driver;

    @BeforeClass
    public static void setupDriver() {
        if (GraphicsEnvironment.isHeadless()) {
            System.setProperty("phantomjs.binary.path", "/tmp/binaries/linux/phantomjs/64bit/phantomjs");
            driver = new PhantomJSDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "/tmp/binaries/linux/googlechrome/64bit/chromedriver");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }

    @Before
    public void signIn() {
        String username = System.getenv("NETSUITE_TEST_USERNAME");
        String password = System.getenv("NETSUITE_TEST_PASSWORD");
        driver.get("https://system.sandbox.netsuite.com/pages/customerlogin.jsp");
        driver.findElement(By.id("userName")).sendKeys(username);
        driver.findElement(By.className("password")).findElement(By.tagName("input")).sendKeys(password);
        driver.findElement(By.className("submitButton")).click();
    }

    @After
    public void signOut() {
        driver.get("https://system.sandbox.netsuite.com/pages/nllogoutnoback.jsp");
    }
}
