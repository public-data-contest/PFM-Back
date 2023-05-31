package com.pfm.project.DB_save_api;

import com.pfm.project.domain.store.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DBSaveController {

    private final DBSaveService dbSaveService;
    private final TuningOpenAPI tuningOpenAPI;

    @Autowired
    public DBSaveController(DBSaveService dbSaveService, TuningOpenAPI tuningOpenAPI) {
        this.dbSaveService = dbSaveService;
        this.tuningOpenAPI = tuningOpenAPI;
    }


    @GetMapping("/api")
    public String index(){
        return "../resources/templates/index";
    }

    @PostMapping("/api/store")
    public void saveStoreDb(){
        List<Store> stores = new ArrayList<>();
        List<PlaceApiDTO> places= new ArrayList<>();

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/55574947656272613834534d504674/json/ListPriceModelStoreService/1/1000");
            URL url1 = new URL("http://openapi.seoul.go.kr:8088/55574947656272613834534d504674/json/ListPriceModelStoreService/1001/1210");

            JSONArray array1 = tuningOpenAPI.getJsonObjectFromOpenAPI(url);
            JSONArray array2 = tuningOpenAPI.getJsonObjectFromOpenAPI(url1);

            for (int i =0; i<array1.size(); i++){
                StorePlaceMapper api_request = tuningOpenAPI.tuningOpenAPIStoreData((JSONObject) array1.get(i));

                if (api_request != null) {
                    stores.add(api_request.getStore());
                    places.add(api_request.getPlace());
                }

            }

            for (int i =0; i<array2.size(); i++){
                StorePlaceMapper api_request = tuningOpenAPI.tuningOpenAPIStoreData((JSONObject) array2.get(i));

                if (api_request != null) {
                    stores.add(api_request.getStore());
                    places.add(api_request.getPlace());
                }

            }

            dbSaveService.saveStoreDB(stores);
            dbSaveService.savePlaceDB(places);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/api/product")
    public void saveProductDb(){

        String result = "";
        String result1 = "";
        String result2 = "";
        String result3 = "";

        List<ProductApiDTO> req = new ArrayList<>();

        try {
            URL url = new URL("http://openAPI.seoul.go.kr:8088/7a584c4877627261393579564c574a/json/ListPriceModelStoreProductService/1/1000");
            URL url1 = new URL("http://openAPI.seoul.go.kr:8088/7a584c4877627261393579564c574a/json/ListPriceModelStoreProductService/1001/2000");
            URL url2 = new URL("http://openAPI.seoul.go.kr:8088/7a584c4877627261393579564c574a/json/ListPriceModelStoreProductService/2001/3000");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type","application/json");

            HttpURLConnection connection1 = (HttpURLConnection)url1.openConnection();
            connection1.setRequestMethod("GET");
            connection1.setRequestProperty("Content-type","application/json");

            HttpURLConnection connection2= (HttpURLConnection)url2.openConnection();
            connection2.setRequestMethod("GET");
            connection2.setRequestProperty("Content-type","application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            BufferedReader bf1 = new BufferedReader(new InputStreamReader(url1.openStream(),"UTF-8"));
            BufferedReader bf2 = new BufferedReader(new InputStreamReader(url2.openStream(),"UTF-8"));

            result = bf.readLine();
            result1 = bf1.readLine();
            result2 = bf2.readLine();

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(result);
            JSONObject object1 = (JSONObject) parser.parse(result1);
            JSONObject object2 = (JSONObject) parser.parse(result2);

            JSONObject out = (JSONObject) object.get("ListPriceModelStoreProductService");
            JSONObject out1 = (JSONObject) object1.get("ListPriceModelStoreProductService");
            JSONObject out2 = (JSONObject) object2.get("ListPriceModelStoreProductService");

            JSONArray array = (JSONArray) out.get("row");
            JSONArray array1 = (JSONArray) out1.get("row");
            JSONArray array2 = (JSONArray) out2.get("row");




            for (int i =0; i<array.size(); i++){
                JSONObject arr = (JSONObject) array.get(i);
                Long id = Long.parseLong((String) arr.get("SH_ID"));
                double price = (double)arr.get("IM_PRICE");
                int resultPrice = Integer.parseInt(String.valueOf(Math.round(price)));
                String productName = (String)arr.get("IM_NAME");


                ProductApiDTO api_request = ProductApiDTO.builder().productPrice(resultPrice).productName(productName).id(id).build();

                req.add(api_request);

            }

            for (int i =0; i<array1.size(); i++){
                JSONObject arr1 = (JSONObject) array1.get(i);
                Long id = Long.parseLong((String) arr1.get("SH_ID"));
                double price = (double)arr1.get("IM_PRICE");
                int resultPrice = Integer.parseInt(String.valueOf(Math.round(price)));
                String productName = (String)arr1.get("IM_NAME");


                ProductApiDTO api_request = ProductApiDTO.builder().productPrice(resultPrice).productName(productName).id(id).build();

                req.add(api_request);

            }

            for (int i =0; i<array2.size(); i++){
                JSONObject arr2 = (JSONObject) array2.get(i);
                Long id = Long.parseLong((String) arr2.get("SH_ID"));
                double price = (double)arr2.get("IM_PRICE");
                int resultPrice = Integer.parseInt(String.valueOf(Math.round(price)));
                String productName = (String)arr2.get("IM_NAME");


                ProductApiDTO api_request = ProductApiDTO.builder().productPrice(resultPrice).productName(productName).id(id).build();

                req.add(api_request);

            }

            dbSaveService.saveProductDB(req);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}