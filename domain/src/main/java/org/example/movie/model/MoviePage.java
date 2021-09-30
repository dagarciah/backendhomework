package org.example.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MoviePage {
    private final int pageNumber;
    private final int pageSize;
    private final long total;
    private final List<Movie> items;
}
