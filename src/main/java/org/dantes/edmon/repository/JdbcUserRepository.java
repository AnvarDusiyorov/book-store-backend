package org.dantes.edmon.repository;

import org.dantes.edmon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public User findByEmail(String email) {
        String sqlQuery = "select email, first_name, second_name, " +
                "phone_number from users where email = ?";

        return jdbc.queryForObject(sqlQuery, this::mapRowToIngredient, email);
    }

    private User mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        User toret = new User();
        toret.setFirstName(rs.getString("first_name"));
        toret.setSecondName(rs.getString("second_name"));
        toret.setEmail(rs.getString("email"));
        toret.setPhoneNumber(rs.getString("phone_number"));

        return toret;
    }

    @Override
    public User save(User user) {
        saveIntoUsersTable(user);

        saveIntoAuthoritiesTable(user);

        return user;
    }

    private void saveIntoUsersTable(User user){
        String sqlQuery = "insert into Users (email, password, first_name, second_name, phone_number) values (?, ?, nullif(?, ''), nullif(?, ''), nullif(?, '') )";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR
        );

        PreparedStatementCreator psc =
                factory.newPreparedStatementCreator(
                        Arrays.asList(
                                user.getEmail(), user.getPassword(),  user.getFirstName(),
                                user.getSecondName(), user.getPhoneNumber()
                        )
                );

        jdbc.update(psc);
    }

    private void saveIntoAuthoritiesTable(User user){
        String sqlQuery = "insert into Authorities (user_email, authority) values (?, 'ROLE_USER')";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.VARCHAR);

        PreparedStatementCreator psc =
                factory.newPreparedStatementCreator(Arrays.asList(user.getEmail()));

        jdbc.update(psc);
    }
}
