package com.yuu.cruddemo.entity.repo;

import com.yuu.cruddemo.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
