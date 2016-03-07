package com.dechcaudron.koel.api.objects;

import java.util.List;

public class Artist
{
    private final String name;
    private final List<Album> albumList;

    public Artist(String name, List<Album> albumList)
    {
        this.name = name;
        this.albumList = albumList;
    }

    public List<Album> getAlbumList()
    {
        return albumList;
    }

    public String getName()
    {
        return name;
    }
}
