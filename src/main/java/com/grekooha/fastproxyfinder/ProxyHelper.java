package com.grekooha.fastproxyfinder;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Optional;

public class ProxyHelper {

    private static final String RESOURCE__URL = "http://rutracker.org";

    private Integer counter = 0;
    private Optional<ProxyData> fastestProxy = Optional.empty();

    public Optional<ProxyData> findFastestProxy(List<ProxyData> proxies) {
        System.out.println("Total proxies in the list: " + proxies.size());
        System.out.println("==================");
        proxies.parallelStream().forEach(this::checkProxy);
        waitResult(proxies);
        System.out.println("Done.");
        System.out.println("=================");
        return fastestProxy;
    }

    private void waitResult(List<ProxyData> proxies) {
        var timeStart = System.currentTimeMillis();
        int MAX_AWAIT_MS = 8000;
        while (counter < proxies.size()) {
            if ( System.currentTimeMillis() - timeStart > MAX_AWAIT_MS) break;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkProxy(ProxyData entry) {
        try {
            SocketAddress address = new InetSocketAddress(entry.getHost(), entry.getPort());
            URLConnection conn = new URL(RESOURCE__URL)
                    .openConnection(new Proxy(entry.getType(), address));
            conn.setConnectTimeout(100);
            long timeStart = System.currentTimeMillis();
            conn.connect();
            int delay = (int) (System.currentTimeMillis() - timeStart);
            synchronized (fastestProxy) {
                if (fastestProxy.isEmpty()) {
                    entry.setDelay(delay);
                    fastestProxy = Optional.of(entry);
                } else if (fastestProxy.get().getDelay() > delay) {
                    entry.setDelay(delay);
                    fastestProxy = Optional.of(entry);
                }
            }
            synchronized (counter) {
                counter++;
            }
        } catch ( IOException e) {
            synchronized (counter) {
                counter++;
            }
        }
    }
}