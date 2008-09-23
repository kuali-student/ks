/**
 * 
 */
package org.kuali.student.rules.devgui.client.service;

import java.util.List;

import org.kuali.student.rules.devgui.client.model.RuleTypesHierarchyInfo;
import org.kuali.student.rules.devgui.client.model.RulesHierarchyInfo;
import org.kuali.student.rules.rulemanagement.dto.BusinessRuleInfoDTO;
import org.kuali.student.rules.rulemanagement.dto.BusinessRuleTypeDTO;
import org.kuali.student.rules.rulemanagement.dto.StatusDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author zzraly
 */
public interface DevelopersGuiServiceAsync {
    public void findRulesHierarchyInfo(AsyncCallback<List<RulesHierarchyInfo>> callback);

    public void findRuleTypesHierarchyInfo(AsyncCallback<List<RuleTypesHierarchyInfo>> callback);

    public void createBusinessRule(BusinessRuleInfoDTO businessRuleInfo, AsyncCallback<String> callback);

    public void updateBusinessRule(String businessRuleId, BusinessRuleInfoDTO businessRuleInfo, AsyncCallback<StatusDTO> callback);

    public void fetchDetailedBusinessRuleInfo(String ruleId, AsyncCallback<BusinessRuleInfoDTO> callback);

    public void fetchBusinessRuleTypeInfo(String ruleTypeKey, AsyncCallback<BusinessRuleTypeDTO> callback);
}
