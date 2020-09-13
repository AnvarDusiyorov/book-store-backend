package org.dantes.edmon.controller.management;

import org.dantes.edmon.dto.managementDTO.ManagementBookDTO;
import org.dantes.edmon.dto.managementDTO.PileManagementBookDTO;
import org.dantes.edmon.service.ManagementBookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path="/management/book")
public class ManagementBookController {

    private ManagementBookDtoService managementBookDtoService;

    @Autowired
    public ManagementBookController(ManagementBookDtoService managementBookDtoService) {
        this.managementBookDtoService = managementBookDtoService;
    }

    @PostMapping
    public ResponseEntity<ManagementBookDTO> saveBook(@RequestBody ManagementBookDTO bookDTO, Principal principal){

        managementBookDtoService.save(bookDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/pile")
    public ResponseEntity<PileManagementBookDTO> saveListOfBooks(@RequestBody PileManagementBookDTO pileManagementBookDTO){

        for(ManagementBookDTO bookDTO : pileManagementBookDTO.getBooks()){
            managementBookDtoService.save(bookDTO);
        }
        return new ResponseEntity<>(pileManagementBookDTO, HttpStatus.OK);
    }
}
