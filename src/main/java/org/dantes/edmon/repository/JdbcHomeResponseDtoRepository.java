package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BestsellerBookDTO;
import org.dantes.edmon.dto.RatingDTO;
import org.dantes.edmon.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcHomeResponseDtoRepository implements HomeResponseDtoRepository {

    private JdbcTemplate jdbc;
    private final Integer LIMIT_NUMBER = 15;

    @Autowired
    public JdbcHomeResponseDtoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public List<BestsellerBookDTO> getAllBestsellerBooks() {
        List<Integer> topBestsellersIDlist = getTopBestsellersIDlist();

        List<BestsellerBookDTO> bestsellerBookDTOS = new ArrayList<>();

        for(Integer bookID : topBestsellersIDlist){
            String title = getTitleByBookID(bookID);
            List<Author> authors = getAuthorsByBookID(bookID);
            RatingDTO ratingInfo = getRatingInfoByBookID(bookID);

            BestsellerBookDTO bookDTO = new BestsellerBookDTO();
            bookDTO.setTitle(title);
            bookDTO.setRatingInfo(ratingInfo);
            bookDTO.setAuthors(authors);

            bestsellerBookDTOS.add(bookDTO);
        }

        return bestsellerBookDTOS;
    }

    @Override
    public List<String> getAllGenres() {
        String sqlQuery = "select genre_name from genre";

        List<String> genres = jdbc.queryForList(sqlQuery, String.class);

        return genres;
    }

    private List<Integer> getTopBestsellersIDlist(){
        String sqlQuery = "SELECT book_id FROM purchase_book GROUP BY book_id ORDER BY count(book_id) DESC LIMIT ?";

        return jdbc.queryForList(sqlQuery, Integer.class, LIMIT_NUMBER);
    }

    private List<Author> getAuthorsByBookID(Integer bookID){
        String sqlQuery = "SELECT author.creative_pseudonym as creative_pseudonym, book_id FROM " +
                "author INNER JOIN book_author ON author.creative_pseudonym = book_author.creative_pseudonym " +
                "WHERE book_id = ?";

        List<Author> authors = jdbc.query(
                sqlQuery, (ResultSet rs, int rownum) -> {
                    Author author = new Author();
                    author.setCreativePseudonym(rs.getString("creative_pseudonym"));
                    return author;
                },
                bookID);

        return authors;
    }

    private String getTitleByBookID(Integer bookID){
        String sqlQuery = "select title from book where book_id = ?";

        return jdbc.queryForObject(sqlQuery, String.class, bookID);
    }

    private RatingDTO getRatingInfoByBookID(Integer bookID){
        String sqlQuery = "SELECT SUM(rating)/count(rating) AS real_rating FROM rating_book WHERE book_id = ? GROUP BY book_id";

        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setHasRating("true");

        try {
            Double rating = jdbc.queryForObject(sqlQuery, Double.class, bookID);
            ratingDTO.setRating(rating);
        }catch (Exception e){
            ratingDTO.setHasRating("false");
        }

        return ratingDTO;
    }
}
