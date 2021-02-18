package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        var ph = new ProxyHelper();
        var t1 = System.currentTimeMillis();
        ArrayList<ProxyData> pr = Parser.getProxies();
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

