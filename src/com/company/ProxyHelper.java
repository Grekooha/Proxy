package com.company;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ProxyHelper {

    private static final String RESOURCE__URL = "http://rutracker.org";

    public static ProxyData findFastestProxy(ArrayList<ProxyData> proxies) {
        System.out.println("Total proxies in the list: " + proxies.size());
        System.out.println("==================");
        ProxyData fastProxy = null;
        for (ProxyData entry : proxies)
            try {
                SocketAddress sa = new InetSocketAddress(entry.getHost(), entry.getPort());
                Proxy proxy = new Proxy(entry.getType(), sa);
                URL url = new URL(RESOURCE__URL);
                URLConnection conn = url.openConnection(proxy);
                conn.setConnectTimeout(100);
                long timeStart = System.currentTimeMillis();
                conn.connect();
                int delay = (int) (System.currentTimeMillis() - timeStart);
                if (fastProxy == null) {
                    entry.setDelay(delay);
                    fastProxy = entry;
                } else if (fastProxy.getDelay() > delay) {
                    entry.setDelay(delay);
                    fastProxy = entry;
                }

            } catch (IOException e) {
                e.getMessage();
            }
        System.out.println("Done.");
        System.out.println("=================");
        return fastProxy;
    }

}