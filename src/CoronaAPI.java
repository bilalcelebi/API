import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpRequest;

import org.json.JSONObject;

public class CoronaAPI {

	public static void main(String[] args) throws IOException {
		
		URL url = new URL("https://api.collectapi.com/corona/totalData");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("content-type", "application/json");
		connection.setRequestProperty("authorization", "apikey 0ibpKKvwmT3tIspyTl2eXM:4FDHQWragVdnFm4mqppPmj");
		
		int status = connection.getResponseCode();
		
		// System.out.println("Code : " + (connection.getResponseCode()));
		
		BufferedReader reader;
		String line;
		StringBuffer response = new StringBuffer();
		
		if(status > 299) {
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
		}
		else {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
		}
		
		String result = response.toString();
		JSONObject obj = new JSONObject(result);
		String totaldeaths = obj.getJSONObject("result").getString("totalDeaths");
		String totalcases = obj.getJSONObject("result").getString("totalCases");
		String totalrecovered = obj.getJSONObject("result").getString("totalRecovered");
		System.out.println("Toplam Ölü : " + totaldeaths);
		System.out.println("Toplam Vaka : " + totalcases);
		System.out.println("Toplam Kurtarılmış Vaka : " + totalrecovered);
	}

}
