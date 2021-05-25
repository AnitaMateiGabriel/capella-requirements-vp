package org.polarsys.capella.vp.requirements.importer.commandline;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.vp.requirements.importer.RequirementsVPPlugin;

public class Activator implements BundleActivator {

	  // The plug-in ID
	  public static final String PLUGIN_ID = "org.polarsys.capella.vp.requirements.importer.commandline"; //$NON-NLS-1$

		private static BundleContext context;

		public static BundleContext getContext() {
			return context;
		}

		/**
		 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
		 */
		public void start(BundleContext bundleContext) throws Exception {
			Activator.context = bundleContext;
		}

		/**
		 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
		 */
		public void stop(BundleContext bundleContext) throws Exception {
			Activator.context = null;
		}

}
