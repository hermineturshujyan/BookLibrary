package com.workfront.internship.booklibrary.dao;

import com.workfront.internship.booklibrary.common.Genre;
import java.util.List;

/**
 * Created by Sona on 6/30/2016.
 */
public interface GenreDAO {
    void createGenre(Genre genre);

    Genre getGenreByID(int id);

    List<Genre> getAllGenres();

    void updateGenre(Genre genre);

    void deleteGenre(int id);
}
