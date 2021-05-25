/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.requirements.importer.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Joao Barata
 */
public class RequirementsPreferencesPlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.vp.requirements.importer.preferences"; //$NON-NLS-1$

  // The shared instance
  private static RequirementsPreferencesPlugin plugin;

  private ScopedPreferenceStore preferenceStore;

  @Override
  public IPreferenceStore getPreferenceStore() {

    // Create the preference store lazily.
    if (preferenceStore == null) {
      preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, RequirementsPreferencesPlugin.PLUGIN_ID);
    }
    return preferenceStore;
  }
  
  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    new RequirementsPreferencesInitializer().initializeDefaultPreferences();
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static RequirementsPreferencesPlugin getDefault() {
    return plugin;
  }
}


//public class RequirementsPreferencesPlugin implements BundleActivator {
//
//  // The plug-in ID
//  public static final String PLUGIN_ID = "org.polarsys.capella.vp.requirements.importer.preferences"; //$NON-NLS-1$
//
//  // The shared instance
//	private static BundleContext context;
//
//  private ScopedPreferenceStore preferenceStore;
//
//  public IPreferenceStore getPreferenceStore() {
//
//    // Create the preference store lazily.
//    if (preferenceStore == null) {
//      preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, RequirementsPreferencesPlugin.PLUGIN_ID);
//    }
//    return preferenceStore;
//  }
//	public static RequirementsPreferencesPlugin getDefault() {
//		return plugin;
//	}
//
//	public static BundleContext getContext() {
//		return context;
//	}
//
//	/**
//	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
//	 */
//	public void start(BundleContext bundleContext) throws Exception {
//		RequirementsPreferencesPlugin.context = bundleContext;
//	}
//
//	/**
//	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
//	 */
//	public void stop(BundleContext bundleContext) throws Exception {
//		RequirementsPreferencesPlugin.context = null;
//	}
//
//}
