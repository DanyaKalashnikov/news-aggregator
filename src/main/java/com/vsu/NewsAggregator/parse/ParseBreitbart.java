package com.vsu.NewsAggregator.parse;

import com.vsu.NewsAggregator.model.old.breitbart.ItemB;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseBreitbart {

    private static final String url = "https://feeds.feedburner.com/breitbart";
    private static final String TAG_ITEM = "item";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINK = "link";
    private static final String TAG_CATEGORY = "category";

    public static List<Node> parseDocToItems(){
        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node root = doc.getFirstChild();
        //List<ItemB> items = parseChannel(root);
        List<Node> items = parseChannel(root);
        return items;
    }

    private static Document buildDocument() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(url);
    }

    /*private static List<ItemB> parseChannel(Node root){
        NodeList channelNode = root.getChildNodes().item(1).getChildNodes();
        List<ItemB> items = new ArrayList<>();

        for(int i = 0; i < channelNode.getLength(); i++){
            Node node = channelNode.item(i);
            if(node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (TAG_ITEM.equals(node.getNodeName())) {
                items.add(parseItem(node));
            }
        }
        return items;
    }*/
    private static List<Node> parseChannel(Node root){
        NodeList channelNode = root.getChildNodes().item(1).getChildNodes();
        List<Node> items = new ArrayList<>();

        for(int i = 0; i < channelNode.getLength(); i++){
            Node node = channelNode.item(i);
            if(node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (TAG_ITEM.equals(node.getNodeName())) {
                items.add(node);
            }
        }
        return items;
    }

    public static ItemB parseItem(Node node){
        ItemB item = new ItemB();
        List<String> tags = new ArrayList<>();
        NodeList nodeChildNodes = node.getChildNodes();
        for (int j = 0; j < nodeChildNodes.getLength(); j++){
            Node itemNode = nodeChildNodes.item(j);
            if(itemNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (itemNode.getNodeName()){
                case TAG_TITLE -> item.setTitle(itemNode.getTextContent());
                case TAG_CATEGORY -> tags.add(itemNode.getTextContent());
                case TAG_LINK -> {
                    item.setLink(itemNode.getTextContent());
                    try {
                        List<String> text = parseHtml(itemNode.getTextContent());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        item.setTags(tags);
        return item;
    }

    private static List<String> parseHtml(String url) throws IOException {
        List<String> text = new ArrayList<>();
        org.jsoup.nodes.Document doc = Jsoup.connect(url)
                .userAgent("OperaGx")
                .referrer("https://google.com")
                .get();
        String paragraph = "";
        Element news = doc.getElementsByClass("entry-content").first();
        Elements paragraphs = news.children();
        for (Element node : paragraphs){
            String link = "";
            if(node.tagName().equals("p")){
                //text = String.format("%s %s", text, node.text());
                paragraph = node.text();
                text.add(paragraph);
            }
            else if(node.tagName().equals("div")){
                link = node.select("img").attr("src");
                text.add(link);
                text.add(node.text());
                //text = String.format("%s %s", text, link + " " + node.text());
            }
            //paragraph = link + node.text();
            //text.add(paragraph);
        }
        String aaa = "aaa";
        return text;
    }
}
