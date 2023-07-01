package com.spotify.payloadbuilders;

import com.spotify.pojo.Playlist;

public class PlaylistBuilder {

    public static Playlist buildRequestPlaylist(String name, String description, boolean _public) {
        return Playlist
                .builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

}
