package es.ewic.frontend.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

import es.ewic.frontend.model.Entry;
import es.ewic.frontend.model.Reservation;
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

	// Reservation
	public static Reservation jsonToReservation(JSONObject reservationData) {
		Calendar reservationDate = DateUtils.parseDateLong(reservationData.getString("date"));
		reservationDate = DateUtils.changeCalendarTimezoneFromUTCToDefault(reservationDate);

		return new Reservation(reservationData.getInt("idReservation"), reservationDate,
				reservationData.getString("state"), reservationData.getString("remarks"),
				reservationData.getInt("nClients"), reservationData.getString("idGoogleLoginClient"),
				reservationData.getInt("idShop"), reservationData.getString("clientName"));
	}

	public static List<Reservation> jsonArrayToReservationList(JSONArray reservationsData) {
		ArrayList<Reservation> reservations = new ArrayList<>();
		for (int i = 0; i < reservationsData.length(); i++) {
			JSONObject reservationData = reservationsData.getJSONObject(i);
			reservations.add(jsonToReservation(reservationData));
		}
		return reservations;
	}

	// Entry
	public static Entry jsonToEntry(JSONObject entryData) {
		return new Entry(entryData.getInt("entryNumber"), entryData.getLong("duration"),
				entryData.getString("description"));
	}

	public static List<Entry> jsonArrayToEntryList(JSONArray entriesData) {
		ArrayList<Entry> entries = new ArrayList<>();
		for (int i = 0; i < entriesData.length(); i++) {
			JSONObject entryData = entriesData.getJSONObject(i);
			entries.add(jsonToEntry(entryData));
		}
		return entries;
	}

}
