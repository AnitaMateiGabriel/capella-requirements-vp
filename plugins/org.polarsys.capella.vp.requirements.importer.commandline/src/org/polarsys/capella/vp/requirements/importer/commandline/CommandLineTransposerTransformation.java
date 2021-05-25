package org.polarsys.capella.vp.requirements.importer.commandline;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.diffmerge.bridge.api.incremental.IIncrementalBridgeExecution;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.vp.requirements.importer.transposer.activities.TransposerTransformation;
import org.polarsys.capella.vp.requirements.importer.transposer.bridge.RequirementsVPBridge;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;

public class CommandLineTransposerTransformation extends TransposerTransformation {

	  public static String getId() {
	    return CommandLineTransposerTransformation.class.getCanonicalName();
	  }
	 
	  

	  @Override
	  protected IStatus _run(ActivityParameters activityParams) {
	    // Override this to avoid launching the activity in a Job in testing mode
	    IStatus status = mergeAndSave(activityParams, new NullProgressMonitor());
	    return status;
	  }

}
