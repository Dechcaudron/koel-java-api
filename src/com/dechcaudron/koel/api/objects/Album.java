package com.dechcaudron.koel.api.objects;

import java.util.List;

public class Album
{
    private final String name;
    private final List<Song> songList;

    public Album(String name, List<Song> songList)
    {
        this.name = name;
        this.songList = songList;
    }

    public List<Song> getSongList()
    {
        return songList;
    }

    public String getName()
    {
        return name;
    }
}
