package entity;

import entity.dto.CreateRoomRequestDTO;
import entity.dto.MediaMtxAuthRequestDTO;
import entity.dto.RoomDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.security.SecureRandom;
import java.util.Base64;

@ApplicationScoped
public class RoomService {
    private static final SecureRandom RANDOM = new SecureRandom();

    @Inject
    private RoomRepository repository;

    public Room createRoom(CreateRoomRequestDTO request) {
        Room room = new Room();
        room.setName(request.name());
        room.setRoomKey(generateKey());
        room.persist();

        return room;
    }

    public Room getRoomByKey(String key) {
        Room roomFound = repository.findByKey(key).orElseThrow(NotFoundException::new);

        if (!roomFound.isActive()) {
            throw new NotFoundException();
        }

        return roomFound;
    }

    public Boolean isAuthorized(MediaMtxAuthRequestDTO req) {
        Room room = this.getRoomByKey(req.path());

        return room.isActive();
    }

    public void deleteRoom(String key) {
        Room room = this.getRoomByKey(key);

        if (room != null) {
            room.setActive(false);
        }
    }

    private String generateKey() {
        byte[] bytes = new byte[9];
        RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
