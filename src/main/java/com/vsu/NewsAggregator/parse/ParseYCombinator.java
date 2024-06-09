package com.vsu.NewsAggregator.parse;

import com.vsu.NewsAggregator.model.old.ycombinator.ChannelY;
import com.vsu.NewsAggregator.model.old.ycombinator.ItemY;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class ParseYCombinator {

    private static final String url = "https://feeds.feedburner.com/ycombinator/news";
    private static final String TAG_ITEM = "item";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINK = "link";

    public static ChannelY parse(){
        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node root = doc.getFirstChild();
        List<ItemY> items = parseChannel(root);
        return new ChannelY(items);
    }

    private static Document buildDocument() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(url);
    }

    private static List<ItemY> parseChannel(Node root){
        NodeList channelNode = root.getFirstChild().getChildNodes();
        List<ItemY> items = new ArrayList<>();

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
    }

    private static ItemY parseItem(Node node){
        ItemY item = new ItemY();
        NodeList nodeChildNodes = node.getChildNodes();
        for (int j = 0; j < nodeChildNodes.getLength(); j++){
            Node itemNode = nodeChildNodes.item(j);
            if(itemNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (itemNode.getNodeName()){
                case TAG_TITLE -> item.setTitle(itemNode.getTextContent());
                case TAG_LINK -> item.setLink(itemNode.getTextContent());
            }
        }
        return item;
    }


}
