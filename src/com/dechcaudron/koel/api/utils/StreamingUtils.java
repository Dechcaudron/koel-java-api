package com.dechcaudron.koel.api.utils;

import com.dechcaudron.koel.api.exceptions.KoelApiException;
import com.dechcaudron.koel.api.objects.KoelAuthenticationToken;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class StreamingUtils
{
    public static URL getStreamingURL(URL serverURL, KoelAuthenticationToken authenticationToken, String songId) throws KoelApiException
    {
        String stringURL = serverURL.toString() + "/api/" + songId + "/play?token=" + authenticationToken.getEncodedToken();
        try
        {
            return new URL(stringURL);
        } catch (MalformedURLException e)
        {
            throw new KoelApiException("Could not create URL for " + stringURL);
        }
    }
}
