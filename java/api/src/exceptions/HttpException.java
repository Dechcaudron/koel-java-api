package exceptions;


public class HttpException extends Exception
{
    private final int responseCode;

    public HttpException(int responseCode)
    {
        this.responseCode = responseCode;
    }

    public int getResponseCode()
    {
        return responseCode;
    }
}
