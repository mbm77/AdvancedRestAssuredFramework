package java8.filter;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args){
        try {
            URL url = new URL("https://jsonmock.hackerrank.com/api/tvseries");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            System.out.println(sb.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
