package com.bookingApi.bookingApi.api.services;


import com.bookingApi.bookingApi.api.DTO.RoomDto;
import com.bookingApi.bookingApi.api.Exceptions.ResponseNotFoundException;
import com.bookingApi.bookingApi.api.mappers.RoomMapper;
import com.bookingApi.bookingApi.api.models.HotelEntity;
import com.bookingApi.bookingApi.api.models.RoomEntity;
import com.bookingApi.bookingApi.api.mappers.RoomListMapper;
import com.bookingApi.bookingApi.api.repositories.HotelRepository;
import com.bookingApi.bookingApi.api.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import java.util.List;



@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    private final RoomMapper roomMapper;

    private RoomListMapper roomListMapper;

    public HttpStatus update(String hotelName, RoomDto roomDto) {
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.
                    findByhotelIdAndNumber(opt_hotel.get().getId(), roomDto.getNumber());
            if(opt_room.isPresent()){
                RoomEntity roomDb = opt_room.get();
                if(Objects.nonNull(roomDto.getDescription()) && !"".equals(roomDto.getDescription())){
                    roomDb.setDescription(roomDto.getDescription());
                }
                if(Objects.nonNull(roomDto.getType()) && !"".equals(roomDto.getType())){
                    roomDb.setType(roomDto.getType());
                }
                if(Objects.nonNull(roomDto.getPrice())){
                    roomDb.setPrice(roomDto.getPrice());
                }
                roomDb.setFreeTag(roomDto.getFreeTag());
                roomRepository.save(roomDb);
                return HttpStatus.OK;
            }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");

        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");
    }
    public ResponseEntity<List<RoomDto>> getRooms(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()) {

            return new ResponseEntity<List<RoomDto>>(roomListMapper.toDtoList(opt_hotel.get().getRooms()),HttpStatus.OK);

        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");

    }

    public ResponseEntity<List<RoomDto>> getAllFreeRooms(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return new ResponseEntity<List<RoomDto>>(roomListMapper.toDtoList(roomRepository.
                    findByhotelIdAndFreeTagTrue(opt_hotel.get().getId())),HttpStatus.OK);
        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");

    }

    public ResponseEntity<List<RoomDto>> getAllOccupiedRooms(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return new ResponseEntity<List<RoomDto>>(roomListMapper.toDtoList(roomRepository.
                    findByhotelIdAndFreeTagFalse(opt_hotel.get().getId())),HttpStatus.OK);
        }else throw new ResponseNotFoundException("hotel '" + hotelName + " not found.");
    }

    public HttpStatus setRoomFreeTag(String hotelName,int roomNumber, boolean freeTag){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.
                    findByhotelIdAndNumber(opt_hotel.get().getId(),roomNumber);
            if(opt_room.isPresent()){
                roomRepository.save(opt_room.get().setFreeTag(freeTag));
                return HttpStatus.OK;
            } else throw new ResponseNotFoundException("room number '" + roomNumber + "' not found.");

        }else throw new ResponseNotFoundException("room number '" + roomNumber + "' not found.");
    }
    public HttpStatus setRoomPrice(String hotelName,int roomNumber, double price){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.
                    findByhotelIdAndNumber(opt_hotel.get().getId(),roomNumber);
            if(opt_room.isPresent()){
                roomRepository.save(opt_room.get().setPrice(price));
                return HttpStatus.OK;

            }else throw new ResponseNotFoundException("room number '" + roomNumber + "' not found.");

        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");
    }


    public HttpStatus addRoom(String hotelName, RoomDto roomDto){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        RoomEntity room = roomMapper.toEntity(roomDto);
        if(opt_hotel.isPresent()){
            room.setHotelId(opt_hotel.get().getId());
            roomRepository.save(room);
            return HttpStatus.CREATED;

        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found");
    }

    public HttpStatus deleteRoom(String hotelName, int number){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.findByhotelIdAndNumber(opt_hotel.get().getId(), number);
            if(opt_room.isPresent()){
                roomRepository.delete(opt_room.get());
                return HttpStatus.OK;
            }else{
                throw new ResponseNotFoundException("room '" + number + "' not found.");
            }
        }else{
            throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");
        }

    }


    public ResponseEntity<RoomDto> getRoom(String hotelName, int number){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.
                    findByhotelIdAndNumber(opt_hotel.get().getId(), number);

            if(opt_room.isPresent()){
                return new ResponseEntity<RoomDto>(roomMapper.toDto(opt_room.get()),HttpStatus.OK);

            }else throw new ResponseNotFoundException("room '" + number + "' not found.");

        }throw new ResponseNotFoundException("hotel '" + hotelName + "' not found");
    }


}
