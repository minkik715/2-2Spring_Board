package Project.springProject.Service;

import Project.springProject.domain.Article;
import Project.springProject.domain.Food;
import Project.springProject.repository.ArticleRepository;
import Project.springProject.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Long create(Food food) {

        foodRepository.save(food);
        return food.getId();
    }

    public Food findById(Long l){
        return foodRepository.findById(l);
    }


    public List<Food> findFoods(){
        return foodRepository.findAll();
    }
}
