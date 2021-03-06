package com.dechcaudron.koel.api.utils;

import com.dechcaudron.koel.api.exceptions.HttpException;
import com.dechcaudron.koel.api.exceptions.KoelApiException;
import com.dechcaudron.koel.api.exceptions.KoelAuthenticationException;
import com.dechcaudron.koel.api.http.HttpManager;
import com.dechcaudron.koel.api.objects.KoelAuthenticationToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public abstract class AuthenticationUtils
{

    public static KoelAuthenticationToken login(URL koelServerUrl, String email, String password) throws KoelApiException, IOException
    {
        URI endpoint;
        try
        {
            endpoint = new URI(koelServerUrl.toString() + "/api/me");

        } catch (URISyntaxException e)
        {
            //We tried to construct a URI from a URL, so we should be totally fine
            throw new RuntimeException("Could not construct endpoint URI");
        }

        HashMap<String, String> emailAndPass = new HashMap<>(2);
        emailAndPass.put("email", email);
        emailAndPass.put("password", password);

        String koelResponse = "";

        try
        {
            koelResponse = HttpManager.executePOST(endpoint, null, emailAndPass);
            JSONObject jsonResponse = new JSONObject(koelResponse);

            return new KoelAuthenticationToken(jsonResponse.getString("token"));

        } catch (JSONException e)
        {
            throw new KoelApiException("The Koel instance did not answer as expected: " + koelResponse);

        } catch (HttpException e)
        {
            if (e.getResponseCode() == 401)
                throw new KoelAuthenticationException("The given credentials (email = " + email + ", password = " + password + ") were not accepted by the Koel instance");
            else
                throw new KoelApiException("The Koel instance answered with HTTP code " + e.getResponseCode());
        }
    }
}
