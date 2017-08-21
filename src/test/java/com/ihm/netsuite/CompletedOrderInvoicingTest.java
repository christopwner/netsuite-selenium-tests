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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Test case #149.
 *
 * @author Christopher Towner
 */
public class CompletedOrderInvoicingTest extends NetsuiteTest {

    @Test
    public void testEnterCutoffDate() {
        System.out.println("testCutoffDate");

        driver.get("https://system.sandbox.netsuite.com/app/site/hosting/scriptlet.nl?script=114&deploy=1");
        
        driver.findElement(By.id("custpage_cut_off_date")).clear();

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        driver.findElement(By.id("custpage_cut_off_date")).sendKeys(dateFormat.format(date));
        
        driver.findElement(By.id("custpage_btn_get_so")).click();
    }

}
