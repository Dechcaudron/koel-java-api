package com.dechcaudron.koel.api.objects;

import java.util.List;

public class Album
{
    private final String name;
    private final List<Song> songList;
    private final String artistName;

    public Album(String artistName, String name, List<Song> songList)
    {
        this.name = name;
        this.songList = songList;
        this.artistName = artistName;
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
}
