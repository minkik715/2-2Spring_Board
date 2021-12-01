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
        validate(article); //중복검증

        articleRepository.save(article);
        return article.getId();
    }

    private void validate(Article article) {
        if (article != null) {
            throw new IllegalStateException("이미 존재하는 글입니다.");
        }
    }
    public List<Article> findArticles(){
        return articleRepository.findAll();
    }
}
