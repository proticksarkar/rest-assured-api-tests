package com.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistRequestPayload {

    @JsonProperty("playlist_request_payload")
    private List<Playlist> playlistRequestPayload;

}
