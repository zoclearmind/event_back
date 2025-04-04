package com.zocode.event.mapper;

import com.zocode.event.dto.UtilisateurDTO;
import com.zocode.event.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    Utilisateur toEntity(UtilisateurDTO dto);
    UtilisateurDTO toDTO(Utilisateur entity);
}

