package com.company;

public class Main {

    public static void main(String[] args) {

//        ProxyHelper.findFastestProxy(Parsing.getProxies());

        ProxyData fast = ProxyHelper.findFastestProxy(Parser.getProxies());
        System.out.println("Result " + fast.getHost() + " " + fast.getDelay());
    }
}

