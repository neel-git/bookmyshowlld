package com.example.bookmyshow.repositories;

import com.example.bookmyshow.models.Movie;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<Movie> findByName(String name);
}
