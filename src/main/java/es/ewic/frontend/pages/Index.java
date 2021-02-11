package es.ewic.frontend.pages;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import es.ewic.frontend.model.Seller;
import es.ewic.frontend.services.AuthenticationPolicy;
import es.ewic.frontend.services.AuthenticationPolicyType;
import es.ewic.frontend.util.ModelConverter;
import es.ewic.frontend.util.RequestUtils;
import es.ewic.frontend.util.UserSession;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USER)
public class Index {

	@Inject
	Messages messages;
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	@Inject
	private AlertManager alertManager;

	@SessionState(create = false)
	private UserSession userSession;

	@Property
	private String username;
	@Property
	private String password;

	@InjectComponent("username")
	private Field usernameField;
	@InjectComponent("password")
	private Field passwordField;
	@InjectComponent
	private Form loginForm;

	void onValidateFromLoginForm() {
		JSONObject sellerData = RequestUtils.loginSeller(username, password);

		if (sellerData != null) {
			Seller seller = ModelConverter.jsonToSeller(sellerData);
			userSession = new UserSession();
			userSession.setSeller(seller);
		} else {
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, messages.get("error-login"));
			loginForm.recordError(usernameField, messages.get("error-login"));
			loginForm.recordError(passwordField, messages.get("error-login"));
		}
	}

	Object onSuccessFromLoginForm() {

		return ControlBox.class;
	}

	void setupRender() {
	}

}
