package com.dechcaudron.koel.api.objects;

public class Song
{
    private final String title;
    private final String id;

    public Song(String title, String id)
    {
        this.title = title;
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getId()
    {
        return id;
    }
}
