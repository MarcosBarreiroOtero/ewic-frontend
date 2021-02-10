package es.ewic.frontend.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

import es.ewic.frontend.model.Seller;
import es.ewic.frontend.model.Shop;

public class ModelConverter {

	// Seller
	public static Seller jsonToSeller(JSONObject sellerData) {
		return new Seller(sellerData.getInt("idSeller"), sellerData.getString("loginName"),
				sellerData.getString("firstName"), sellerData.getString("lastName"), sellerData.getString("email"));
	}

	// Shop
	public static Shop jsonToShop(JSONObject shopData) {
		return new Shop(shopData.getInt("idShop"), shopData.getString("name"), shopData.getDouble("latitude"),
				shopData.getDouble("longitude"), shopData.getString("location"), shopData.getInt("maxCapacity"),
				shopData.getInt("actualCapacity"), shopData.getString("type"), shopData.getBoolean("allowEntries"),
				shopData.getInt("idSeller"), shopData.getString("timetable"));
	}

	public static List<Shop> jsonArrayToShopList(JSONArray shopsData) {
		ArrayList<Shop> shops = new ArrayList<>();
		for (int i = 0; i < shopsData.length(); i++) {
			JSONObject shopData = shopsData.getJSONObject(i);
			shops.add(jsonToShop(shopData));
		}
		return shops;
	}

}
