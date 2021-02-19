package es.ewic.frontend.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.ComponentEventRequestFilter;
import org.apache.tapestry5.services.PageRenderRequestFilter;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;

/**
 * This module is automatically included as part of the Tapestry IoC Registry,
 * it's a good place to configure and extend Tapestry, or to place your own
 * service definitions.
 */
public class AppModule {
	public static void bind(ServiceBinder binder) {
		// binder.bind(MyServiceInterface.class, MyServiceImpl.class);

		// Make bind() calls on the binder object to define most IoC services.
		// Use service builder methods (example below) when the implementation
		// is provided inline, or requires more initialization than simply
		// invoking the constructor.
		binder.bind(PageRenderAuthenticationFilter.class);
		binder.bind(ComponentEventAuthenticationFilter.class);
	}

	public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {
		// The values defined here (as factory default overrides) are themselves
		// overridden with application defaults by DevelopmentModule and QaModule.

		// The application version is primarily useful as it appears in
		// any exception reports (HTML or textual).
		configuration.override(SymbolConstants.APPLICATION_VERSION, "0.0.1-SNAPSHOT");

		// This is something that should be removed when going to production, but is
		// useful
		// in the early stages of development.
		configuration.override(SymbolConstants.PRODUCTION_MODE, false);
	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
		// Contributions to ApplicationDefaults will override any contributions to
		// FactoryDefaults (with the same key). Here we're restricting the supported
		// locales to just "en" (English). As you add localised message catalogs and
		// other assets,
		// you can extend this list of locales (it's a comma separated series of locale
		// names;
		// the first locale name is the default when there's no reasonable match).
//		configuration.add(SymbolConstants.CHARSET, "UTF-8");
//		configuration.add("org.apache.tapestry.messages-encoding", "UTF-8");
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en, es");

		// You should change the passphrase immediately; the HMAC passphrase is used to
		// secure
		// the hidden field data stored in forms to encrypt and digitally sign
		// client-side data.
		configuration.add(SymbolConstants.HMAC_PASSPHRASE, "change this immediately");
	}

	/**
	 * Contribute our {@link ComponentClassTransformWorker2} to transformation
	 * pipeline to add our code to loaded classes
	 *
	 * @param configuration component class transformer configuration
	 */
	public static void contributeComponentClassTransformWorker(
			OrderedConfiguration<ComponentClassTransformWorker2> configuration) {

		configuration.add("AuthenticationPolicy", new AuthenticationPolicyWorker());

	}

	/**
	 * Contributes "PageRenderAuthenticationFilter" filter which checks for access
	 * rights of requests.
	 */
	public void contributePageRenderRequestHandler(OrderedConfiguration<PageRenderRequestFilter> configuration,
			PageRenderRequestFilter pageRenderAuthenticationFilter) {

		/*
		 * Add filters to the filters pipeline of the PageRender command of the
		 * MasterDispatcher service.
		 */
		configuration.add("PageRenderAuthenticationFilter", pageRenderAuthenticationFilter, "before:*");

	}

	/**
	 * Contribute "PageRenderAuthenticationFilter" filter to determine if the event
	 * can be processed and the user has enough rights to do so.
	 */
	public void contributeComponentEventRequestHandler(OrderedConfiguration<ComponentEventRequestFilter> configuration,
			ComponentEventRequestFilter componentEventAuthenticationFilter) {

		/*
		 * Add filters to the filters pipeline of the ComponentEvent command of the
		 * MasterDispatcher service.
		 */
		configuration.add("ComponentEventAuthenticationFilter", componentEventAuthenticationFilter, "before:*");

	}

	/**
	 * Use annotation or method naming convention:
	 * <code>contributeApplicationDefaults</code>
	 */
	@Contribute(SymbolProvider.class)
	@ApplicationDefaults
	public static void setupEnvironment(MappedConfiguration<String, Object> configuration) {
		// Support for jQuery is new in Tapestry 5.4 and will become the only supported
		// option in 5.5.
		configuration.add(SymbolConstants.CHARSET, "UTF-8");
		configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
//		configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "context:mybootstrap");
	}

}
