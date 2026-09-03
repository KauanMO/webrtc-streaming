package entity;

import entity.dto.CreateRoomRequestDTO;
import entity.dto.RoomDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.security.SecureRandom;
import java.util.Base64;

@Path("/api/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    @Inject
    private RoomService service;

    @ConfigProperty(name = "app.media.base-url", defaultValue = "http://localhost:8889")
    String mediaBaseUrl;

    @POST
    @Transactional
    // TODO: plugar @RolesAllowed / SmallRye JWT aqui pra só usuário autenticado criar sala
    public RoomDTO create(CreateRoomRequestDTO request) {
        Room newRoom = service.createRoom(request);

        return RoomDTO.from(newRoom, mediaBaseUrl);
    }

    @GET
    @Path("/{key}")
    public RoomDTO get(@PathParam("key") String key) {
        Room roomFound = service.getRoomByKey(key);

        return RoomDTO.from(roomFound, mediaBaseUrl);
    }

    @DELETE
    @Path("/{key}")
    @Transactional
    public void close(@PathParam("key") String key) {
        service.deleteRoom(key);
    }
}
