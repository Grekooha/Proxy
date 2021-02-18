package com.company;

import java.net.Proxy;

public class ProxyData {

    private String host;
    private int port;
    private Proxy.Type type;
    private int delay;

    public ProxyData(String host, int port, Proxy.Type type) {

        this.host = host;
        this.port = port;
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Proxy.Type getType() {
        return type;
    }

    public void setType(Proxy.Type type) {
        this.type = type;
    }
}
