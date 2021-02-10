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

import es.ewic.frontend.model.Shop;
import es.ewic.frontend.util.ModelConverter;
import es.ewic.frontend.util.RequestUtils;
import es.ewic.frontend.util.UserSession;

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

	void setupRender() {
		if (userSession != null) {
			System.out.println(userSession == null);
			System.out.println(userSession.getSeller().getIdSeller());
			JSONArray shopsData = RequestUtils.getSellerShops(userSession.getSeller().getIdSeller());

			shops = ModelConverter.jsonArrayToShopList(shopsData);

		}
	}

	void onActionFromClickShop(int idShop) {
		System.out.println(idShop);

		JSONObject shopData = RequestUtils.getShopById(idShop);

		activeShop = ModelConverter.jsonToShop(shopData);

		ajaxResponseRenderer.addRender(activeShopArea);
	}

	Object afterRender() {
		if (userSession == null) {
			return Index.class;
		}

		return null;
	}

}
