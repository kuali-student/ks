/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may	obtain a copy of the License at
 *
 * 	http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.enrollment.lpr.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.kuali.student.common.dto.AttributeInfo;
import org.kuali.student.common.dto.ContextInfo;
import org.kuali.student.common.dto.StatusInfo;
import org.kuali.student.common.exceptions.AlreadyExistsException;
import org.kuali.student.common.exceptions.DisabledIdentifierException;
import org.kuali.student.common.exceptions.DoesNotExistException;
import org.kuali.student.common.exceptions.InvalidParameterException;
import org.kuali.student.common.exceptions.MissingParameterException;
import org.kuali.student.common.exceptions.OperationFailedException;
import org.kuali.student.common.exceptions.PermissionDeniedException;
import org.kuali.student.common.exceptions.ReadOnlyException;
import org.kuali.student.common.exceptions.VersionMismatchException;
import org.kuali.student.common.infc.HoldsLprService;
import org.kuali.student.common.infc.HoldsLuiService;
import org.kuali.student.enrollment.lpr.dto.LuiPersonRelationInfo;
import org.kuali.student.enrollment.lpr.dto.LuiPersonRelationStateInfo;
import org.kuali.student.enrollment.lpr.dto.LuiPersonRelationTypeInfo;
import org.kuali.student.enrollment.lpr.infc.LuiPersonRelationStateInfc;
import org.kuali.student.enrollment.lpr.service.LuiPersonRelationService;
import org.kuali.student.enrollment.lui.dto.LuiInfo;
import org.kuali.student.enrollment.lui.service.LuiService;

/**
 * @author nwright
 */
public class LuiPersonRelationServiceMockPersistenceImpl extends LuiPersonRelationServiceAdapter
        implements LuiPersonRelationService, HoldsLprService, HoldsLuiService {

    private LuiService luiService;

    @Override
    public LuiService getLuiService() {
        return luiService;
    }

    @Override
    public void setLuiService(LuiService luiService) {
        this.luiService = luiService;
    }
    
    private Map<String, LuiPersonRelationInfo> lprCache = new HashMap<String, LuiPersonRelationInfo>();

    @Override
    public List<String> createBulkRelationshipsForPerson(String personId,
            List<String> luiIdList,
            String relationState,
            String luiPersonRelationType,
            LuiPersonRelationInfo luiPersonRelationInfo,
            ContextInfo context)
            throws AlreadyExistsException, DoesNotExistException,
            DisabledIdentifierException, InvalidParameterException,
            MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        List<String> lprIds = new ArrayList<String>(luiIdList.size());
        for (String luiId : luiIdList) {
            LuiPersonRelationInfo lprInfo = new LuiPersonRelationInfo.Builder(luiPersonRelationInfo).luiId(luiId).build();

            String lprId = this.createLuiPersonRelation(personId,
                    luiId,
                    luiPersonRelationType,
                    lprInfo,
                    context);
            lprIds.add(lprId);
        }
        return lprIds;
    }

    @Override
    public String createLuiPersonRelation(String personId, String luiId,
            String luiPersonRelationType,
            LuiPersonRelationInfo luiPersonRelationInfo,
            ContextInfo context) throws
            AlreadyExistsException,
            DoesNotExistException,
            DisabledIdentifierException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        MockHelper helper = new MockHelper();
        LuiPersonRelationInfo.Builder builder = new LuiPersonRelationInfo.Builder(luiPersonRelationInfo);
        builder.id(UUID.randomUUID().toString()).personId(personId).luiId(luiId).type(luiPersonRelationType).metaInfo(helper.createMeta(context));
        LuiPersonRelationInfo copy = builder.build();
        this.lprCache.put(copy.getId(), copy);
        return copy.getId();
    }

    @Override
    public StatusInfo deleteLuiPersonRelation(String luiPersonRelationId, ContextInfo context) throws
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        if (this.lprCache.remove(luiPersonRelationId) == null) {
            throw new DoesNotExistException(luiPersonRelationId);
        }
        return new StatusInfo.Builder().success(Boolean.TRUE).build();
    }

    @Override
    public LuiPersonRelationInfo fetchLUIPersonRelation(String luiPersonRelationId,
            ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        LuiPersonRelationInfo bean = this.lprCache.get(
                luiPersonRelationId);
        if (bean == null) {
            throw new DoesNotExistException(luiPersonRelationId);
        }
        return bean;
    }

    @Override
    public List<String> findAllValidLuisForPerson(String personId,
            String luiPersonRelationType,
            String relationState,
            String atpId,
            ContextInfo context) throws
            DoesNotExistException,
            DisabledIdentifierException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        List<String> luiIds = new ArrayList();
        for (LuiPersonRelationInfo lpr : this.lprCache.values()) {
            if (!personId.equals(lpr.getPersonId())) {
                continue;
            }
            if (!luiPersonRelationType.equals(lpr.getType())) {
                continue;
            }
            if (!relationState.equals(lpr.getState())) {
                continue;
            }
            LuiInfo lui = luiService.getLui(lpr.getLuiId(), context);
            if (!atpId.equals(lui.getAtpKey())) {
                continue;
            }
            luiIds.add(lpr.getLuiId());
        }
        return luiIds;
    }

//    @Override
//    public List<String> findAllValidPeopleForLui(String luiId,
//                                                 String luiPersonRelationType,
//                                                 String relationState,
//                                                 ContextInfo context) throws
//            DoesNotExistException,
//            DisabledIdentifierException,
//            InvalidParameterException,
//            MissingParameterException,
//            OperationFailedException,
//            PermissionDeniedException {
//        List<String> personIds = new ArrayList();
//        for (LuiPersonRelationInfo bean : this.lprCache.values()) {
//            if (!luiId.equals(bean.getLuiId())) {
//                continue;
//            }
//            if (!luiPersonRelationType.equals(bean.getType())) {
//                continue;
//            }
//            if (!relationState.equals(bean.getState())) {
//                continue;
//            }
//            LuiInfo lui = luiService.getLui(bean.getLuiId(), context);
//            personIds.add(bean.getPersonId());
//        }
//        return personIds;
//    }
    @Override
    public List<LuiPersonRelationStateInfo> findAllowedRelationStates(
            String luiPersonRelationType,
            ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException {
        // check type is valid
        this.getLuiPersonRelationTypeEnum(luiPersonRelationType);
        if (isInstructorType(luiPersonRelationType)) {
            List<LuiPersonRelationStateInfo> states = new ArrayList(LuiPersonRelationStateEnum.COURSE_INSTRUCTOR_STATES.length);
            for (LuiPersonRelationStateEnum state : LuiPersonRelationStateEnum.COURSE_INSTRUCTOR_STATES) {
                states.add(new LuiPersonRelationStateInfo.Builder(state).build());
            }
            return states;
        }
        if (luiPersonRelationType.equals(LuiPersonRelationTypeEnum.ADVISOR.getKey())) {
            List<LuiPersonRelationStateInfo> states = new ArrayList(LuiPersonRelationStateEnum.COURSE_INSTRUCTOR_STATES.length);
            for (LuiPersonRelationStateInfc state : LuiPersonRelationStateEnum.PROGRAM_ADVISOR_STATES) {
                states.add(new LuiPersonRelationStateInfo.Builder(state).build());
            }
            return states;
        }
        if (isStudentCourseType(luiPersonRelationType)) {
            List<LuiPersonRelationStateInfo> states = new ArrayList(LuiPersonRelationStateEnum.COURSE_STUDENT_STATES.length);
            for (LuiPersonRelationStateInfc state : LuiPersonRelationStateEnum.COURSE_STUDENT_STATES) {
                states.add(new LuiPersonRelationStateInfo.Builder(state).build());
            }
            return states;
        }
        if (isStudentProgramType(luiPersonRelationType)) {
            List<LuiPersonRelationStateInfo> states = new ArrayList(LuiPersonRelationStateEnum.PROGRAM_STUDENT_STATES.length);
            for (LuiPersonRelationStateInfc state : LuiPersonRelationStateEnum.PROGRAM_STUDENT_STATES) {
                states.add(new LuiPersonRelationStateInfo.Builder(state).build());
            }
            return states;
        }
        throw new IllegalArgumentException(luiPersonRelationType);
    }

    private boolean isInstructorType(String typeKey) {
        for (LuiPersonRelationTypeEnum type : LuiPersonRelationTypeEnum.COURSE_INSTRUCTOR_TYPES) {
            if (type.getKey().equals(typeKey)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStudentCourseType(String typeKey) {
        for (LuiPersonRelationTypeEnum type : LuiPersonRelationTypeEnum.COURSE_STUDENT_TYPES) {
            if (type.getKey().equals(typeKey)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStudentProgramType(String typeKey) {
        if (LuiPersonRelationTypeEnum.STUDENT.getKey().equals(typeKey)) {
            return true;
        }
        return false;
    }

    @Override
    public List<String> findLuiIdsRelatedToPerson(String personId,
            String luiPersonRelationType,
            String relationState,
            ContextInfo context) throws
            DoesNotExistException,
            DisabledIdentifierException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        List<String> luiIds = new ArrayList();
        for (LuiPersonRelationInfo bean : this.lprCache.values()) {
            if (!personId.equals(bean.getPersonId())) {
                continue;
            }
            if (!luiPersonRelationType.equals(bean.getType())) {
                continue;
            }
            if (!relationState.equals(bean.getState())) {
                continue;
            }

            luiIds.add(bean.getLuiId());
        }
        return luiIds;
    }

    @Override
    public List<String> findLuiPersonRelationIds(String personId, String luiId,
            ContextInfo context) throws
            DoesNotExistException,
            DisabledIdentifierException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        List<String> lprIds = new ArrayList();
        for (LuiPersonRelationInfo bean : this.lprCache.values()) {
            if (!personId.equals(bean.getPersonId())) {
                continue;
            }
            if (!luiId.equals(bean.getLuiId())) {
                continue;
            }
            lprIds.add(bean.getId());
        }
        return lprIds;
    }

    @Override
    public List<String> findLuiPersonRelationIdsForLui(String luiId,
            ContextInfo context) throws
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        List<String> lprIds = new ArrayList();
        for (LuiPersonRelationInfo bean : this.lprCache.values()) {
            if (!luiId.equals(bean.getLuiId())) {
                continue;
            }
            lprIds.add(bean.getId());
        }
        return lprIds;
    }

    @Override
    public List<String> findLuiPersonRelationIdsForPerson(String personId,
            ContextInfo context)
            throws DoesNotExistException, DisabledIdentifierException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<String> lprIds = new ArrayList();
        for (LuiPersonRelationInfo bean : this.lprCache.values()) {
            if (!personId.equals(bean.getPersonId())) {
                continue;
            }
            lprIds.add(bean.getId());
        }
        return lprIds;
    }

    @Override
    public List<LuiPersonRelationStateInfo> findLuiPersonRelationStates(
            ContextInfo context)
            throws OperationFailedException {
        List<LuiPersonRelationStateInfo> states = new ArrayList();
        for (LuiPersonRelationStateEnum state : LuiPersonRelationStateEnum.values()) {
            states.add(new LuiPersonRelationStateInfo.Builder(state).build());
        }
        return states;
    }

    // TODO: Add this method to the service interface
    private LuiPersonRelationTypeEnum getLuiPersonRelationTypeEnum(String typeKey)
            throws DoesNotExistException {
        for (LuiPersonRelationTypeEnum type : LuiPersonRelationTypeEnum.values()) {
            if (type.getKey().equals(typeKey)) {
                return type;
            }
        }
        throw new DoesNotExistException(typeKey);
    }

    // TODO: Add this method to the service interface
    private LuiPersonRelationTypeInfo getLuiPersonRelationType(String typeKey)
            throws DoesNotExistException {
        return new LuiPersonRelationTypeInfo.Builder(this.getLuiPersonRelationTypeEnum(typeKey)).build();
    }

//    @Override
//    public List<LuiPersonRelationTypeInfo> findLuiPersonRelationTypesForLuiPersonRelation(
//            String personId,
//            String luiId,
//            String relationState,
//            ContextInfo context)
//            throws DoesNotExistException, DisabledIdentifierException,
//            InvalidParameterException, MissingParameterException,
//            OperationFailedException, PermissionDeniedException {
//        // TODO: reevaluate if this method is needed -- I can see no use case for it
//        Map<String, LuiPersonRelationTypeInfo> types = new HashMap();
//        for (LuiPersonRelationInfo lpr : this.lprCache.values()) {
//            if (!lpr.getPersonId().equals(personId)) {
//                continue;
//            }
//            if (!lpr.getLuiId().equals(luiId)) {
//                continue;
//            }
//            if (!lpr.getState().equals(relationState)) {
//                continue;
//            }
//            LuiPersonRelationTypeInfo type = this.getLuiPersonRelationType(lpr.getType());
//            types.put(type.getKey(), type);
//        }
//        return new ArrayList(types.values());
//    }
    @Override
    public List<LuiPersonRelationInfo> findLuiPersonRelations(String personId,
            String luiId,
            ContextInfo context)
            throws DoesNotExistException, DisabledIdentifierException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<LuiPersonRelationInfo> lprs = new ArrayList();
        for (LuiPersonRelationInfo bean : this.lprCache.values()) {
            if (!personId.equals(bean.getPersonId())) {
                continue;
            }
            if (!luiId.equals(bean.getLuiId())) {
                continue;
            }
            lprs.add(bean);
        }
        return lprs;
    }

    @Override
    public List<LuiPersonRelationInfo> findLuiPersonRelationsByIdList(
            List<String> luiPersonRelationIdList,
            ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        List<LuiPersonRelationInfo> lprs = new ArrayList();
        for (String id : luiPersonRelationIdList) {
            LuiPersonRelationInfo bean = this.fetchLUIPersonRelation(id, context);
            lprs.add(bean);
        }
        return lprs;
    }

    @Override
    public List<LuiPersonRelationInfo> findLuiPersonRelationsForLui(String luiId,
            ContextInfo context)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        List<String> ids = this.findLuiPersonRelationIdsForLui(luiId, context);
        return this.findLuiPersonRelationsByIdList(ids, context);
    }

    @Override
    public List<LuiPersonRelationInfo> findLuiPersonRelationsForPerson(
            String personId,
            ContextInfo context)
            throws DoesNotExistException, DisabledIdentifierException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<String> ids = this.findLuiPersonRelationIdsForPerson(personId, context);
        return this.findLuiPersonRelationsByIdList(ids, context);
    }

    @Override
    public LuiPersonRelationInfo updateLuiPersonRelation(String luiPersonRelationId,
            LuiPersonRelationInfo luiPersonRelationInfo,
            ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            ReadOnlyException,
            OperationFailedException,
            PermissionDeniedException,
            VersionMismatchException {
        LuiPersonRelationInfo existing = this.lprCache.get(
                luiPersonRelationId);
        if (existing == null) {
            throw new DoesNotExistException(luiPersonRelationId);
        }
        if (!luiPersonRelationInfo.getMetaInfo().getVersionInd().equals(
                existing.getMetaInfo().getVersionInd())) {
            throw new VersionMismatchException(
                    "Updated by " + existing.getMetaInfo().getUpdateId() + " on "
                    + existing.getMetaInfo().getUpdateId() + " with version of "
                    + existing.getMetaInfo().getVersionInd());
        }
        LuiPersonRelationInfo.Builder builder = new LuiPersonRelationInfo.Builder(luiPersonRelationInfo).metaInfo(new MockHelper ().updateMeta(existing.getMetaInfo(), context));
        // update attributes in order to be different than that in luiPersonRelationInfo
        List<AttributeInfo> atts = new ArrayList<AttributeInfo>();
        for (AttributeInfo att : luiPersonRelationInfo.getAttributes()) {
            atts.add(new AttributeInfo.Builder(att).build());
        }
        builder.attributes(atts);
        LuiPersonRelationInfo copy = builder.build();
        this.lprCache.put(luiPersonRelationId, copy);
        // mirroring what was done before immutable DTO's; why returning copy of copy?
        return new LuiPersonRelationInfo.Builder(copy).build();
    }

    @Override
    public StatusInfo updateRelationState(String luiPersonRelationId,
            LuiPersonRelationStateInfo relationState,
            ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        try {
            LuiPersonRelationInfo existing = this.lprCache.get(luiPersonRelationId);
            if (existing == null) {
                throw new DoesNotExistException(luiPersonRelationId);
            }
            LuiPersonRelationInfo revised = new LuiPersonRelationInfo.Builder(existing).state(relationState.getKey()).build();
            try {
                this.updateLuiPersonRelation(luiPersonRelationId, revised, context);
            } catch (ReadOnlyException ex) {
                throw new OperationFailedException ("got an unexpected exception", ex);
            }
        } catch (VersionMismatchException ex) {
            throw new OperationFailedException("id changed between fetch and update", ex);
        }
        return new StatusInfo.Builder().success(Boolean.TRUE).build();
    }
}

