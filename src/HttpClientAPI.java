import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpClientAPI {

	public static void main(String[] args) {
		
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.covid19api.com/countries")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenApply(HttpClientAPI::parse)
			.join();
		
	}
	
	public static String parse(String responseBody) {
		
		JSONArray albums = new JSONArray(responseBody);
		for(int i = 0; i < albums.length(); i++) {
			JSONObject album = albums.getJSONObject(i);
			String country = album.getString("Country");
			String slug = album.getString("Slug");
			String ıso2 = album.getString("ISO2");
			System.out.println(country + " " + slug + " " + ıso2);
		}
		
		
		return null;
		
	}

}
