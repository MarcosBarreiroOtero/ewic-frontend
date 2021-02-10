package es.ewic.frontend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.tapestry5.json.JSONObject;

public class RequestUtils {

	public static final String BASE_ENDPOINT = "http://192.168.1.44:8080/ewic";

	public static JSONObject loginSeller(String username, String password) {

		URL url;
		try {
			url = new URL(BASE_ENDPOINT + "/seller/login?loginName=" + username + "&password=" + password);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				JSONObject sellerData = new JSONObject(content.toString());
				return sellerData;
			}
			con.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
