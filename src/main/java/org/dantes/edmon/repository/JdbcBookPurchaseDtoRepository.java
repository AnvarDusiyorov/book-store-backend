package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookPurchaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;

@Repository
public class JdbcBookPurchaseDtoRepository implements BookPurchaseDtoRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcBookPurchaseDtoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public BookPurchaseDTO savePurchase(BookPurchaseDTO bookPurchaseDTO) {
        String sqlQuery = "insert into purchase_book(book_id, user_email) values(?, ?)";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
          sqlQuery, Types.INTEGER, Types.VARCHAR
        );

        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(
                       bookPurchaseDTO.getBookID(),
                       bookPurchaseDTO.getUserEmail()
                )
        );

        jdbc.update(psc);

        return bookPurchaseDTO;
    }
}
