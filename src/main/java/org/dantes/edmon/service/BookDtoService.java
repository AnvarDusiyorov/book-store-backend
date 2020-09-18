package org.dantes.edmon.service;

import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.dto.PileOfGenres;
import org.dantes.edmon.dto.ShortViewBookDTO;
import org.dantes.edmon.dto.search.PileBookDTO;
import org.dantes.edmon.dto.search.SearchRequestDTO;

import java.util.List;

public interface BookDtoService {
    BookDTO findBookByBookID(Integer bookID);
    Integer getRatingGivenByUser(String userEmail);
    List<Integer> getAllBookIdByGenre(String genreName);
    ShortViewBookDTO findShortViewBookByBookID(Integer bookID);
    PileOfGenres getAllGenres();
    List<ShortViewBookDTO> getAllShortViewBooksByListOfBookID(List<Integer> bookIdList);
    PileBookDTO getAllShortViewBooksBySearchRequestDTO(SearchRequestDTO searchRequestDTO);
}
