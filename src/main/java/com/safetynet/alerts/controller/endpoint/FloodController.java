package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.FloodEndPointService;
import com.safetynet.alerts.dto.FloodDto;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Flood controller.
 */
@RestController
@RequestMapping("/flood/stations")
@Slf4j
public class FloodController {

    private final FloodEndPointService floodEndPointService;

    /**
     * Instantiates a new Flood controller.
     *
     * @param floodEndPointService the flood end point service
     */
    public FloodController(FloodEndPointService floodEndPointService) {
        this.floodEndPointService = floodEndPointService;
    }

    /**
     * Gets end point.
     *
     * @param stations the stations
     * @return the end point
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FloodDto>> getEndPoint(@RequestParam String stations) {
        return new ResponseEntity<>(floodEndPointService.getPersonListByStationNumberList(stations), HttpStatus.OK);
    }
}
