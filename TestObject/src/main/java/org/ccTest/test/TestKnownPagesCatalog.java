package org.ccTest.test;

import org.ccTest.common.pageobject.KnownPage;
import org.ccTest.common.pageobject.KnownPagesCatalog;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TestKnownPagesCatalog implements KnownPagesCatalog {

    @Override
    public Iterable<? extends KnownPage> getAllPages() {
        return Arrays.asList(KnownPages.values());
    }
}
