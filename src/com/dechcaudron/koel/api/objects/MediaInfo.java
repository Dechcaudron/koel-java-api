package com.dechcaudron.koel.api.objects;

import java.util.ArrayList;
import java.util.List;

public class MediaInfo
{
    private final List<Artist> artists;

    public MediaInfo(List<Artist> artists)
    {
        this.artists = artists;
    }

    public List<Artist> getArtists()
    {
        return artists;
    }

    public List<Album> getAllAlbums()
    {
        List<Album> albums = new ArrayList<>();

        for (Artist artist : artists)
        {
            albums.addAll(artist.getAlbumList());
        }

        return albums;
    }

    public List<Song> getAllSongs()
    {
        List<Song> songs = new ArrayList<>();

        for (Album album : getAllAlbums())
        {
            songs.addAll(album.getSongList());
        }

        return songs;
    }
}
