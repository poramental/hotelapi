package com.bookingApi.bokkingApi.Controllers;

import com.bookingApi.bokkingApi.DTO.ExcursionDto;
import com.bookingApi.bokkingApi.models.ExcursionEntity;
import com.bookingApi.bokkingApi.services.ExcursionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/hotels/{hotel_name}/excursions")
public class ExcursionController {

    ExcursionService excursionService;

    @GetMapping
    public List<ExcursionDto> getExcursions(@PathVariable(name = "hotel_name") String hotelName){
        return excursionService.getExcursions(hotelName);
    }

    @PostMapping
    public HttpStatus addExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody ExcursionDto excursionDto){
        return excursionService.addExcursion(hotelName,excursionDto);
    }


}