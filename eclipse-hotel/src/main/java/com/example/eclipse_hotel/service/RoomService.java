package com.example.eclipse_hotel.service;

import com.example.eclipse_hotel.model.Room;
import com.example.eclipse_hotel.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class RoomService {

    private RoomRepository roomRepository;

    public List<Room> findAll() {
        log.info("Fetching all rooms");
        return roomRepository.findAll();
    }

    public Room findById(Long id) {
        log.info("Fetching room with id: {}", id);
        return roomRepository.findById(id).orElse(null);
    }

    public Room save(Room room) {
        log.info("Saving room with id: {}", room.getId());
        return roomRepository.save(room);
    }

    public void deleteById(Long id) {
        log.info("Deleting room with id: {}", id);
        roomRepository.deleteById(id);
    }
}
