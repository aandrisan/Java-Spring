package ro.sd.a2.mapper;

import ro.sd.a2.dto.ActorDto;
import ro.sd.a2.entity.Actor;
import ro.sd.a2.utils.AplicationUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActorMapper {

    public static ActorDto actorToDTO(Actor actor){
        ActorDto actorDto = ActorDto.builder()
                .id(actor.getId())
                .name(actor.getName())
                .image(actor.getImage())
                .birthDate(AplicationUtils.generatePrettyDateFromSQLDate(actor.getBirthDate()))
                .build();
        return actorDto;
    }

    public static List<ActorDto> listActorsToDTO (List<Actor> actorList){
        List <ActorDto> list = new ArrayList<ActorDto>();
        for(Actor a : actorList){
            list.add(ActorMapper.actorToDTO(a));
        }
        return list;
    }

    public static Actor updateFielsOfActor(ActorDto actorDto, Actor actor){
        actor.setImage(actorDto.getImage());
        actor.setName(actorDto.getName());
        return actor;
    }

    public static Actor mappDtoToActor (ActorDto actorDto) throws ParseException {
        Actor actor = Actor.builder()
                .id(UUID.randomUUID().toString())
                .name(actorDto.getName())
                .image(actorDto.getImage())
                .birthDate(AplicationUtils.generateDateFromString(actorDto.getBirthDate()))
                .build();
        return actor;
    }
}
