package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.dto.RealRatingOfBookDTO;
import org.dantes.edmon.dto.ShortViewBookDTO;
import org.dantes.edmon.dto.search.SearchRequestDTO;
import org.dantes.edmon.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcBookDtoRepository implements BookDtoRepository {

    private JdbcTemplate jdbc;
    private ReviewRepository reviewRepository;

    @Autowired
    public JdbcBookDtoRepository(JdbcTemplate jdbc, ReviewRepository reviewRepository){
        this.jdbc = jdbc;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public BookDTO findBookByBookID(Integer bookID) {
        String sqlQuery = "select * from book where book_id = ?";

        return jdbc.queryForObject(sqlQuery, (ResultSet rs, int rownum) -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBookID(rs.getInt("book_id"));
            bookDTO.setTitle(rs.getString("title"));
            bookDTO.setImageLink(rs.getString("image_link"));
            bookDTO.setBookDescription(rs.getString("book_description"));
            bookDTO.setPrice(rs.getDouble("price"));

            bookDTO.setRating(getRatingByBookID(bookID));
            bookDTO.setGenres(getGenresByBookIDSortedByGenreName(bookID));
            bookDTO.setAuthors(getAuthorsByBookIDSortedByCreativePseudonym(bookID));

            bookDTO.setReviews(reviewRepository.getAllReviewByBookID(bookID));

            return bookDTO;
        }, bookID);
    }

    @Override
    public Integer getRatingGivenByUser(String userEmail) {
        String sqlQuery = "select rating from rating_book where user_email = ?";

        return jdbc.queryForObject(sqlQuery, Integer.class, userEmail);
    }

    private Double getRatingByBookID(Integer bookID){
        String sqlQuery = "SELECT SUM(rating)/count(rating) AS real_rating " +
                "FROM rating_book RIGHT JOIN book " +
                "ON rating_book.book_id = book.book_id " +
                "WHERE book.book_id = ? GROUP BY book.book_id";

        return jdbc.queryForObject(sqlQuery, Double.class, bookID);
    }

    private List<String> getGenresByBookIDSortedByGenreName(Integer bookID){
        String sqlQuery = "select genre_name from book_genre where book_id = ? order by genre_name";

        List<String> genres = jdbc.queryForList(sqlQuery, String.class, bookID);

        return genres;
    }

    private List<Author> getAuthorsByBookIDSortedByCreativePseudonym(Integer bookID){
        String sqlQuery = "select creative_pseudonym from book_author where book_id = ? order by creative_pseudonym";

        List<Author> authors = jdbc.query(
                sqlQuery, (ResultSet rs, int rownum) -> {
                    Author author = new Author();
                    author.setCreativePseudonym(rs.getString("creative_pseudonym"));
                    return author;
                },
                bookID);

        return authors;
    }

    @Override
    public List<Integer> getAllBookIdByGenre(String genreName) {
        String sqlQuery = "select book_id from book_genre where genre_name = ?";

        return jdbc.queryForList(sqlQuery, Integer.class, genreName);
    }

    @Override
    public ShortViewBookDTO findShortViewBookByBookID(Integer bookID) {
        String sqlQuery = "select * from book where book_id = ?";

        return jdbc.queryForObject(sqlQuery, (ResultSet rs, int rownum) -> {
            ShortViewBookDTO shortViewBookDTO = new ShortViewBookDTO();

            shortViewBookDTO.setBookID(rs.getInt("book_id"));
            shortViewBookDTO.setTitle(rs.getString("title"));
            shortViewBookDTO.setImageLink(rs.getString("image_link"));
            shortViewBookDTO.setBookDescription(rs.getString("book_description"));
            shortViewBookDTO.setPrice(rs.getDouble("price"));

            shortViewBookDTO.setRating(getRatingByBookID(bookID));
            shortViewBookDTO.setGenres(getGenresByBookIDSortedByGenreName(bookID));
            shortViewBookDTO.setAuthors(getAuthorsByBookIDSortedByCreativePseudonym(bookID));

            return shortViewBookDTO;
        }, bookID);
    }

    @Override
    public List<String> getAllGenres() {
        String sqlQuery = "select genre_name from genre";

        return jdbc.queryForList(sqlQuery, String.class);
    }

    @Override
    public List<ShortViewBookDTO> getAllBooksBySearchRequestDTO(SearchRequestDTO searchRequestDTO) {
        // TODO
        return null;
    }
}
