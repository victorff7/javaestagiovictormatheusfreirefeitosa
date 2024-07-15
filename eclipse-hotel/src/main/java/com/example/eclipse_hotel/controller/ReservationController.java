package com.example.eclipse_hotel.controller;

import com.example.eclipse_hotel.model.Customer;
import com.example.eclipse_hotel.model.Reservation;
import com.example.eclipse_hotel.model.Room;
import com.example.eclipse_hotel.model.dto.ReservationDTO;
import com.example.eclipse_hotel.service.CustomerService;
import com.example.eclipse_hotel.service.ReservationService;
import com.example.eclipse_hotel.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final RoomService roomService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Customer customer = customerService.findById(reservationDTO.getCustomerId());
        Room room = roomService.findById(reservationDTO.getRoomId());

        if (customer == null || room == null) {
            return ResponseEntity.badRequest().build();
        }

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setCheckIn(reservationDTO.getCheckIn());
        reservation.setCheckOut(reservationDTO.getCheckOut());
        reservation.setStatus(reservationDTO.getStatus());

        Reservation savedReservation = reservationService.save(reservation);

        ReservationDTO responseDTO = new ReservationDTO();
        responseDTO.setId(savedReservation.getId());
        responseDTO.setCustomerId(savedReservation.getCustomer().getId());
        responseDTO.setRoomId(savedReservation.getRoom().getId());
        responseDTO.setCheckIn(savedReservation.getCheckIn());
        responseDTO.setCheckOut(savedReservation.getCheckOut());
        responseDTO.setStatus(savedReservation.getStatus());

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return ResponseEntity.ok(reservationService.save(reservation));
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
    }

    @GetMapping("/dateRange")
    public List<Reservation> getReservationsByDateRange(@RequestParam LocalDateTime startDate,
                                                        @RequestParam LocalDateTime endDate) {
        return reservationService.findByDateRange(startDate, endDate);
    }

    @GetMapping("/current")
    public List<Reservation> getCurrentReservations() {
        return reservationService.findCurrentReservations();
    }
}
