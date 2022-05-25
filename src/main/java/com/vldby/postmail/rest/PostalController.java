package com.vldby.postmail.rest;

import com.vldby.postmail.dto.DeliveryRequestDto;
import com.vldby.postmail.dto.PostalDeliveryRegistrationDto;
import com.vldby.postmail.entity.PostalDelivery;
import com.vldby.postmail.entity.PostalDeliveryStatus;
import com.vldby.postmail.entity.PostalLog;
import com.vldby.postmail.entity.PostalOffice;
import com.vldby.postmail.exception.ResourceNotFoundException;
import com.vldby.postmail.mappers.PostalDeliveryMapper;
import com.vldby.postmail.repository.PostalDeliveryRepo;
import com.vldby.postmail.repository.PostalOfficeRepo;
import com.vldby.postmail.service.PostalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(RestConstants.Endpoints.POSTAL)
public class PostalController {

    private final Logger logger = LoggerFactory.getLogger(PostalController.class);

    private final PostalService postalService;
    private final PostalOfficeRepo postalOfficeRepo;
    private final PostalDeliveryRepo postalDeliveryRepo;

    public PostalController(PostalService postalService, PostalOfficeRepo postalOfficeRepo, PostalDeliveryRepo postalDeliveryRepo) {
        this.postalService = postalService;
        this.postalOfficeRepo = postalOfficeRepo;
        this.postalDeliveryRepo = postalDeliveryRepo;
    }

    @Operation(summary = "Registration postal delivery and save to log entity", tags = "registration")
    @Parameters(value = @Parameter(name = "body", description = "Postal delivery data"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Postal office not found by index {index}", content = {@Content})
    })
    @Transactional
    @PostMapping(value = RestConstants.Endpoints.REGISTRATION,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostalLog> postalDeliveryRegistration(@RequestBody PostalDeliveryRegistrationDto requestDto,
                                                                HttpServletRequest request) {
        logger.info(RestConstants.REQUEST_LOG_FORMAT, request.getMethod(), request.getRequestURI());
        PostalDeliveryMapper postalDeliveryMapper = PostalDeliveryMapper.INSTANCE;
        PostalDelivery postalDelivery = postalDeliveryRepo.save(postalDeliveryMapper.toEntity(requestDto));
        PostalOffice postalOffice = findPostalOfficeByIndex(requestDto.getIndexOffice());
        PostalLog postalLog = postalService.postageRegister(postalDelivery, postalOffice);
        return ResponseEntity.ok(postalLog);
    }

    @Operation(summary = "Postal delivery arrival to post office record", tags = "arrived")
    @Parameters(value = @Parameter(name = "body", description = "Postal delivery identifier and postal office target"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Postal office not found by index {index}", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Postal delivery not found by identifier {index}", content = {@Content})
    })
    @PostMapping(value = RestConstants.Endpoints.ARRIVED,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostalLog> recordArrivalToPostOffice(@RequestBody DeliveryRequestDto requestDto,
                                                               HttpServletRequest request) {
        logger.info(RestConstants.REQUEST_LOG_FORMAT, request.getMethod(), request.getRequestURI());
        PostalOffice postalOffice = findPostalOfficeByIndex(requestDto.getPostalOfficeIndex());
        PostalDelivery postalDelivery = findPostalDeliveryByIdentifier(requestDto.getPostalDeliveryIdentifier());
        PostalLog postalLog = postalService.savePostalLog(postalDelivery, postalOffice, PostalDeliveryStatus.ARRIVED);
        return ResponseEntity.ok(postalLog);
    }

    @Operation(summary = "Postal delivery sent record", tags = "sent")
    @Parameters(value = @Parameter(name = "body", description = "Postal delivery identifier and postal office target"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Postal office not found by index {index}", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Postal delivery not found by identifier {index}", content = {@Content})
    })
    @PostMapping(value = RestConstants.Endpoints.SENT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostalLog> recordSentFromPostOffice(@RequestBody DeliveryRequestDto requestDto,
                                                              HttpServletRequest request) {
        logger.info(RestConstants.REQUEST_LOG_FORMAT, request.getMethod(), request.getRequestURI());
        PostalOffice postalOffice = findPostalOfficeByIndex(requestDto.getPostalOfficeIndex());
        PostalDelivery postalDelivery = findPostalDeliveryByIdentifier(requestDto.getPostalDeliveryIdentifier());
        PostalLog postalLog = postalService.savePostalLog(postalDelivery, postalOffice, PostalDeliveryStatus.SENT);
        return ResponseEntity.ok(postalLog);
    }

    @Operation(summary = "Postal delivery accepted record", tags = "accepted")
    @Parameters(value = @Parameter(name = "body", description = "Postal delivery identifier and postal office target"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Postal office not found by index {index}", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Postal delivery not found by identifier {index}", content = {@Content})
    })
    @PostMapping(value = RestConstants.Endpoints.ACCEPTED,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostalLog> recordAcceptedPostalDelivery(@RequestBody DeliveryRequestDto requestDto,
                                                              HttpServletRequest request) {
        logger.info(RestConstants.REQUEST_LOG_FORMAT, request.getMethod(), request.getRequestURI());
        PostalOffice postalOffice = findPostalOfficeByIndex(requestDto.getPostalOfficeIndex());
        PostalDelivery postalDelivery = findPostalDeliveryByIdentifier(requestDto.getPostalDeliveryIdentifier());
        PostalLog postalLog = postalService.savePostalLog(postalDelivery, postalOffice, PostalDeliveryStatus.ACCEPTED);
        return ResponseEntity.ok(postalLog);
    }

    @Operation(summary = "Get movement history of postal delivery", tags = "history")
    @Parameters(value = @Parameter(name = "identifier", description = "Postal delivery identifier"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Postal delivery not found by identifier {index}", content = {@Content})
    })
    @GetMapping(params = {"identifier"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostalLog>> getMovementHistoryPostalDelivery(@RequestParam("identifier") String identifier,
                                                                            HttpServletRequest request) {
        logger.info(RestConstants.REQUEST_LOG_FORMAT, request.getMethod(), request.getRequestURI());
        PostalDelivery postalDelivery = findPostalDeliveryByIdentifier(identifier);
        List<PostalLog> movementHistory = postalService.getMovementHistoryPostalDelivery(postalDelivery);
        return ResponseEntity.ok(movementHistory);
    }

    private PostalDelivery findPostalDeliveryByIdentifier(String identified) {
        PostalDelivery postalDelivery = postalDeliveryRepo.findByIdentifier(identified);
        if (postalDelivery == null)
            throw new ResourceNotFoundException("Postal delivery not found by identifier '" + identified + "'");

        return postalDelivery;
    }

    private PostalOffice findPostalOfficeByIndex(String index) {
        PostalOffice postalOffice = postalOfficeRepo.findByIndex(index);
        if (postalOffice == null)
            throw new ResourceNotFoundException("Postal office not found by index '" + index + "'");

        return postalOffice;
    }

}