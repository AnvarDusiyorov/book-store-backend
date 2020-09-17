package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookPurchaseDTO;

public interface BookPurchaseDtoRepository {
    BookPurchaseDTO savePurchase(BookPurchaseDTO bookPurchaseDTO);
}
