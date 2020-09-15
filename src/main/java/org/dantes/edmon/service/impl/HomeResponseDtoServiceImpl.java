package org.dantes.edmon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dantes.edmon.dto.HomeResponseDTO;
import org.dantes.edmon.repository.HomeResponseDtoRepository;
import org.dantes.edmon.service.HomeResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HomeResponseDtoServiceImpl implements HomeResponseDtoService {
    private HomeResponseDtoRepository homeResponseDtoRepository;

    @Autowired
    public HomeResponseDtoServiceImpl(HomeResponseDtoRepository homeResponseDtoRepository){
        this.homeResponseDtoRepository = homeResponseDtoRepository;
    }

    @Override
    public HomeResponseDTO getHomeResponseDTO() {
        HomeResponseDTO homeResponseDTO = new HomeResponseDTO();
        homeResponseDTO.setBestsellers(homeResponseDtoRepository.getAllBestsellerBooks());
        homeResponseDTO.setGenres(homeResponseDtoRepository.getAllGenres());

        return homeResponseDTO;
    }
}
