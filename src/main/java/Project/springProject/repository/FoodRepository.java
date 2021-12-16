package Project.springProject.repository;

import Project.springProject.domain.Food;

import java.util.List;

public interface FoodRepository {
    Food save(Food food);
    Food findById(Long id);
    List<Food> findAll();
}
