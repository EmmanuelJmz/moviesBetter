package org.example.peliculasvue.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByNameContaining(String name);
    List<Movie> findByDirectorContaining(String director);
    List<Movie> findByStartDateBetween(LocalDate publishDate, LocalDate publishDate2);
    List<Movie> findByGenderOrCategory(String gender, String category);
    List<Movie> findByOrderByPublishDateDesc();
}
