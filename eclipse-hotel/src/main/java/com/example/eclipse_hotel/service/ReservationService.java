package com.example.eclipse_hotel.service;

import com.example.eclipse_hotel.model.Reservation;
import com.example.eclipse_hotel.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class ReservationService {
    
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        log.info("Fetching all reservations");
        return reservationRepository.findAll();
    }

    public Reservation findById(Long id) {
        log.info("Fetching reservation with id: {}", id);
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation save(Reservation reservation) {
        log.info("Saving reservation with id: {}", reservation.getId());
        return reservationRepository.save(reservation);
    }

    public void deleteById(Long id) {
        log.info("Deleting reservation with id: {}", id);
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Fetching reservations between {} and {}", startDate, endDate);
        return reservationRepository.findByCheckInBetween(startDate, endDate);
    }

    public List<Reservation> findCurrentReservations() {
        log.info("Fetching current reservations");
        return reservationRepository.findByStatus(Reservation.Status.IN_USE);
    }
}
