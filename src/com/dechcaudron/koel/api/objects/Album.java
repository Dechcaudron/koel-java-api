package com.dechcaudron.koel.api.objects;

import java.util.List;

public class Album
{
    private final String name;
    private final List<Song> songList;
    private final String artistName;
    private final String imagePath;

    public Album(String artistName, String name, List<Song> songList, String imagePath)
    {
        this.name = name;
        this.songList = songList;
        this.artistName = artistName;
        this.imagePath = imagePath;
    }

    public List<Song> getSongList()
    {
        return songList;
    }

    public String getName()
    {
        return name;
    }

    public String getArtistName()
    {
        return artistName;
    }

    public String getImagePath()
    {
        return imagePath;
    }
}
