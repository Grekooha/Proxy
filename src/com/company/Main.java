package com.company;

public class Main {

    public static void main(String[] args) {

//        ProxyHelper.findFastestProxy(Parsing.getProxies());
        var ph = new ProxyHelper();
        var t1 = System.currentTimeMillis();
        var fast = ph.findFastestProxy(Parser.getProxies());
        var t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println("Result " + fast.get().getHost() + " " + fast.get().getDelay());
    }
}

