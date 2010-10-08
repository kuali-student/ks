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

package org.kuali.student.common.ui.client.service;

import java.util.List;

import org.kuali.student.core.document.dto.DocumentInfo;
import org.kuali.student.core.document.dto.RefDocRelationInfo;
import org.kuali.student.core.dto.StatusInfo;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpcservices/DocumentRpcService")
public interface DocumentRpcService extends BaseRpcService {
	public DocumentInfo getDocument(String documentId) throws Exception;
	
	public List<DocumentInfo> getDocumentsByIdList(List<String> documentIdList) throws Exception;
	
	public StatusInfo deleteDocument(String documentId) throws Exception;
	
    public DocumentInfo updateDocument(String documentId, DocumentInfo documentInfo) throws Exception;
	
	public StatusInfo addDocumentCategoryToDocument(String documentId, String documentCategoryKey) throws Exception;
	
    public StatusInfo removeDocumentCategoryFromDocument(String documentId, String documentCategoryKey) throws Exception;

    /**
     * Check for authorization to upload documents
     * @param referenceId identifier of reference
     * @param referenceTypeKey reference type
     */
    public Boolean isAuthorizedUploadDocuments(String referenceId, String referenceTypeKey);
    
	public StatusInfo deleteRefDocRelation(String docRelationId) throws Exception;
	
	public List<RefDocRelationInfo> getRefDocIdsForRef(String refObjectTypeKey, String refObjectId) throws Exception;

}
