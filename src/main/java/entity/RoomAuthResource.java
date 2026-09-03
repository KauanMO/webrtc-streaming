package entity;

import entity.dto.MediaMtxAuthRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/rooms/auth")
@Consumes(MediaType.APPLICATION_JSON)
public class RoomAuthResource {
    @Inject
    public RoomService service;

    @POST
    public Response authorize(MediaMtxAuthRequestDTO req) {
        return service.isAuthorized(req)
                ? Response.ok().build()
                : Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
