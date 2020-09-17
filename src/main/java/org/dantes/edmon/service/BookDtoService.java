package org.dantes.edmon.service;

import org.dantes.edmon.dto.BookDTO;

public interface BookDtoService {
    BookDTO findBookByBookID(Integer bookID);
    Integer getRatingGivenByUser(String userEmail);
}
