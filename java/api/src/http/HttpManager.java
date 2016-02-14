package http;

import exceptions.HttpException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HttpManager {

    public static String executePOST(URI endPoint, HashMap<String, String> headers, HashMap<String, String> formParams) throws IOException, HttpException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(endPoint);

        if (headers != null)
            setHeaders(httpPost, headers);

        if (formParams != null)
            setFormParams(httpPost, formParams);

        try {
            return consumeResponse(httpClient.execute(httpPost));
        } finally {
            httpClient.close();
        }
    }

    public static String executeGET(URI endPoint, HashMap<String, String> headers) throws IOException, HttpException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(endPoint);

        if (headers != null)
            setHeaders(httpGet, headers);

        try {
            return consumeResponse(httpClient.execute(httpGet));
        } finally {
            httpClient.close();
        }
    }

    private static void setHeaders(HttpRequestBase requestBase, HashMap<String, String> headersMap) {
        ArrayList<Header> headers = new ArrayList<>(headersMap.size());

        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
        }

        requestBase.setHeaders((Header[]) headers.toArray());
    }

    private static void setFormParams(HttpEntityEnclosingRequestBase requestBase, HashMap<String, String> paramsMap) {
        List<NameValuePair> nameValuePairs = new ArrayList<>(paramsMap.size());

        for (Map.Entry<String, String> param : paramsMap.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }

        try {
            requestBase.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        } catch (UnsupportedEncodingException e) {
            //think of something
        }
    }

    private static void checkStatusCode(int statusCode) throws HttpException {
        if (statusCode < 200 || statusCode >= 300)
            throw new HttpException(statusCode);
    }

    private static String consumeResponse(CloseableHttpResponse httpResponse) throws IOException, HttpException {
        try {
            checkStatusCode(httpResponse.getStatusLine().getStatusCode());

            HttpEntity entity = httpResponse.getEntity();

            return EntityUtils.toString(entity);

        } finally {
            httpResponse.close();
        }
    }
}
