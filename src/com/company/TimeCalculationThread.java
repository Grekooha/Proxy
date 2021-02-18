//package com.company;
//
//import java.io.IOException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//
//public class TimeCalculationThread {
//
//    private static final String RESOURCE__URL = "http://rutracker.org";
//
//    public static ProxyData findFastestProxy(ArrayList<ProxyData> proxies) {
//        System.out.println("Total proxies in the list: " + proxies.size());
//        System.out.println("==================");
//        ProxyData fastProxy = null;
//
//        for (ProxyData entry : proxies) {
//            System.setProperty("http.proxyHost", entry.getHost());
//            System.setProperty("http.proxyPort", entry.getPort());
//
//            try {
//                URL url = new URL(RESOURCE__URL);
//                URLConnection conn = url.openConnection();
//                conn.setConnectTimeout(100);
//                long timeStart = System.currentTimeMillis();
//                conn.connect();
//                int delay = (int) (System.currentTimeMillis() - timeStart);
//
//                if (fastProxy == null) {
//                    entry.setDelay(delay);
//                    fastProxy = entry;
//                } else if (fastProxy.getDelay() > delay) {
//                    entry.setDelay(delay);
//                    fastProxy = entry;
//                }
//
//            } catch (IOException e) {
//                e.getMessage();
//            }
//
//        }
//        System.out.println("Done.");
//        System.out.println("=================");
//        return fastProxy;
//    }
//}