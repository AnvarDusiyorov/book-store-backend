package org.dantes.edmon.controller;

import org.dantes.edmon.dto.HomeResponseDTO;
import org.dantes.edmon.service.HomeResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/home")
public class HomeController {
    private HomeResponseDtoService homeResponseDtoService;

    @Autowired
    public HomeController(HomeResponseDtoService homeResponseDtoService){
        this.homeResponseDtoService = homeResponseDtoService;
    }

    @GetMapping
    public ResponseEntity<HomeResponseDTO> getHomePage(){
        return new ResponseEntity<>(homeResponseDtoService.getHomeResponseDTO(), HttpStatus.OK);
    }
}
