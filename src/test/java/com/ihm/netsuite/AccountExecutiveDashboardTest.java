/*
 * Copyright (C) 2017 Christopher Towner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ihm.netsuite;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Test of iHM's Account Executive dashboard on Netsuite.
 *
 * @author Christopher Towner
 */
public class AccountExecutiveDashboardTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        if (GraphicsEnvironment.isHeadless()) {
            options.addArguments("headless");
            options.addArguments("disable-gpu");
        }
        System.setProperty("webdriver.chrome.driver", "/tmp/binaries/linux/googlechrome/64bit/chromedriver");
        driver = new ChromeDriver(options);
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

    @Test
    public void testPortletHeaders() {
        System.out.println("testAdjustments");

        List<String> expected = new LinkedList<>(
                Arrays.asList("Shortcuts", "iHM Adjustments", "iHM Special Billing Requests",
                        "Settings", "AE Invoice Search", "iHM Payments", "Radio A/R Aging Dashboard",
                        "Non-Radio A/R Aging Dashboard", "KPI Meter", "Key Performance Indicators",
                        "Top 10 Clients By Balance"));

        for (WebElement e : driver.findElements(By.className("ns-portlet-header-text"))) {
            if (!expected.remove(e.getText())) {
                System.err.println("Found unexpected portlet header: " + e.getText());
            }
        }

        assertTrue(expected.isEmpty());
    }

}
