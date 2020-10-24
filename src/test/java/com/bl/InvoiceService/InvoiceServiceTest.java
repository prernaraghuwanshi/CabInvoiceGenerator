package com.bl.InvoiceService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceGenerator invoiceGenerator = null;

    @Before
    public void initialize() {
        invoiceGenerator = new InvoiceGenerator();
    }
}
