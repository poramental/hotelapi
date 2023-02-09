package com.bookingApi.bokkingApi.Controllers;


import com.bookingApi.bokkingApi.DTO.AdditionalServiceDto;
import com.bookingApi.bokkingApi.models.AdditionalServiceEntity;
import com.bookingApi.bokkingApi.services.ServiceForAdditionalServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdditionalServiceController {

    private final ServiceForAdditionalServices additionalServ;


    @PostMapping("/additional_services")
    public HttpStatus createService(@RequestBody AdditionalServiceDto serviceDto){
        return additionalServ.createService(serviceDto);
    }

    @GetMapping("/hotels/{hotel_name}/additional_services")
    public ResponseEntity<List<AdditionalServiceDto>> getServices(@PathVariable(name="hotel_name") String hotelName){
        hotelName = hotelName.replace("_"," ");
        return additionalServ.getServices(hotelName);
    }

    @PostMapping("/hotels/{hotel_name}/additional_services/{service_name}")
    public HttpStatus addServiceToHotel(@PathVariable(name = "hotel_name") String hotelName,
                                        @PathVariable(name = "service_name") String serviceName){
        hotelName = hotelName.replace("_"," ");
        serviceName = serviceName.replace("_"," ");
        return additionalServ.addServiceToHotel(hotelName, serviceName);
    }

    @GetMapping("/additional_services")
    public ResponseEntity<List<AdditionalServiceDto>>getServices(){
        return additionalServ.getServices();
    }

    @DeleteMapping("/additional_services/{service_name}")
    public HttpStatus deleteService(@PathVariable(name = "service_name") String serviceName){
        serviceName = serviceName.replace("_"," ");
        return additionalServ.deleteService(serviceName);
    }

    @DeleteMapping("/hotels/{hotel_name}/additional_services/{service_name}")
    public HttpStatus deleteServiceFromHotel(@PathVariable(name = "hotel_name") String hotelName,
                                             @PathVariable(name = "service_name") String serviceName){
        hotelName = hotelName.replace("_"," ");
        serviceName = serviceName.replace("_"," ");
        return additionalServ.deleteServiceFromHotel(hotelName,serviceName);
    }

    @PutMapping("/additional_services/{service_name}")
    public HttpStatus updateService(@PathVariable(name = "service_name") String serviceName,
                                    @RequestBody AdditionalServiceDto additionalServiceDto) {
        serviceName = serviceName.replace("_", " ");
        return additionalServ.updateService(serviceName, additionalServiceDto);
    }



}
