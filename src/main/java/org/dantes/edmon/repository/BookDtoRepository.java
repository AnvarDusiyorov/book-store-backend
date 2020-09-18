package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.dto.ShortViewBookDTO;
import org.dantes.edmon.dto.search.SearchRequestDTO;

import java.util.List;

public interface BookDtoRepository {
    BookDTO findBookByBookID(Integer bookID);
    Integer getRatingGivenByUser(String userEmail);
    List<Integer> getAllBookIdByGenre(String genreName);
    ShortViewBookDTO findShortViewBookByBookID(Integer bookID);
    List<String> getAllGenres();
    List<ShortViewBookDTO> getAllBooksBySearchRequestDTO(SearchRequestDTO searchRequestDTO);
}
