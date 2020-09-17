package org.dantes.edmon.dto;

import lombok.Data;

@Data
public class BookPurchaseDTO {
    private String userEmail;
    private Integer bookID;
}
