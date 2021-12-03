package Project.springProject.Service;

import Project.springProject.domain.Article;
import Project.springProject.repository.ArticleRepository;
import Project.springProject.repository.MemoryArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long create(Article article) {

        articleRepository.save(article);
        return article.getId();
    }

    public Article findById(Long l){
        return articleRepository.findById(l);
    }

    public Article delete(Article article){
        articleRepository.delete(article);
        return article;
    }


    public List<Article> findArticles(){
        return articleRepository.findAll();
    }
}
