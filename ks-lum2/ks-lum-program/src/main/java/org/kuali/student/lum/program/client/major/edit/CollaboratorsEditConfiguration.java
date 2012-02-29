package org.kuali.student.lum.program.client.major.edit;

import org.kuali.student.r1.common.ui.client.configurable.mvc.Configurer;
import org.kuali.student.core.workflow.ui.client.views.CollaboratorSectionView;
import org.kuali.student.lum.common.client.configuration.AbstractSectionConfiguration;
import org.kuali.student.lum.program.client.ProgramConstants;
import org.kuali.student.lum.program.client.ProgramMsgConstants;
import org.kuali.student.lum.program.client.ProgramSections;

import com.google.gwt.core.client.GWT;

/**
 * @author Will
 */
public class CollaboratorsEditConfiguration extends AbstractSectionConfiguration {

    public CollaboratorsEditConfiguration(Configurer configurer) {
        this.setConfigurer(configurer);
        rootSection = GWT.create(CollaboratorSectionView.class);
        CollaboratorSectionView view = (CollaboratorSectionView)rootSection;
// TODO KSCM wait for ks-core-ui/paul        view.init(ProgramSections.COLLABORATORS_EDIT, getLabel(ProgramMsgConstants.PROGRAM_MENU_SECTIONS_COLLABORATORS),ProgramConstants.PROGRAM_MODEL_ID);
    	//rootSection = new CollaboratorSectionView(ProgramSections.COLLABORATORS_EDIT, getLabel(ProgramMsgConstants.PROGRAM_MENU_SECTIONS_COLLABORATORS),ProgramConstants.PROGRAM_MODEL_ID);
    }

    @Override
    protected void buildLayout() {

    }
}
