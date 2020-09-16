package org.dantes.edmon.repository;

import org.dantes.edmon.dto.EvaluateReviewDTO;
import org.dantes.edmon.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcReviewRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public ReviewDTO findReviewByBookIdAndUserEmail(Integer bookID, String userEmail) {

        String sqlQuery = "select book_id, user_email from review " +
                "where book_id = ? and user_email = ?";

        return jdbc.queryForObject(sqlQuery, (ResultSet rs, int rownum) -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setUserEmail(rs.getString("user_email"));
            reviewDTO.setBookID(rs.getInt("book_id"));
            return reviewDTO;
        }, bookID, userEmail);
    }

    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        reviewDTO.setCreatedAt(new Date());

        String sqlQuery = "insert into review(book_id, user_email, description, createdAt) values(?, ?, ?, ?)";
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);

        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(
                        reviewDTO.getBookID(),
                        reviewDTO.getUserEmail(),
                        reviewDTO.getDescription(),
                        reviewDTO.getCreatedAt()
                )
        );

        jdbc.update(psc);
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getAllReviewByBookID(Integer bookID) {
        String sqlQuery = "select * from review where book_id = ?";

        List<ReviewDTO> reviews = jdbc.query(sqlQuery, (ResultSet rs, int rownum) -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setReviewID(rs.getInt("review_id"));
            reviewDTO.setBookID(rs.getInt("book_id"));
            reviewDTO.setUserEmail(rs.getString("user_email"));
            reviewDTO.setDescription(rs.getString("description"));
            reviewDTO.setCreatedAt(rs.getDate("createdAt"));
            reviewDTO.setAmountOfPluses(getAmountOfPlusesByReviewID(reviewDTO.getReviewID()));
            reviewDTO.setAmountOfMinuses(getAmountOfMinusesByReviewID(reviewDTO.getAmountOfMinuses()));

            return reviewDTO;
        }, bookID);
        return reviews;
    }



    private Integer getAmountOfPlusesByReviewID(Integer reviewID){
        String sqlQuery = "SELECT count(review_id) FROM evaluate_review " +
                "WHERE evaluation = 1 and review_id = ?";

        return jdbc.queryForObject(sqlQuery, Integer.class, reviewID);
    }

    private Integer getAmountOfMinusesByReviewID(Integer reviewID){
        String sqlQuery = "SELECT count(review_id) FROM evaluate_review " +
                "WHERE evaluation = -1 and review_id = ?";

        return jdbc.queryForObject(sqlQuery, Integer.class, reviewID);
    }

    @Override
    public EvaluateReviewDTO findEvaluateReviewByReviewIDandUserEmail(Integer reviewID, String userEmail) {
        String sqlQuery = "select * from evaluate_review where review_id = ? and user_email = ?";

        return jdbc.queryForObject(sqlQuery, (ResultSet rs, int rownum) -> {
            EvaluateReviewDTO evaluateReviewDTO = new EvaluateReviewDTO();
            evaluateReviewDTO.setReviewID(rs.getInt("review_id"));
            evaluateReviewDTO.setUserEmail(rs.getString("user_email"));

            return evaluateReviewDTO;
        }, reviewID, userEmail);
    }

    @Override
    public EvaluateReviewDTO saveEvaluateReview(EvaluateReviewDTO evaluateReviewDTO) {
        String sqlQuery = "INSERT INTO evaluate_review(review_id, user_email, evaluation) values(?, ?, ?)";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.INTEGER, Types.VARCHAR, Types.INTEGER);

        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(
                        evaluateReviewDTO.getReviewID(),
                        evaluateReviewDTO.getUserEmail(),
                        evaluateReviewDTO.getEvaluation()
                )
        );

        jdbc.update(psc);
        return evaluateReviewDTO;
    }

}
