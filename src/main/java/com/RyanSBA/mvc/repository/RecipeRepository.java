package com.RyanSBA.mvc.repository;

import com.RyanSBA.mvc.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    @Query("SELECT r FROM Recipe r")
    List<Recipe> findAll();

    @Query("SELECT r FROM Recipe r WHERE r.id = ?1")
    Optional<Recipe> findById(int id);

}
