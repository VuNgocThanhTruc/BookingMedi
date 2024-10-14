package com.devtucs.bookingservice.service;

import com.devtucs.bookingservice.mapper.BookingMapper;
import com.devtucs.bookingservice.repository.BookingRepository;
import com.devtucs.bookingservice.dto.request.BookingRequest;
import com.devtucs.bookingservice.dto.response.BookingResponse;
import com.devtucs.bookingservice.entity.Booking;
import com.devtucs.bookingservice.exception.AppException;
import com.devtucs.bookingservice.exception.ErrorCodeConstant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;

    public BookingResponse getBooking(String request) {
        Booking booking = bookingRepository.findById(request)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.BOOKING_NOT_FOUND));

        return bookingMapper.toBookingResponse(booking);
    }

    public BookingResponse create(BookingRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Booking booking = bookingMapper.toBooking(request);
        booking.setUserId(authentication.getName());
        booking.setCreatedAt(Instant.now());
        booking.setCreatedBy(authentication.getName());
        booking.setModifiedBy(authentication.getName());
        booking.setModifiedAt(Instant.now());

        booking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
    }

    public BookingResponse update(String id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.BOOKING_NOT_FOUND));
        bookingMapper.toUpdateBooking(booking, request);

        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public void delete(String request) {
        Booking booking = bookingRepository.findById(request)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.BOOKING_NOT_FOUND));
        bookingRepository.delete(booking);
    }

    public List<BookingResponse> getBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingResponse).toList();
    }

    public List<BookingResponse> getBookingsByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return bookingRepository.findAllByUserId(authentication.getName())
                .stream()
                .map(bookingMapper::toBookingResponse)
                .toList();
    }
}
