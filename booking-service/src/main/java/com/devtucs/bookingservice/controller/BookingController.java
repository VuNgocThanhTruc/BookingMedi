package com.devtucs.bookingservice.controller;

import com.devtucs.bookingservice.service.BookingService;
import com.devtucs.bookingservice.dto.request.BookingRequest;
import com.devtucs.bookingservice.dto.response.ApiResponse;
import com.devtucs.bookingservice.dto.response.BookingResponse;
import com.devtucs.bookingservice.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @GetMapping
    public ApiResponse<List<BookingResponse>> getBookings(){
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookings())
                .build();
    }

    @GetMapping("{idBooking}")
    public ApiResponse<BookingResponse> getBooking(@PathVariable String idBooking){
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getBooking(idBooking))
                .build();
    }

    @GetMapping("/my-booking")
    public ApiResponse<List<BookingResponse>> getBookingsByUserId(){
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookingsByUserId())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<BookingResponse> create(@RequestBody BookingRequest request){
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.create(request))
                .build();
    }

    @PutMapping("/{idBooking}")
    public ApiResponse<BookingResponse> update(@PathVariable String idBooking, @RequestBody BookingRequest request){

        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.update(idBooking, request))
                .build();
    }

    @DeleteMapping("/{idBooking}")
    public ApiResponse<String> delete(@PathVariable String idBooking){
        bookingService.delete(idBooking);
        return ApiResponse.<String>builder()
                .result("Delete successfully!")
                .build();
    }
}
