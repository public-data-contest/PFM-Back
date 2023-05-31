package com.pfm.project.controller;

import com.pfm.project.dto.ErrorResponseBody;
import com.pfm.project.dto.SuccessResponseBody;
import com.pfm.project.dto.store.response.StoreDetailResponse;
import com.pfm.project.service.StoreDetailService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
public class StoreDetailController {

    final StoreDetailService storeDetailService;

    @Autowired
    public StoreDetailController(StoreDetailService storeDetailService) {
        this.storeDetailService = storeDetailService;
    }


    @GetMapping("/stores/{storeId}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "업소 정보",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StoreDetailResponse.class))
            ),
    })
    public ResponseEntity findStoreDetail(@PathVariable Long storeId) {
        try {
            StoreDetailResponse storeDetailResponse = storeDetailService.findStoreDetail(storeId);

            ResponseEntity response =  ResponseEntity.ok().body(
                    SuccessResponseBody
                            .<StoreDetailResponse>builder()
                            .status(HttpStatus.OK)
                            .message("Successfully found stores by user place")
                            .data(storeDetailResponse)
                            .build()
            );

            return response;

        } catch (Exception e) {
            if (e instanceof NotFoundException) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ErrorResponseBody
                                .builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .code(HttpStatus.NOT_FOUND.name())
                                .message(e.getMessage() == null ? e.getMessage() : "Not Found")
                                .error(e.getMessage())
                                .build()
                );
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ErrorResponseBody
                            .builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .code(HttpStatus.BAD_REQUEST.name())
                            .message(e.getMessage() == null ? e.getMessage() : "Bad request" )
                            .error(e.getMessage())
                            .build()
            );
        }


    }
}
