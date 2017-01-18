/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.requirements.ui.properties.controllers;

import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.vp.requirements.CapellaRequirements.impl.CapellaIncomingRelationImpl;

public class DiagramIncomingLink extends CapellaIncomingRelationImpl {

  private DRepresentation containingRepresentation;
  private String id;

  public DiagramIncomingLink(DRepresentation containingRepresentation, String id) {
    this.containingRepresentation = containingRepresentation;
    this.id = id;
  }

  public DRepresentation getContainingRepresentation() {
    return containingRepresentation;
  }
  
  public String getId() {
    return id;
  }
}
