package ro.sd.a2.mapper;

import ro.sd.a2.dto.DirectorDto;
import ro.sd.a2.entity.Director;
import ro.sd.a2.utils.AplicationUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DirectorMapping {

    public static DirectorDto directorToDTO(Director director){
        DirectorDto directorDto = DirectorDto.builder()
                .id(director.getId())
                .name(director.getName())
                .image(director.getImage())
                .birthDate(AplicationUtils.generatePrettyDateFromSQLDate(director.getBirthDate()))
                .build();
        return directorDto;
    }

    public static Director dtoToDirector(DirectorDto directorDto) throws ParseException {
        Director director = Director.builder()
                .id(UUID.randomUUID().toString())
                .name(directorDto.getName())
                .birthDate(AplicationUtils.generateDateFromString(directorDto.getBirthDate()))
                .image(directorDto.getImage())
                .build();
        return director;
    }

    public static List<DirectorDto> listDirectorToDTO (List<Director> directorList){
        List<DirectorDto> directorDtos = new ArrayList<DirectorDto>();
        for(Director d : directorList){
            directorDtos.add(DirectorMapping.directorToDTO(d));
        }
        return directorDtos;
    }

    public static Director updateFiledsOfDirector (DirectorDto directorDto, Director director){
        director.setImage(directorDto.getImage());
        director.setName(director.getName());
        return director;
    }

}
