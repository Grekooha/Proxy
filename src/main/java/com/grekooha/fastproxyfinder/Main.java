package com.grekooha.fastproxyfinder;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        var ph = new ProxyHelper();
        var t1 = System.currentTimeMillis();
        var parser = new Parser();
        var page = parser.getPage("https://proxy-checker.net/free-proxy/")
                .orElseThrow(() -> new RuntimeException("Can't access proxy list page"));
        List<ProxyData> pr = parser.getProxiesList(page);
        if (!pr.isEmpty()) {
            var fast = ph.findFastestProxy(pr);
            var t2 = System.currentTimeMillis();
            System.out.println(t2 - t1);
            System.out.println("Result " + fast.get().getHost() + " " + fast.get().getDelay());
        } else {
            System.out.println("Failed to get proxy.");
        }
    }
}

