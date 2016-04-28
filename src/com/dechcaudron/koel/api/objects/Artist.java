package com.dechcaudron.koel.api.objects;

import java.util.List;

public class Artist
{
    private final String name;
    private final List<Album> albumList;
    private final String imagePath;

    public Artist(String name, List<Album> albumList, String imagePath)
    {
        this.name = name;
        this.albumList = albumList;
        this.imagePath = imagePath;
    }

    public List<Album> getAlbumList()
    {
        return albumList;
    }

    public String getName()
    {
        return name;
    }

    public String getImagePath()
    {
        return imagePath;
    }
}
