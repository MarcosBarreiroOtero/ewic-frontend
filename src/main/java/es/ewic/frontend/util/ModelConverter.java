package es.ewic.frontend.util;

import org.apache.tapestry5.json.JSONObject;

import es.ewic.frontend.model.Seller;

public class ModelConverter {

	// Seller

	public static Seller jsonToSeller(JSONObject sellerData) {
		return new Seller(sellerData.getInt("idSeller"), sellerData.getString("loginName"),
				sellerData.getString("firstName"), sellerData.getString("lastName"), sellerData.getString("email"));
	}

}
