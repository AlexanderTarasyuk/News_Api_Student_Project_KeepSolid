package com.example.alextarasyuk.news_api_student_project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTarasyuk
 */

public class NewsStore {
    private static List<Article> articles = new ArrayList<>();

    public static List<Article> getArticles() {
        return articles;
    }

    public static void setArticles(List<Article> articles) {
        NewsStore.articles = articles;
    }
}
