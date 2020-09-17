package org.dantes.edmon.controller;

import org.dantes.edmon.dto.BookPurchaseDTO;
import org.dantes.edmon.dto.CommonResponseDTO;
import org.dantes.edmon.service.BookPurchaseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path="/buybook")
public class BuyBookController {

    private BookPurchaseDtoService bookPurchaseDtoService;

    @Autowired
    public BuyBookController(BookPurchaseDtoService bookPurchaseDtoService){
        this.bookPurchaseDtoService = bookPurchaseDtoService;
    }

    @PostMapping
    public ResponseEntity<CommonResponseDTO> savePurchase(
            @RequestBody BookPurchaseDTO bookPurchaseDTO, Principal principal){

        String userEmail = principal.getName();
        bookPurchaseDTO.setUserEmail(userEmail);

        bookPurchaseDtoService.savePurchase(bookPurchaseDTO);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setCreated(true);

        return new ResponseEntity<>(commonResponseDTO, HttpStatus.CREATED);
    }
}
