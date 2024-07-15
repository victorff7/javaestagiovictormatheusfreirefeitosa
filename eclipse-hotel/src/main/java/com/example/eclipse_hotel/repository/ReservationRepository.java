package com.example.eclipse_hotel.repository;

import com.example.eclipse_hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCheckInBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Reservation> findByStatus(Reservation.Status status);
}
