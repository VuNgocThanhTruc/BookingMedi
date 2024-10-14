package com.devtucs.bookingservice.repository;

import com.devtucs.bookingservice.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findAllByUserId(String userId);
}
