package com.bookingApi.bokkingApi.Controllers;

import com.bookingApi.bokkingApi.DTO.ExcursionDto;
import com.bookingApi.bokkingApi.services.ExcursionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/hotels/{hotel_name}/excursions")
public class ExcursionController {
    ExcursionService excursionService;

    @GetMapping
    public ResponseEntity<List<ExcursionDto>> getExcursions(@PathVariable(name = "hotel_name") String hotelName){
        return excursionService.getExcursions(hotelName);
    }

    @PostMapping
    public HttpStatus addExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody ExcursionDto excursionDto){
        return excursionService.addExcursion(hotelName,excursionDto);
    }


}