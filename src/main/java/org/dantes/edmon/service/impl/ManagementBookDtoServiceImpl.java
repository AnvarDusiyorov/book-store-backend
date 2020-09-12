package org.dantes.edmon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dantes.edmon.dto.managementDTO.ManagementBookDTO;
import org.dantes.edmon.repository.ManagementBookDtoRepository;
import org.dantes.edmon.service.ManagementBookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManagementBookDtoServiceImpl implements ManagementBookDtoService {

    private final ManagementBookDtoRepository managementBookDtoRepository;

    @Autowired
    public ManagementBookDtoServiceImpl(ManagementBookDtoRepository managementBookDtoRepository) {
        this.managementBookDtoRepository = managementBookDtoRepository;
    }

    @Override
    public ManagementBookDTO save(ManagementBookDTO bookDTO) {
        return managementBookDtoRepository.save(bookDTO);
    }
}
