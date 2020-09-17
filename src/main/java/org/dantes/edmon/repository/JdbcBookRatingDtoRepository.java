package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookRatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.Arrays;

@Repository
public class JdbcBookRatingDtoRepository implements BookRatingDtoRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcBookRatingDtoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }


    @Override
    public BookRatingDTO findBookRatingByBookIdAndUserEmail(Integer bookID, String userEmail) {
        String sqlQuery = "select * from rating_book where book_id = ? and user_email = ?";

        return jdbc.queryForObject(sqlQuery, (ResultSet rs, int rownum) -> {
            BookRatingDTO bookRatingDTO = new BookRatingDTO();
            bookRatingDTO.setBookID(rs.getInt("book_id"));
            bookRatingDTO.setUserEmail(rs.getString("user_email"));
            bookRatingDTO.setRating(rs.getInt("rating"));
            return bookRatingDTO;
        }, bookID, userEmail);
    }

    @Override
    public BookRatingDTO saveBookRating(BookRatingDTO bookRatingDTO) {
        String sqlQuery = "insert into rating_book(book_id, user_email, rating) values(?, ?, ?)";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.INTEGER, Types.VARCHAR, Types.INTEGER
                );


        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(
                        bookRatingDTO.getBookID(),
                        bookRatingDTO.getUserEmail(),
                        bookRatingDTO.getRating()
                )
        );

        jdbc.update(psc);
        return bookRatingDTO;
    }
}
