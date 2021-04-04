package com.grekooha.fastproxyfinder;

import com.grekooha.fastproxyfinder.exception.IncorrectDataException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Parser {

    public Optional<Document> getPage(String url) {
        try {
            return Optional.ofNullable(Jsoup.connect(url).get());
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public List<ProxyData> getProxiesList(Document document) {
        Elements trElements = document.getElementsByAttributeValue("class", "mt15").select("tbody").select("tr");
        System.out.println("Parsing is over.");
        System.out.println("==================");
        return trElements.stream().map(element -> {
            try {
                return new ProxyData(extractHost(element), extractPort(element), extractType(element));
            } catch (IncorrectDataException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


//    public static ArrayList<ProxyData> getProxies() {
//
//        try {
//            Document document = Jsoup.connect("https://proxy-checker.net/free-proxy/").get();
//            Elements trElements = document.getElementsByAttributeValue("class", "mt15").select("tbody").select("tr");
//            System.out.println("Parsing is over.");
//            System.out.println("==================");
//            ArrayList<ProxyData> proxies = new ArrayList<>(trElements.size());
//            trElements.forEach(element -> {
//                try {
//                    proxies.add(new ProxyData(extractHost(element), extractPort(element), extractType(element)));
//                } catch (IncorrectDataException e) {
//                    System.out.println(e.getMessage());
//                }
//            });
//            System.out.println("Lists of proxies and ports received.");
//            System.out.println("==================");
//            return proxies;
//        } catch (IOException ex) {
//            return new ArrayList<>();
//        }
//    }

    private String extractHost(Element element) {
        return element.child(0).text().trim();
    }

    private int extractPort(Element element) throws IncorrectDataException {
        String port = element.child(1).text().trim();
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            throw new IncorrectDataException("Incorrect port " + port);
        }
    }

    private Proxy.Type extractType(Element element) throws IncorrectDataException {
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