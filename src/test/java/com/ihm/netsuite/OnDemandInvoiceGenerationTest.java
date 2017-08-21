/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihm.netsuite;

import org.junit.Test;

/**
 * @author Christopher Towner
 */
public class OnDemandInvoiceGenerationTest extends NetsuiteTest {

    @Test
    public void testNavigate() {
        driver.get("https://system.sandbox.netsuite.com/app/site/hosting/scriptlet.nl?script=115&deploy=1");
    }
}
