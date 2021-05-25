package org.polarsys.capella.vp.requirements.importer.commandline;

import org.polarsys.capella.core.transition.common.launcher.IDefaultWorkflow;
import org.polarsys.capella.vp.requirements.importer.transposer.activities.TriggerDiffMerge;
import org.polarsys.capella.vp.requirements.importer.transposer.launcher.RequirementsImportLauncher;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CommandLineRequirementsImportLauncher extends RequirementsImportLauncher {

	  public CommandLineRequirementsImportLauncher() {
	    super();
	    activities.put(IDefaultWorkflow.WORKFLOW_STEP__INITIALIZATION,
	        new String[] { CommandLineInitializeTransformation.getId() });
	    activities.put(IDefaultWorkflow.WORKFLOW_STEP__DIFF_MERGE,
	        new String[] { TriggerDiffMerge.getId(), CommandLineTransposerTransformation.getId() });
	  }

	  public IContext getContext() {
	    return context;
	  }
}
