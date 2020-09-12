package org.dantes.edmon.repository;

import org.dantes.edmon.dto.managementDTO.ManagementBookDTO;

public interface ManagementBookDtoRepository {
    ManagementBookDTO save(ManagementBookDTO bookDTO);
}
