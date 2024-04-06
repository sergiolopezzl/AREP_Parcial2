package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static spark.Spark.*;

public class ServiceProxy {

    public static void main(String[] args) {
        //44
        staticFiles.location("/public");
        port(getPort());
        get("/linear-search", (req, res) -> {
            res.type("application/json");
            String output = invoker("http://ec2-3-82-11-102.compute-1.amazonaws.com:4500//linear-search?" + req.queryString());
            //String output = invoker("http://localhost:4500/linear-search?" + req.queryString());
            
            return output;
        });
        get("/binary-search", (req, res) -> {
            res.type("application/json");
            String output = invoker("http://ec2-18-209-33-177.compute-1.amazonaws.com:4500/binary-search?" + req.queryString());
            //String output = invoker("http://localhost:4500/binary-search?" + req.queryString());
            
            return output;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }


    private static String invoker(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();
        } else System.out.println("GET request not worked");
        System.out.println(response);
        System.out.println("GET DONE");
        return response.toString();
    }
}
