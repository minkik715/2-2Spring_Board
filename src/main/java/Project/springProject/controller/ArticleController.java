package Project.springProject.controller;

import Project.springProject.Service.ArticleService;
import Project.springProject.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value ="articles/new")
    public String createForm(){
        return "articles/createArticleForm";
    }
    @PostMapping("articles/new")
    public  String create(ArticleForm form){
        Article article = new Article();
        article.setName(form.getName());
        article.setContent(form.getContent());

        articleService.create(article);

        return "redirect:/";
    }
    @GetMapping("/articles")
    public String list(Model model){
        List<Article> articles = articleService.findArticles();
        model.addAttribute("articles", articles);
        return "articles/articleList";
    }

    @GetMapping("article")
    public String view(Model model){
        Article article = articleService.findById(1L);
        model.addAttribute("article",article);
        return "articles/article";
    }
    @PostMapping("article/delete")
    public String delete(ArticleForm form){
        Article article =

    }
}
