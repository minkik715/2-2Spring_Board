package Project.springProject.repository;

import Project.springProject.domain.Article;

import java.util.List;

public interface ArticleRepository {
    Article save(Article article);
    Article findById(Long id);
    List<Article> findAll();

}
