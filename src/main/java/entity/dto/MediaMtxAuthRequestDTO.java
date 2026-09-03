package entity.dto;

public record MediaMtxAuthRequestDTO(
        String ip,
        String user,
        String password,
        String path,
        String protocol,
        String id,
        String action,
        String query
) {
}
