package org.example.peliculasvue.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.peliculasvue.dto.MovieRequestDTO;
import org.example.peliculasvue.dto.MovieResponseDTO;
import org.example.peliculasvue.entity.Movie;

public class ValueMapper {
    public static Movie convertToEntity(MovieRequestDTO movieRequestDTO){
        Movie movie = new Movie();
        movie.setName(movieRequestDTO.getName());
        movie.setDirector(movieRequestDTO.getDirector());
        movie.setDuration(movieRequestDTO.getDuration());
        movie.setGender(movieRequestDTO.getGender());
        movie.setCategory(movieRequestDTO.getCategory());
        movie.setPublishDate(movieRequestDTO.getPublishDate());
        return movie;
    }

    public static MovieResponseDTO convertToDTO(Movie movie){
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        movieResponseDTO.setId(movie.getId());
        movieResponseDTO.setName(movie.getName());
        movieResponseDTO.setDirector(movie.getDirector());
        movieResponseDTO.setDuration(movie.getDuration());
        movieResponseDTO.setGender(movie.getGender());
        movieResponseDTO.setCategory(movie.getCategory());
        movieResponseDTO.setPublishDate(movie.getPublishDate());
        return movieResponseDTO;
    }

    public static String jsonAsString(Object o){
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}
