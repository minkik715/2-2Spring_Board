package Project.springProject.repository;

import Project.springProject.domain.Article;
import Project.springProject.domain.Food;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryFoodRepository implements FoodRepository{

    private Map<Long, Food> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Food save(Food food) {
        food.setId(++sequence);
        store.put(food.getId(), food);
        return food;
    }

    @Override
    public Food findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(store.values());
    }
}
