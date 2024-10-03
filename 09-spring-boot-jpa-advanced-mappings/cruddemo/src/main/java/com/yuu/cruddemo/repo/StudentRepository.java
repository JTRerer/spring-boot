package com.yuu.cruddemo.repo;

import com.yuu.cruddemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
