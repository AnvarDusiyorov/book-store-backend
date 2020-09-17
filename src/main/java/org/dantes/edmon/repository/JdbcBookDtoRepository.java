package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.dto.RealRatingOfBookDTO;
import org.dantes.edmon.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
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

            bookDTO.setRatingInfo(getRatingInfoByBookID(bookID));
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

    private RealRatingOfBookDTO getRatingInfoByBookID(Integer bookID){
        String sqlQuery = "SELECT SUM(rating)/count(rating) AS real_rating FROM rating_book WHERE book_id = ? GROUP BY book_id";

        RealRatingOfBookDTO realRatingOfBookDTO = new RealRatingOfBookDTO();
        realRatingOfBookDTO.setHasRating("true");

        try {
            Double rating = jdbc.queryForObject(sqlQuery, Double.class, bookID);
            realRatingOfBookDTO.setRating(rating);
        }catch (Exception e){
            realRatingOfBookDTO.setHasRating("false");
        }

        return realRatingOfBookDTO;
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
        // TODO
        return null;
    }
}
