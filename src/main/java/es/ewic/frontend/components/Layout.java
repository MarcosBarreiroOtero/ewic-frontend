package es.ewic.frontend.components;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import es.ewic.frontend.pages.Index;
import es.ewic.frontend.services.AuthenticationPolicy;
import es.ewic.frontend.services.AuthenticationPolicyType;
import es.ewic.frontend.util.UserSession;

/**
 * Layout component for pages of application.
 */
public class Layout {

	@Property
	@Parameter(required = true, defaultPrefix = "message")
	private String title;

	@Inject
	Messages messages;
	@Inject
	private Request request;

	@Property
	@SessionState(create = false)
	private UserSession userSession;

	@InjectPage
	private Index index;

	@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USER)
	Object onActionFromLogOut() {
		userSession = null;
		request.getSession(false).invalidate();
		return index;

	}

}
