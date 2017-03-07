package com.example.difm.mynewsfeed;

/**
 * Created by difm on 03/01/2017.
 */

public class Article{

    String content;
    String title;
    String link;

    public Article(String title, String content, String link) {
        this.content = content;
        this.title = title;
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
