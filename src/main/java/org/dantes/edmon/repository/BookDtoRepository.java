package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookDTO;

public interface BookDtoRepository {
    BookDTO findBookByBookID(Integer bookID);
    Integer getRatingGivenByUser(String userEmail);
}
