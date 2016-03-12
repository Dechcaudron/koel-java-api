package com.dechcaudron.koel.api.objects;

public class Song
{
    private final String title;
    private final String id;
    private final String artistName;
    private final String albumName;

    public Song(String artistName, String albumName, String title, String id)
    {
        this.title = title;
        this.id = id;
        this.artistName = artistName;
        this.albumName = albumName;
    }

    public String getTitle()
    {
        return title;
    }

    public String getId()
    {
        return id;
    }

    public String getArtistName()
    {
        return artistName;
    }

    public String getAlbumName()
    {
        return albumName;
    }
}
