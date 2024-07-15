package com.example.eclipse_hotel.model.dto;

import com.example.eclipse_hotel.model.Reservation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private Long id;
    private Long customerId;
    private Long roomId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Reservation.Status status;
}
