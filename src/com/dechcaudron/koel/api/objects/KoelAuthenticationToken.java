package com.dechcaudron.koel.api.objects;

import com.dechcaudron.koel.api.exceptions.KoelApiException;

import java.util.GregorianCalendar;

public class KoelAuthenticationToken
{
    //TODO: get expiration date, validate encoded tokens
    private final String encodedToken;

    public KoelAuthenticationToken(String encodedToken) throws KoelApiException
    {
        this.encodedToken = encodedToken;
    }

    public GregorianCalendar getExpirationDate()
    {
        return new GregorianCalendar();
    }
}
