package es.ewic.frontend.pages;

import java.util.List;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import es.ewic.frontend.model.Entry;
import es.ewic.frontend.model.Reservation;
import es.ewic.frontend.model.Shop;
import es.ewic.frontend.services.AuthenticationPolicy;
import es.ewic.frontend.services.AuthenticationPolicyType;
import es.ewic.frontend.util.DateUtils;
import es.ewic.frontend.util.ModelConverter;
import es.ewic.frontend.util.RequestUtils;
import es.ewic.frontend.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USER)
public class ControlBox {

	@Inject
	Messages messages;
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	@Inject
	private AlertManager alertManager;

	@SessionState(create = false)
	private UserSession userSession;

	@Property
	private List<Shop> shops;
	@Property
	private Shop shop;

	@Property
	private Shop activeShop;
	@Property
	private List<Reservation> reservations;
	@Property
	private Reservation reservation;
	@Property
	private String nEntries;
	@Property
	private String avgDuration;
	@InjectComponent
	private Zone activeShopArea;

	public String getCapacityColorClass() {
		float percentage = activeShop.getActualCapacity() / activeShop.getMaxCapacity();

		if (percentage < 75) {
			return "text-success";
		} else if (percentage < 100) {
			return "text-warning";
		} else {
			return "text-danger";
		}
	}

	public String getCapacityControlState() {
		float percentage = activeShop.getActualCapacity() / activeShop.getMaxCapacity();
		return (int) percentage + " % (" + activeShop.getActualCapacity() + " / " + activeShop.getMaxCapacity() + ")";
	}

	public String getShopType() {
		return messages.get(activeShop.getType());
	}

	public String getReservationDate() {
		return DateUtils.formatDateLong(reservation.getDate());
	}

	public String getReservationState() {
		return messages.get(reservation.getState());
	}

	void setupRender() {
		if (userSession != null) {
			JSONArray shopsData = RequestUtils.getSellerShops(userSession.getSeller().getIdSeller());

			shops = ModelConverter.jsonArrayToShopList(shopsData);

		}
	}

	void onActionFromClickShop(int idShop) {
		JSONObject shopData = RequestUtils.getShopById(idShop);
		activeShop = ModelConverter.jsonToShop(shopData);

		JSONArray reservatonsData = RequestUtils.getUpcomingReservations(idShop);
		reservations = ModelConverter.jsonArrayToReservationList(reservatonsData);

		JSONArray entriesData = RequestUtils.getDailyEntries(idShop);
		List<Entry> dailyEntries = ModelConverter.jsonArrayToEntryList(entriesData);
		if (!dailyEntries.isEmpty()) {
			long durationCont = 0;
			for (Entry entry : dailyEntries) {
				durationCont += entry.getDuration();
			}

			float avg = (float) durationCont / dailyEntries.size();
			avg = Math.round(avg);

			avgDuration = avg < 1 ? messages.get("entriesLessThan1") : avg + " m";
		} else {
			avgDuration = "0 m";
		}

		nEntries = Integer.toString(dailyEntries.size());

		ajaxResponseRenderer.addRender(activeShopArea);
	}

	Object afterRender() {
		if (userSession == null) {
			return Index.class;
		}

		return null;
	}

}
