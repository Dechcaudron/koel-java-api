package objects;

import java.util.GregorianCalendar;

public class KoelAuthenticationToken
{
    private final String encodedToken;
    private final GregorianCalendar expirationDate;

    public KoelAuthenticationToken(String encodedToken, GregorianCalendar expirationDate)
    {
        this.encodedToken = encodedToken;
        this.expirationDate = expirationDate;
    }

    public GregorianCalendar getExpirationDate()
    {
        return expirationDate;
    }
}
