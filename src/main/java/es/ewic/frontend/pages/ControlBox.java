package es.ewic.frontend.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

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

}
