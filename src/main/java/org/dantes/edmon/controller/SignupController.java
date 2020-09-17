package org.dantes.edmon.controller;

import org.dantes.edmon.dto.CommonResponseDTO;
import org.dantes.edmon.model.User;
import org.dantes.edmon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/signup")
public class SignupController {

    private UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<CommonResponseDTO> saveUser(@RequestBody User user){
        User userInDatabase = userService.findByEmail(user.getEmail());

        CommonResponseDTO responseDTO = new CommonResponseDTO();
        HttpStatus httpStatus = (userInDatabase == null) ? HttpStatus.CREATED : HttpStatus.CONFLICT;

        if(userInDatabase == null){
            userService.save(user);
            responseDTO.setCreated(true);
        }else {
            responseDTO.setCreated(false);
            responseDTO.setReason("such user already exist");
        }

        return new ResponseEntity<>(responseDTO, httpStatus);
    }


}
