package com.inter;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
