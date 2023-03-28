package com.bookingApi.bookingApi.api.Controllers;

import com.bookingApi.bookingApi.api.DTO.ExcursionDto;
import com.bookingApi.bookingApi.api.repositories.ExcursionRepository;
import com.bookingApi.bookingApi.api.services.ExcursionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rest/hotels/{hotel_name}/excursions")
public class ExcursionController {
    ExcursionService excursionService;
    private final ExcursionRepository excursionRepository;

    @GetMapping
    public ResponseEntity<List<ExcursionDto>> getExcursions(@PathVariable(name = "hotel_name") String hotelName){
        hotelName = hotelName.replace("_"," ");
        return excursionService.getExcursions(hotelName);
    }

    @PostMapping
    public HttpStatus addExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody ExcursionDto excursionDto){
        hotelName = hotelName.replace("_"," ");
        return excursionService.addExcursion(hotelName,excursionDto);
    }

    @GetMapping("{excursion_name}")
    public ResponseEntity<ExcursionDto> getExcursionByName(@PathVariable(name = "excursion_name") String excursionName){
        excursionName = excursionName.replace("_"," ");
        return excursionService.getExcursionByName(excursionName);
    }

    @PutMapping("{excursion_name}")
   public HttpStatus updateExcursion(@PathVariable(name = "excursion_name") String excursionName,
                                     @RequestBody ExcursionDto excursionDto){
       return excursionService.updateExcursion(excursionName,excursionDto);

    }

    @DeleteMapping("{excursion_name}")
    public HttpStatus deleteExcursionByName(@PathVariable(name = "excursion_name") String excursionName){
        return excursionService.deleteExcursionByName(excursionName);
    }


}