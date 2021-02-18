package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<ProxyData> getProxies() {

        try {
            Document document = Jsoup.connect("https://proxy-checker.net/free-proxy/").get();
            Elements trElements = document.getElementsByAttributeValue("class", "mt15").select("tbody").select("tr");
            System.out.println("Parsing is over.");
            System.out.println("==================");
            ArrayList<ProxyData> proxies = new ArrayList<>(trElements.size());
            trElements.forEach(element -> {
                try {
                    proxies.add(new ProxyData(extractHost(element), extractPort(element), extractType(element)));
                } catch (IncorrectDataException e) {
                    System.out.println(e.getMessage());
                }
            });
            System.out.println("Lists of proxies and ports received.");
            System.out.println("==================");
            return proxies;

        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static String extractHost(Element element) {
        return element.child(0).text().trim();
    }

    private static int extractPort(Element element) throws IncorrectDataException {
        String port = element.child(1).text().trim();
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            throw new IncorrectDataException("Incorrect port " + port);
        }
    }

    private static Proxy.Type extractType(Element element) throws IncorrectDataException {
        String type = element.child(3).text().trim();
        switch (type) {
            case "HTTP":
                return Proxy.Type.HTTP;
            case "SOCKS4":
            case "SOCKS5":
                return  Proxy.Type.SOCKS;
            default:
                throw new IncorrectDataException("Incorrect proxy type " + type);
        }
    }
}