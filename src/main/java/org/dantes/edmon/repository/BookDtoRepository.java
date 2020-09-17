package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookDTO;

import java.util.List;

public interface BookDtoRepository {
    BookDTO findBookByBookID(Integer bookID);
    Integer getRatingGivenByUser(String userEmail);
    List<Integer> getAllBookIdByGenre(String genreName);
}
