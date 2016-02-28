package com.dechcaudron.koel.api.exceptions;

public class KoelApiException extends Exception
{
    private final String message;

    public KoelApiException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
