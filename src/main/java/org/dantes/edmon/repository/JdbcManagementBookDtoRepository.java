package org.dantes.edmon.repository;

import org.dantes.edmon.dto.managementDTO.ManagementBookDTO;
import org.dantes.edmon.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

@Repository
public class JdbcManagementBookDtoRepository implements ManagementBookDtoRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcManagementBookDtoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    //tag::save[]
    @Override
    public ManagementBookDTO save(ManagementBookDTO bookDTO) {
        String bookTitle = bookDTO.getTitle();
        List<String> genres = bookDTO.getGenres();
        List<Author> authors = bookDTO.getAuthors();
        String imageLink = bookDTO.getImageLink();
        Double price = bookDTO.getPrice();

        if(!isBookDtoAlreadyExist(bookDTO)){
            processGenres(genres);
            processAuthors(authors);
            processBook(bookTitle, price, imageLink);
            addRelations(bookDTO);
        }

        return bookDTO;
    }
    //end::save[]

    //tag::isBookDtoAlreadyExist[]
    private boolean isBookDtoAlreadyExist(ManagementBookDTO bookDTO){
        String bookTitle = bookDTO.getTitle();
        List<String> genres = bookDTO.getGenres();
        List<Author> authors = bookDTO.getAuthors();

        int amountOfPossibleNamesakes = getAmountOfPossibleNamesakes(bookTitle);

        if(amountOfPossibleNamesakes == 0)
            return false;

        List<Integer> namesakesIdList = getNamesakesIdList(bookTitle);
        boolean isGenresAreSame = checkIfGenresSame(namesakesIdList, genres);
        boolean isAuthorsAreSame = checkIfAuthorsSame(namesakesIdList, authors);

        return isAuthorsAreSame && isGenresAreSame;
    }

    private boolean checkIfAuthorsSame(List<Integer> namesakesIdList, List<Author> maybeNewBookAuthors ){

        maybeNewBookAuthors.sort(Comparator.comparing(Author::getCreativePseudonym));

        for(Integer namesakesBookID : namesakesIdList){
            List<Author> namesakesAuthors = getAuthorsByBookIDSortedByCreativePseudonym(namesakesBookID);
            if(maybeNewBookAuthors.equals(namesakesAuthors)){
                return true;
            }
        }

        return false;
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

    private boolean checkIfGenresSame(List<Integer> namesakesIdList, List<String> maybeNewBookGenres){
        maybeNewBookGenres.sort(Comparator.naturalOrder());

        for(Integer namesakesBookID : namesakesIdList){
            List<String> namesakesGenres = getGenresByBookIDSortedByGenreName(namesakesBookID);
             if(maybeNewBookGenres.equals(namesakesGenres)){
                return true;
            }
        }

        return false;
    }

    private List<String> getGenresByBookIDSortedByGenreName(Integer bookID){
        String sqlQuery = "select genre_name from book_genre where book_id = ? order by genre_name";

        List<String> genres = jdbc.queryForList(sqlQuery, String.class, bookID);

        return genres;
    }

    private List<Integer> getNamesakesIdList(String bookTitle){
        String sqlQuery = "select book_id from book where title = ?";

        List<Integer> namesakesIdList = jdbc.queryForList(sqlQuery, Integer.class, bookTitle);

        return namesakesIdList;
    }

    private int getAmountOfPossibleNamesakes(String bookTitle){
        String sqlQuery = "select count(book_id) from book where title = ?";

        return jdbc.queryForObject(sqlQuery, Integer.class, bookTitle);
    }
    // end::isBookDtoAlreadyExist[]

    private void addRelations(ManagementBookDTO bookDTO){
        String bookTitle = bookDTO.getTitle();
        List<String> genres = bookDTO.getGenres();
        List<Author> authors = bookDTO.getAuthors();
        String imageLink = bookDTO.getImageLink();
        Double price = bookDTO.getPrice();

        Integer bookID = getLastBookID(bookTitle, price, imageLink);

        for(String genre : genres){
            try {
                addRelationBetweenBookAndGenre(bookID, genre);
            }catch (Exception e){}
        }

        for(Author author : authors){
            try{
                addRelationBetweenBookAndAuthor(bookID, author);
            }catch (Exception e){}
        }
    }

    // function for getting last bookID 'cause there can be many books with same title,
    // but with different genres and authors
    private Integer getLastBookID(String bookTitle, Double price, String imageLink){
        String sqlQuery = "select max(book_id) from book where title = ? and price = ? and image_link = ?";

        return jdbc.queryForObject(sqlQuery, Integer.class, bookTitle, price, imageLink);
    }

    private void addRelationBetweenBookAndGenre(Integer bookID, String genreName){
        String sqlQuery = "insert into book_genre(book_id, genre_name) values(?, ?)";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.INTEGER, Types.VARCHAR
        );


        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(bookID, genreName)
        );

        jdbc.update(psc);
    }

    private void addRelationBetweenBookAndAuthor(Integer bookID, Author author){
        String sqlQuery = "insert into book_author(book_id, creative_pseudonym) values(?, ?)";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.INTEGER, Types.VARCHAR
        );


        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(bookID, author.getCreativePseudonym())
        );

        jdbc.update(psc);
    }


    private void processBook(String bookTitle, Double price, String imageLink){
        try {
            saveBook(bookTitle, price, imageLink);
        }catch (Exception e){}
    }

    private void saveBook(String bookTitle, Double price, String imageLink){
        String sqlQuery = "insert into book(title, price, image_link) values(?, ?, nullif(?, '') )";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.VARCHAR, Types.DOUBLE, Types.VARCHAR
        );

        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(bookTitle, price, imageLink)
        );

        jdbc.update(psc);
    }

    private void processGenres(List<String> genres){
        for(String genre : genres){
            try {
                saveGenre(genre);
            }catch (Exception e){ }
        }
    }

    private void saveGenre(String genre){
        String sqlQuery = "insert into genre(genre_name) values (?)";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.VARCHAR
        );

        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(genre)
        );

        jdbc.update(psc);
    }

    private void processAuthors(List<Author> authors){
        for(Author author : authors){
            try {
                saveAuthor(author);
            }catch (Exception e){}
        }
    }

    private void saveAuthor(Author author){
        String creativePseudonym = author.getCreativePseudonym();

        String sqlQuery = "insert into author(creative_pseudonym) values(?)";

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sqlQuery, Types.VARCHAR
        );

        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                Arrays.asList(creativePseudonym)
        );

        jdbc.update(psc);
    }

}
