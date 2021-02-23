package es.ewic.frontend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

public class RequestUtils {

	public static final String BASE_ENDPOINT = "http://192.168.1.44:8080/ewic";

	public static JSONObject loginSeller(String username, String password) {

		try {
			URL url = new URL(BASE_ENDPOINT + "/seller/login?loginName=" + username + "&password=" + password);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				return new JSONObject(content.toString());
			}
			con.disconnect();
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

	public static JSONArray getSellerShops(int idSeller) {

		try {
			URL url = new URL(BASE_ENDPOINT + "/shop/seller/" + idSeller);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				return new JSONArray(content.toString());
			}
			con.disconnect();
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

	public static JSONObject getShopById(int idShop) {
		try {
			URL url = new URL(BASE_ENDPOINT + "/shop/" + idShop);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				return new JSONObject(content.toString());
			}
			con.disconnect();
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

	public static JSONArray getUpcomingReservations(int idShop) {
		try {
			URL url = new URL(BASE_ENDPOINT + "/reservation/seller/" + idShop);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				return new JSONArray(content.toString());
			}
			con.disconnect();
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

	public static JSONArray getDailyEntries(int idShop) {
		try {
			Calendar now = Calendar.getInstance();
			URL url = new URL(
					BASE_ENDPOINT + "/shop/" + idShop + "/dailyEntries?date=" + DateUtils.formatBackendDate(now));

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				return new JSONArray(content.toString());
			}
			con.disconnect();
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

}
