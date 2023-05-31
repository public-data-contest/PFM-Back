package com.pfm.project.controller;

import com.pfm.project.dto.ErrorResponseBody;
import com.pfm.project.dto.SuccessResponseBody;
import com.pfm.project.dto.place.request.PlaceRequest;
import com.pfm.project.dto.store.request.SearchStoreByUserPlaceRequest;
import com.pfm.project.dto.store.request.SearchStoreByWordReqeust;
import com.pfm.project.dto.store.response.StoreBriefInfo;
import com.pfm.project.dto.store.response.StoreBriefInfoResponse;
import com.pfm.project.service.SearchService;
import com.pfm.project.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/")
@Tag(name = "Home", description = "Home화면 관련 api")
public class HomeController {


    private final StoreService storeService;
    private final SearchService searchService;


    public HomeController(StoreService storeService, SearchService searchService) {
        this.storeService = storeService;
        this.searchService = searchService;
    }

    @Operation(summary = "FirstHomepage", description = "홈페이지 처음들어왔을때")
    @PostMapping("/home")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "홈페이지 7카드의 되돌려받는 값",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StoreBriefInfoResponse.class)))
            )
    })
    public ResponseEntity Cardcoordinates(@RequestBody PlaceRequest place) {

        try {
            List<StoreBriefInfo> places = storeService.coordinates(place.getLongitude(), place.getLatitude());
            StoreBriefInfoResponse filter;
            List<StoreBriefInfoResponse> responses = new ArrayList<>();

            for (int i = 0; i < places.size(); i++) {
                filter = new StoreBriefInfoResponse(places.get(i));
                responses.add(filter);
            }

            return ResponseEntity.ok().body(
                    SuccessResponseBody.<List<StoreBriefInfoResponse>>builder().status(HttpStatus.OK).message("Successfully found stores by words")
                            .data(responses).build());

        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ErrorResponseBody
                                .builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .code(HttpStatus.NOT_FOUND.name())
                                .message(e.getMessage() == null ? e.getMessage() : "Not Found" )
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


    @Operation(summary = "All", description = "7개 카드 옆에 더보기 클릭시")
    @PostMapping("/search/map")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "홈페이지에서 더보기 이동시 되돌려 받는 값",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StoreBriefInfoResponse.class)))
            )
    })
    public ResponseEntity AllSelect(@RequestBody SearchStoreByUserPlaceRequest userPlaceRequest) {

        try {

            List<StoreBriefInfo> stores =
                    storeService.allSelect(userPlaceRequest.getAddress(),
                            userPlaceRequest.getUserPlace().getLongitude(), userPlaceRequest.getUserPlace().getLatitude(),
                            userPlaceRequest.getPage());
            StoreBriefInfoResponse filter;
            List<StoreBriefInfoResponse> responses = new ArrayList<>();

            for (int i = 0; i < stores.size(); i++) {
                filter = new StoreBriefInfoResponse(stores.get(i));
                responses.add(filter);
            }

            return ResponseEntity.ok().body(
                    SuccessResponseBody.<List<StoreBriefInfoResponse>>builder().status(HttpStatus.OK).message("Successfully found stores by words")
                            .data(responses).build());

        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ErrorResponseBody
                                .builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .code(HttpStatus.NOT_FOUND.name())
                                .message(e.getMessage() == null ? e.getMessage() : "Not Found" )
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

    @Operation(summary = "HomeSearch", description = "홈페이지 검색창")
    @PostMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "홈페이지에서 검색창 사용시",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StoreBriefInfoResponse.class)))
            )
    })
    public ResponseEntity homeSearch(@RequestBody SearchStoreByWordReqeust search) {
        try {
            List<StoreBriefInfo> result = searchService.homeSearch(search.getStoreName(), search.getAddress(), search.getPage());
            List<StoreBriefInfoResponse> responses = new ArrayList<>();
            StoreBriefInfoResponse filter;

            for (int i = 0; i < result.size(); i++) {
                filter = new StoreBriefInfoResponse(result.get(i));
                responses.add(filter);
            }


            return ResponseEntity.ok().body(
                    SuccessResponseBody.<List<StoreBriefInfoResponse>>builder()
                            .status(HttpStatus.OK).message("Successfully found stores by words")
                            .data(responses).build());

        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ErrorResponseBody
                                .builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .code(HttpStatus.NOT_FOUND.name())
                                .message(e.getMessage() == null ? e.getMessage() : "Not Found" )
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
