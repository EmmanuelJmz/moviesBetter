package org.example.peliculasvue.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.peliculasvue.dto.ApiResponse;
import org.example.peliculasvue.dto.MovieRequestDTO;
import org.example.peliculasvue.dto.MovieResponseDTO;
import org.example.peliculasvue.services.MovieService;
import org.example.peliculasvue.util.ValueMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
@Slf4j
public class MovieController {

    private MovieService movieService;

    @PostMapping
    public ResponseEntity<ApiResponse> createNewMovie(@RequestBody @Valid MovieRequestDTO movieRequestDTO){
        log.info("MovieController::CreateNewMovie request body {}", ValueMapper.jsonAsString(movieRequestDTO));

        MovieResponseDTO movieResponseDTO = movieService.createNewMovie(movieRequestDTO);

        ApiResponse<MovieResponseDTO> responseDTO = ApiResponse
                .<MovieResponseDTO>builder()
                .status("Success")
                .results(movieResponseDTO)
                .build();

        log.info("MovieController::CreateNewMovie response {}", ValueMapper.jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getMovies(){
        List<MovieResponseDTO> movies = movieService.getMovies();

        ApiResponse<List<MovieResponseDTO>> responseDTO = ApiResponse
                .<List<MovieResponseDTO>>builder()
                .status("Success")
                .results(movies)
                .build();

        log.info("MovieController::getMovies response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMovieById(@PathVariable Long id) throws Exception {
        MovieResponseDTO movie = movieService.getMovieById(id);

        ApiResponse<MovieResponseDTO> responseDTO = ApiResponse
                .<MovieResponseDTO>builder()
                .status("Success")
                .results(movie)
                .build();

        log.info("MovieController::getMovieById response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Metodo que retorna las peliculas por nombre que contenga el parametro
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchMoviesByName(@RequestParam String name) {
        List<MovieResponseDTO> movies = movieService.getMoviesByNameContaining(name);

        ApiResponse<List<MovieResponseDTO>> responseDTO = ApiResponse
                .<List<MovieResponseDTO>>builder()
                .status("Success")
                .results(movies)
                .build();

        log.info("MovieController::searchMoviesByName response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMovie(@PathVariable Long id, @RequestBody @Valid MovieRequestDTO movieRequestDTO) throws Exception {
        MovieResponseDTO movie = movieService.updateMovie(id, movieRequestDTO);

        ApiResponse<MovieResponseDTO> responseDTO = ApiResponse
                .<MovieResponseDTO>builder()
                .status("Success")
                .results(movie)
                .build();

        log.info("MovieController::updateMovie response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long id) throws Exception {
        movieService.deleteMovie(id);

        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("Success")
                .results("Movie deleted successfully")
                .build();

        log.info("MovieController::deleteMovie response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
