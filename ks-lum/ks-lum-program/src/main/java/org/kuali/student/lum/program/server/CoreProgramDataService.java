package org.kuali.student.lum.program.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.student.r1.common.assembly.data.Data;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r1.common.search.dto.SearchParam;
import org.kuali.student.r1.common.search.dto.SearchRequest;
import org.kuali.student.r1.common.search.dto.SearchResult;
import org.kuali.student.r1.common.search.dto.SearchResultCell;
import org.kuali.student.r1.common.search.dto.SearchResultRow;
import org.kuali.student.common.ui.server.gwt.AbstractDataService;
import org.kuali.student.r2.common.util.ContextUtils;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.lum.clu.service.CluService;
import org.kuali.student.lum.program.client.ProgramClientConstants;
import org.kuali.student.r2.lum.program.dto.CoreProgramInfo;
import org.kuali.student.r2.lum.program.service.ProgramService;

/**
 * @author Jim
 */
public class CoreProgramDataService extends AbstractDataService {

    private static final long serialVersionUID = 1L;
    
    private ProgramService programService;
    private CluService cluService;

    @Override
    protected String getDefaultWorkflowDocumentType() {
        return null;
    }

    @Override
    protected String getDefaultMetaDataState() {
        return null;
    }

    @Override
    protected Object get(String id,ContextInfo contextInfo) throws Exception {
    	if (id==null || id.isEmpty()){
            return findCurrentCoreProgram();
    	} else {
    		return programService.getCoreProgram(id,contextInfo);
    	}

    }

    @Override
    protected Object save(Object dto, Map<String, Object> properties,ContextInfo contextInfo) throws Exception {
        if (dto instanceof CoreProgramInfo) {
            CoreProgramInfo cpInfo = (CoreProgramInfo) dto;
            if (cpInfo.getId() == null && cpInfo.getVersionInfo(contextInfo) != null) {
            	String coreVersionIndId = cpInfo.getVersionInfo(contextInfo).getVersionIndId();
            	cpInfo = programService.createNewCoreProgramVersion(coreVersionIndId, "New core program version",contextInfo);
            } else if (cpInfo.getId() == null) {
                cpInfo = programService.createCoreProgram(cpInfo.getTypeKey(),cpInfo,contextInfo);
            } else {
            	//TOD KSCM : The parameters I adjusted might not be the correct one for this service
                cpInfo = programService.updateCoreProgram(cpInfo.getId(),cpInfo.getTypeKey(),cpInfo,contextInfo);
            }
            return cpInfo;
        } else {
            throw new InvalidParameterException("Only persistence of CoreProgram is supported by this DataService implementation.");
        }
    }

    @Override
	protected List<ValidationResultInfo> validate(Object dto,ContextInfo contextInfo) throws Exception {
		return programService.validateCoreProgram("OBJECT", (CoreProgramInfo)dto,ContextUtils.getContextInfo());
	}
    
    @Override
    protected Class<?> getDtoClass() {
        return CoreProgramInfo.class;
    }

    private CoreProgramInfo findCurrentCoreProgram() throws MissingParameterException, InvalidParameterException, DoesNotExistException, PermissionDeniedException, OperationFailedException {
        	    SearchRequest request = new SearchRequest();

        //TODO find a better way to get this search, param and resultcolumn names
        
        CoreProgramInfo core = new CoreProgramInfo();
        String coreProgramId = null;
	    request.setSearchKey("lu.search.mostCurrent.union");

        List<SearchParam> searchParams = new ArrayList<SearchParam>();
        SearchParam qpv1 = new SearchParam();
        qpv1.setKey("lu.queryParam.luOptionalType");
        qpv1.setValue(ProgramClientConstants.CORE_PROGRAM);
        searchParams.add(qpv1);

        request.setParams(searchParams);

        SearchResult searchResult = null;
     // TODO KSCM-165 searchResult = cluService.search(request,ContextUtils.getContextInfo());
        if (searchResult.getRows().size() > 0) {
            for(SearchResultRow srrow : searchResult.getRows()){
                List<SearchResultCell> srCells = srrow.getCells();
                if(srCells != null && srCells.size() > 0){
                    for(SearchResultCell srcell : srCells){
                        if (srcell.getKey().equals("lu.resultColumn.cluId")) {
                            coreProgramId = srcell.getValue();
                            break;
                        }
                    }
                }
            }
        }
        if (coreProgramId != null) {
            core = programService.getCoreProgram(coreProgramId, ContextUtils.getContextInfo());
        }
        return core;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public void setLuService(CluService cluService) {
        this.cluService = cluService;
    }

    
    //TODO KSCM : added this via automatic generation ... it needs logic 
	@Override
	public List<ValidationResultInfo> validateData(Data data,
			ContextInfo contextInfo) throws OperationFailedException {
		// TODO Auto-generated method stub
		return null;
	}
}
