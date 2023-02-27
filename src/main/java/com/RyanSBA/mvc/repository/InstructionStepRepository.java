package com.RyanSBA.mvc.repository;

import com.RyanSBA.mvc.model.InstructionStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionStepRepository extends JpaRepository<InstructionStep, Integer> {
}
