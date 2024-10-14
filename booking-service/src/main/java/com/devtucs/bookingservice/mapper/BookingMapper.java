package com.devtucs.bookingservice.mapper;

import com.devtucs.bookingservice.dto.request.BookingRequest;
import com.devtucs.bookingservice.dto.response.BookingResponse;
import com.devtucs.bookingservice.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponse toBookingResponse(Booking post);

    Booking toBooking(BookingRequest request);

    void toUpdateBooking(@MappingTarget Booking post, BookingRequest request);
}
