package com.grekooha.fastproxyfinder;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    @Test
    public void getPageReturnBadURL() {
        var parser = new Parser();
        var doc = parser.getPage("334234");

        Assert.assertTrue(doc.isEmpty());
    }

    @Test
    public void getPageReturnCorrectURL() {
        var parser = new Parser();
        var doc = parser.getPage("http://google.com");
        Assert.assertFalse(doc.isEmpty());
    }

    @Test
    public void getProxiesList() {
        var parser = new Parser();
        var res = parser.getProxiesList(Document.createShell("http://google.com"));
        Assert.assertTrue(res.isEmpty());
    }
}