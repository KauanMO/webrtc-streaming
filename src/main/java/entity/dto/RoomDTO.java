package entity.dto;

import entity.Room;

import java.util.UUID;

public record RoomDTO(UUID id, String name, String roomKey, String whipUrl, String whepUrl) {
    public static RoomDTO from(Room room, String mediaBaseUrl) {
        return new RoomDTO(
                room.getId(),
                room.getName(),
                room.getRoomKey(),
                mediaBaseUrl + "/" + room.getRoomKey() + "/whip",
                mediaBaseUrl + "/" + room.getRoomKey() + "/whep"
        );
    }
}
