package Project.springProject.controller;

import Project.springProject.Service.ArticleService;
import Project.springProject.Service.FoodService;
import Project.springProject.domain.Article;
import Project.springProject.domain.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final FoodService foodService;

    @Autowired
    public ArticleController(ArticleService articleService, FoodService foodService) {
        this.articleService = articleService;
        this.foodService = foodService;
    }

    //메인 화면에서 글쓰기로 들어가는 부분
    @GetMapping(value ="articles/new")
    public String createForm(){
        return "articles/createArticleForm";
    }

    //글쓰기 완료 후 메인 페이지로 돌아가는 부분
    @PostMapping("articles/new")
    public  String create(ArticleForm form){
        Article article = new Article();
        article.setName(form.getName());
        article.setContent(form.getContent());

        articleService.create(article);

        return "redirect:/";
    }

    //메인 페이지에서 글의 목록으로 들어가는 부분
    @GetMapping("/articles")
    public String list(Model model){
        List<Article> articles = articleService.findArticles();
        model.addAttribute("articles", articles);
        return "articles/articleList";
    }

    //글 목록에서 수정하기로 넘어가는 부분
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

    //수정을 완료한 후 메인으로 넘어가는 부분
    @PostMapping("articles/update")
    public String update(Model model, ArticleForm articleForm){
        Article article = new Article();
        article.setName(articleForm.getName());
        article.setContent(articleForm.getContent());

        articleService.create(article);

        return "redirect:/";
    }

    //글 목록에서 삭제 버튼을 누르는 부분
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

    //포스트에서 값을 가져오는 부분
    @RequestMapping(value = "/addFood", method = RequestMethod.POST)
    public String addFood(Model model, @RequestParam Map<String, Object> paramMap){
        Food food = new Food();
        food.setComment(paramMap.get("comment_give").toString());
        food.setImage(paramMap.get("image_give").toString());
        food.setName(paramMap.get("name_give").toString());
        foodService.create(food);
        List<Food> foods = foodService.findFoods();
        model.addAttribute("foods", foods);
        return "redirect:/";
    }

    //가져 온 값을 뿌리는 부분
    @GetMapping("/memory")
    public String goList(Model model){
        List<Food> Foods = foodService.findFoods();
        model.addAttribute("foods", Foods);
        return "articles/foodArticle";
    }

}