import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main {

	public static void main(String[] args) throws IOException {
		
		URL url = new URL("https://api.covid19api.com/countries");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		int code = con.getResponseCode();
		
		
		BufferedReader reader;
		String line;
		StringBuffer buffer = new StringBuffer();
		
		if(code > 299) {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
		}
		else {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
		}
		
		// System.out.println(buffer.toString());
		
		HttpClientAPI api = new HttpClientAPI();
		api.parse(buffer.toString());
		
		con.disconnect();
		
		
		
	}

}
