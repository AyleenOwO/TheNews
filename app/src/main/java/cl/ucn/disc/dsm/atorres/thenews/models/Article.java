package cl.ucn.disc.dsm.atorres.thenews.models;

import lombok.Getter;

public class Article {

    @Getter
    private Source source;

    @Getter
    private String author;

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private String url;

    @Getter
    private String urlToImage;

    @Getter
    private String publishedAt;

    @Getter
    private String content;
}
