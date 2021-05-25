/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.vp.requirements.importer.commandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.commandline.core.ui.AbstractWorkbenchCommandLine;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.vp.requirements.importer.RequirementsVPPlugin;
import org.polarsys.capella.vp.requirements.importer.transposer.launcher.RequirementsImportLauncher;

/**
 * @author Joao Barata
 */
public class RequirementsImporterCommandline extends AbstractWorkbenchCommandLine {

	public RequirementsImporterCommandline() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printHelp() {
		System.out.println("Capella Requirements Command Line Importer"); //$NON-NLS-1$
		super.printHelp();
	}

	@Override
	public void checkArgs(IApplicationContext context) throws CommandLineException {
		super.checkArgs(context);

	}

	@Override
	public void prepare(IApplicationContext context) throws CommandLineException {
		super.prepare(context);
	}
//
//  @Override
//  public boolean execute(IApplicationContext context) throws CommandLineException {
//    // load the AIRD
//    String fileURI = "platform:/resource/" + argHelper.getInputs();
//    URI uri = URI.createURI(fileURI);
//    String outputFolder = argHelper.getOutputFolder();
//
//    boolean status;
//    try {
//      status = execute(uri, outputFolder);
//    } catch (FileNotFoundException exception) {
//      logError(exception.getMessage());
//      throw new CommandLineException(exception.getMessage());
//    } catch (CoreException exception) {
//      logError(exception.getMessage());
//      throw new CommandLineException(exception.getMessage());
//    }
//    if (status) {
//      logInfo("validation report generated to: " + " " + argHelper.getOutputFolder()); //$NON-NLS-1$ //$NON-NLS-2$
//    }
//    return false;
//  }

	private IStatus execute(final URI uri, final String outputFolder)
			throws FileNotFoundException, CoreException, CommandLineException {
		final String projectTestName = "sample3";
		final String operationalAnalysis = "19fef609-7a17-4a16-8c4d-563ae4cfd1c4";

//	final EObject target = session.getSemanticElement(operationalAnalysis);
		Resource resource = loadAirdSemanticModel(uri);
//    	Object object = context.g("sample3/sample3.capella#19fef609-7a17-4a16-8c4d-563ae4cfd1c4");
//		if (resource instanceof BlockArchitecture)
//			logInfo("is operational analysis");

		return Status.OK_STATUS;
	}

	/**
	 * @param uri
	 * @return
	 */
	private Resource loadAirdSemanticModel(URI uri) {
		SessionManager sessionManager = SessionManager.INSTANCE;
		Session session = sessionManager.getSession(uri, new NullProgressMonitor());

		Collection<Resource> resources = session.getSemanticResources();
		IModel model = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());

		IScope scope = new ScopeModelWrapper(model); // model e de tip ICapellaModel
		EObject selectedElement = IdManager.getInstance().getEObject("19fef609-7a17-4a16-8c4d-563ae4cfd1c4", scope);

		if (selectedElement instanceof BlockArchitecture) {
			System.out.println("yeee");
//    	run(URI.createFileURI("D:/capella-requirements-vp-forkv2/capella-requirements-vp/tests/org.polarsys.capella.vp.requirements.ju/model/inputs/Sample3.reqif"),
//    			(BlockArchitecture) selectedElement);
			TransactionHelper.getExecutionManager(selectedElement).execute(new AbstractReadWriteCommand() {
				@Override
				public void run() {
					URI model = URI.createFileURI("D:/capella-requirements-vp-forkv2/capella-requirements-vp/tests/org.polarsys.capella.vp.requirements.ju/model/inputs/Sample3.reqif");
					new CommandLineRequirementsImportLauncher().launch(model, (BlockArchitecture) selectedElement,
							new NullProgressMonitor());
				}
			});

			session.save(new NullProgressMonitor());
		}

		if (!resources.isEmpty()) {
			Resource semanticResource = resources.iterator().next();
			return semanticResource;
		}
		return null;
	}

	protected void run(final URI model, final BlockArchitecture target) {
		new CommandLineRequirementsImportLauncher().launch(model, target, new NullProgressMonitor());
	}

	@Override
	protected IStatus executeWithinWorkbench() { // TODO Auto-generated method stub
		// load the AIRDs
		List<IFile> airdFiles = getAirdFilesFromInput();
		String outputFolder = argHelper.getOutputFolder();
		IStatus status = Status.OK_STATUS;
		for (IFile file : airdFiles) {
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			IPath x = file.getFullPath();
			try {
				status = execute(uri, outputFolder);
			} catch (Exception exception) {
				logInfo("no good");
			}
			if (status.isOK()) {
				logInfo("validation report generated to: " + " " + argHelper.getOutputFolder()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return status;
	}
}
