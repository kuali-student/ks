/**
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.lum.lu.ui.course.server.gwt;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.student.common.ui.server.gwt.DataGwtServlet;
import org.kuali.student.core.dto.StatusInfo;
import org.kuali.student.core.statement.dto.StatementTreeViewInfo;
import org.kuali.student.lum.lu.ui.course.client.requirements.CourseRequirementsDataModel;
import org.kuali.student.lum.lu.ui.course.client.service.CreditCourseProposalRpcService;

public class CreditCourseProposalRpcGwtServlet extends DataGwtServlet implements
        CreditCourseProposalRpcService {

	final static Logger LOG = Logger.getLogger(CreditCourseProposalRpcGwtServlet.class);

	private static final long serialVersionUID = 1L;

    @Override
    public List<StatementTreeViewInfo> getCourseStatements(String courseId, String nlUsageTypeKey, String language) throws Exception {
        return null;  
    }

    @Override
    public Map<Integer, StatementTreeViewInfo> storeCourseStatements(String courseId, Map<Integer, CourseRequirementsDataModel.requirementState> states, Map<Integer, StatementTreeViewInfo> rules) throws Exception {
        return null;
    }

    @Override
    public StatementTreeViewInfo createCourseStatement(String courseId, StatementTreeViewInfo statementTreeViewInfo) throws Exception {
        return null;  
    }

    @Override
    public StatusInfo deleteCourseStatement(String courseId, StatementTreeViewInfo statementTreeViewInfo) throws Exception {
        return null;  
    }
    
    @Override
    public StatementTreeViewInfo updateCourseStatement(String courseId, StatementTreeViewInfo statementTreeViewInfo) throws Exception {
        return null;
    }
    
    @Override
    public StatusInfo changeState(String courseId, String newState) throws Exception {
    	return null;
    }
    
    public StatusInfo changeState(String courseId, String newState, Date currentVersionStart) throws Exception {
    	return null;
    }
}