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

    @PostMapping("articles/updatePage")
    public String updateForm(Model model, ArticleForm articleForm){
        List<Article> articles = articleService.findArticles();
        String postContent = articleForm.getContent();
        for (Article article: articles) {
            if (article.getContent().equals(postContent)) {
                model.addAttribute("article", article);
                articleService.delete(article);
                break;
            }
        }
        return "articles/updateArticleForm";}

    @PostMapping("articles/update")
    public String update(Model model, ArticleForm articleForm){
        Article article = new Article();
        article.setName(articleForm.getName());
        article.setContent(articleForm.getContent());

        articleService.create(article);

        return "redirect:/";
    }
    @PostMapping("/articles/delete")
    public String delete(Model model,ArticleForm articleForm){
        String postContent = articleForm.getContent();
        List<Article> articles = articleService.findArticles();
        for (Article article: articles) {
            if(article.getContent().equals(postContent)){
                articleService.delete(article);
                break;
            }
        }
        articles = articleService.findArticles();
        model.addAttribute("articles", articles);
        return "articles/articleList";
    }
}