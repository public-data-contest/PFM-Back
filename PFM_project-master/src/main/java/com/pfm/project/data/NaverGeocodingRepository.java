package com.pfm.project.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Repository;

import java.net.URLEncoder;

@Repository
public class NaverGeocodingRepository {
    private HttpClient httpclient = HttpClientBuilder.create().build();

    public String searchUserAddress(double latitude, double longitude) {
        try {
            String enc = URLEncoder.encode(", ", "UTF-8");
            String url = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?output=json&orders=roadaddr&coords=" + longitude + enc + latitude;

            HttpGet getRequest = new HttpGet(url);
            getRequest.setHeader("X-NCP-APIGW-API-KEY-ID", "y56xusy96s");
            getRequest.setHeader("X-NCP-APIGW-API-KEY", "c4jNxgZSUm4Q2npsEEK4iXMx1NgF7G6qrTKQ82Wo");
            getRequest.setHeader("Accept", "application/json");

            HttpResponse response = httpclient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode bodyJson = mapper.readTree(body);

                if (bodyJson.get("status").get("code").asInt() == 0) {
                    String area1 = bodyJson.get("results").get(0).get("region").get("area1").get("name").asText();
                    String area2 = bodyJson.get("results").get(0).get("region").get("area2").get("name").asText();
                    String area3 = bodyJson.get("results").get(0).get("region").get("area3").get("name").asText();


                    return area1 + " " + area2 + " " + area3;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } catch(Exception e){
            return null;
        }
    }
}
