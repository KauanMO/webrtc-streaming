package entity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<Room> {
    public Optional<Room> findByKey(String key) {
        return find("roomKey", key).stream().findAny();
    }
}
