package org.dantes.edmon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dantes.edmon.dto.BookPurchaseDTO;
import org.dantes.edmon.repository.BookPurchaseDtoRepository;
import org.dantes.edmon.service.BookPurchaseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookPurchaseDtoServiceImpl implements BookPurchaseDtoService {

    private BookPurchaseDtoRepository bookPurchaseDtoRepository;

    @Autowired
    public BookPurchaseDtoServiceImpl(BookPurchaseDtoRepository bookPurchaseDtoRepository){
        this.bookPurchaseDtoRepository = bookPurchaseDtoRepository;
    }

    @Override
    public BookPurchaseDTO savePurchase(BookPurchaseDTO bookPurchaseDTO) {
        return bookPurchaseDtoRepository.savePurchase(bookPurchaseDTO);
    }
}
