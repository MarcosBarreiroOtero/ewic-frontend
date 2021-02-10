package es.ewic.frontend.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

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

	@Property
	@SessionState(create = false)
	private UserSession UserSession;

}
