package com.dechcaudron.koel.api.utils;

import com.dechcaudron.koel.api.exceptions.HttpException;
import com.dechcaudron.koel.api.exceptions.KoelApiException;
import com.dechcaudron.koel.api.exceptions.KoelAuthenticationException;
import com.dechcaudron.koel.api.http.HttpManager;
import com.dechcaudron.koel.api.objects.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class MediaUtils
{
    public static MediaInfo getMediaInfo(URL koelServerURL, KoelAuthenticationToken authenticationToken) throws KoelApiException, IOException
    {
        URI endpoint;

        try
        {
            endpoint = new URI(koelServerURL.toString() + "/api/data");
        } catch (URISyntaxException e)
        {
            throw new RuntimeException("Could not construct URI from URL");
        }

        String response = "";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + authenticationToken.getEncodedToken());

        try
        {
            response = HttpManager.executeGET(endpoint, headers);
        } catch (HttpException e)
        {
            if (e.getResponseCode() == 400)
                throw new KoelAuthenticationException("Invalid token supplied");
        }

        return parseMediaResponse(response);
    }

    private static MediaInfo parseMediaResponse(String response) throws KoelApiException
    {
        try
        {
            JSONObject json = new JSONObject(response);
            JSONArray artistsArray = json.getJSONArray("artists");

            return new MediaInfo(parseArtistsJSON(artistsArray));


        } catch (Exception e)
        {
            throw new KoelApiException(e.getMessage());
        }
    }

    private static List<Artist> parseArtistsJSON(JSONArray artistsArray)
    {
        List<Artist> artists = new ArrayList<>();

        for (int i = 0; i < artistsArray.length(); ++i)
        {
            JSONObject artistJson = artistsArray.getJSONObject(i);
            JSONArray albumsJson = artistJson.getJSONArray("albums");

            String artistName = artistJson.getString("name");
            artists.add(new Artist(artistJson.getString("name"), parseAlbumsJSON(artistName, albumsJson), artistJson.getString("image")));
        }

        return artists;
    }

    private static List<Album> parseAlbumsJSON(String artistName, JSONArray albumsArray)
    {
        List<Album> albums = new ArrayList<>();

        for (int i = 0; i < albumsArray.length(); ++i)
        {
            JSONObject albumJson = albumsArray.getJSONObject(i);
            JSONArray songsArray = albumJson.getJSONArray("songs");

            String albumName = albumJson.getString("name");
            albums.add(new Album(artistName, albumName, parseSongsJSON(artistName, albumName, songsArray), albumJson.getString("cover")));
        }

        return albums;
    }

    private static List<Song> parseSongsJSON(String artistName, String albumName, JSONArray songsArray)
    {
        List<Song> songs = new ArrayList<>();

        for (int i = 0; i < songsArray.length(); ++i)
        {
            JSONObject songJson = songsArray.getJSONObject(i);

            songs.add(new Song(artistName, albumName, songJson.getString("title"), songJson.getString("id")));
        }

        return songs;
    }
}
