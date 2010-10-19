package org.kuali.student.lum.program.client.major.edit;

import org.kuali.student.lum.common.client.configuration.Configuration;
import org.kuali.student.lum.program.client.AbstractProgramConfigurer;
import org.kuali.student.lum.common.client.configuration.ConfigurationManager;

import com.google.gwt.core.client.GWT;

/**
 * @author Igor
 */
public class MajorEditConfigurer extends AbstractProgramConfigurer {

    public MajorEditConfigurer() {
        programSectionConfigManager = new ConfigurationManager(this);
        programSectionConfigManager.registerConfiguration(new MajorInformationEditConfiguration());
        programSectionConfigManager.registerConfiguration(new ManagingBodiesEditConfiguration());
        programSectionConfigManager.registerConfiguration(new SpecializationsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new CatalogInformationEditConfiguration());
        programSectionConfigManager.registerConfiguration(new ProgramRequirementsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new LearningObjectivesEditConfiguration());
        programSectionConfigManager.registerConfiguration(new SupportingDocsEditConfiguration());
        programSectionConfigManager.registerConfiguration(new MajorSummaryConfiguration());
    }
}
