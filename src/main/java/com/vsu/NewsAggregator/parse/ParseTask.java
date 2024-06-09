package com.vsu.NewsAggregator.parse;

import com.vsu.NewsAggregator.model.News;
import com.vsu.NewsAggregator.model.TagNews;
import com.vsu.NewsAggregator.model.compositeKey.TagNewsKey;
import com.vsu.NewsAggregator.model.old.breitbart.ItemB;
import com.vsu.NewsAggregator.repository.TagNewsRepository;
import com.vsu.NewsAggregator.service.NewsService;
import com.vsu.NewsAggregator.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ParseTask {


    private final NewsService newsService;
    private final TagService tagService;
    private final TagNewsRepository tagNewsRepository;

    @Scheduled(fixedDelay = 10000)
    public void parseNewNews() {
        //String url = "https://edition.cnn.com/";
        //String url = "https://feeds.feedburner.com/ycombinator/news";
        //ChannelY channel = ParseYCombinator.parse();
        //ChannelB channel = ParseBreitbart.parse();
        List<Node> items = ParseBreitbart.parseDocToItems();

        for (Node node : items) {
            ItemB item = ParseBreitbart.parseItem(node);
            if (newsService.isExists(item.getTitle())) {
                StringBuilder content = new StringBuilder();
                for (String s : item.getText()) {
                    content.append(s);
                }
                Set<UUID> tags = new HashSet<>();
                for (String t : item.getTags()) {
                    tags.add(tagService.getTagByName(t));
                }
                News news = newsService.saveNews(News.builder()
                        .title(item.getTitle())
                        .content(content.toString())
                        .dateTime(LocalDateTime.now())
                        .build());
                Set<TagNews> setTagNews = new HashSet<>();
                for(UUID tagId : tags){
                    TagNewsKey tagNewsKey = TagNewsKey.builder()
                            .tagId(tagId)
                            .newsId(news.getId())
                            .build();
                    TagNews tagNews = TagNews.builder()
                            .id(tagNewsKey)
                            .tagId(tagId)
                            .newsId(news.getId())
                            .build();
                    setTagNews.add(tagNews);
                    tagNewsRepository.save(tagNews);
                }
                news.setTagsId(setTagNews);
                newsService.saveNews(news);
            }
        }

        /*for (ItemB item : channel.getItems()){
            String title = item.getTitle();
            String link = item.getLink();
            List<String> tags = item.getTags();
            if(!newsService.isExist(title)){
                News obj = new News();
                obj.setTitle(title);
                obj.setLink(link);
                obj.setTags(tags);
                newsService.saveNews(obj);
            }
        }*/

        /*try {
            Document doc = Jsoup.connect(url)
                    .userAgent("OperaGx")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();
            Elements news = doc.getElementsByClass("container__headline-text");
            for(Element element : news){
                String title = element.ownText();
                if(!newsService.isExist(title)){
                    News obj = new News();
                    obj.setTitle(title);

                    newsService.save(obj);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }
}
