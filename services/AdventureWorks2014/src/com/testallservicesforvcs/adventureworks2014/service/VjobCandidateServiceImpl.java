/*Copyright (c) 2016-2017 vcstest4.com All Rights Reserved.
 This software is the confidential and proprietary information of vcstest4.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcstest4.com*/
package com.testallservicesforvcs.adventureworks2014.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.testallservicesforvcs.adventureworks2014.VjobCandidate;
import com.testallservicesforvcs.adventureworks2014.VjobCandidateId;


/**
 * ServiceImpl object for domain model class VjobCandidate.
 *
 * @see VjobCandidate
 */
@Service("AdventureWorks2014.VjobCandidateService")
public class VjobCandidateServiceImpl implements VjobCandidateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VjobCandidateServiceImpl.class);


    @Autowired
    @Qualifier("AdventureWorks2014.VjobCandidateDao")
    private WMGenericDao<VjobCandidate, VjobCandidateId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<VjobCandidate, VjobCandidateId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "AdventureWorks2014TransactionManager")
    @Override
	public VjobCandidate create(VjobCandidate vjobCandidate) {
        LOGGER.debug("Creating a new VjobCandidate with information: {}", vjobCandidate);
        VjobCandidate vjobCandidateCreated = this.wmGenericDao.create(vjobCandidate);
        return vjobCandidateCreated;
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public VjobCandidate getById(VjobCandidateId vjobcandidateId) throws EntityNotFoundException {
        LOGGER.debug("Finding VjobCandidate by id: {}", vjobcandidateId);
        VjobCandidate vjobCandidate = this.wmGenericDao.findById(vjobcandidateId);
        if (vjobCandidate == null){
            LOGGER.debug("No VjobCandidate found with id: {}", vjobcandidateId);
            throw new EntityNotFoundException(String.valueOf(vjobcandidateId));
        }
        return vjobCandidate;
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public VjobCandidate findById(VjobCandidateId vjobcandidateId) {
        LOGGER.debug("Finding VjobCandidate by id: {}", vjobcandidateId);
        return this.wmGenericDao.findById(vjobcandidateId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "AdventureWorks2014TransactionManager")
	@Override
	public VjobCandidate update(VjobCandidate vjobCandidate) throws EntityNotFoundException {
        LOGGER.debug("Updating VjobCandidate with information: {}", vjobCandidate);
        this.wmGenericDao.update(vjobCandidate);

        VjobCandidateId vjobcandidateId = new VjobCandidateId();
        vjobcandidateId.setJobCandidateId(vjobCandidate.getJobCandidateId());
        vjobcandidateId.setBusinessEntityId(vjobCandidate.getBusinessEntityId());
        vjobcandidateId.setName_prefix(vjobCandidate.getName_prefix());
        vjobcandidateId.setName_first(vjobCandidate.getName_first());
        vjobcandidateId.setName_middle(vjobCandidate.getName_middle());
        vjobcandidateId.setName_last(vjobCandidate.getName_last());
        vjobcandidateId.setName_suffix(vjobCandidate.getName_suffix());
        vjobcandidateId.setSkills(vjobCandidate.getSkills());
        vjobcandidateId.setAddr_type(vjobCandidate.getAddr_type());
        vjobcandidateId.setAddr_loc_countryRegion(vjobCandidate.getAddr_loc_countryRegion());
        vjobcandidateId.setAddr_loc_state(vjobCandidate.getAddr_loc_state());
        vjobcandidateId.setAddr_loc_city(vjobCandidate.getAddr_loc_city());
        vjobcandidateId.setAddr_postalCode(vjobCandidate.getAddr_postalCode());
        vjobcandidateId.setEmail(vjobCandidate.getEmail());
        vjobcandidateId.setWebSite(vjobCandidate.getWebSite());
        vjobcandidateId.setModifiedDate(vjobCandidate.getModifiedDate());

        return this.wmGenericDao.findById(vjobcandidateId);
    }

    @Transactional(value = "AdventureWorks2014TransactionManager")
	@Override
	public VjobCandidate delete(VjobCandidateId vjobcandidateId) throws EntityNotFoundException {
        LOGGER.debug("Deleting VjobCandidate with id: {}", vjobcandidateId);
        VjobCandidate deleted = this.wmGenericDao.findById(vjobcandidateId);
        if (deleted == null) {
            LOGGER.debug("No VjobCandidate found with id: {}", vjobcandidateId);
            throw new EntityNotFoundException(String.valueOf(vjobcandidateId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public Page<VjobCandidate> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all VjobCandidates");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Page<VjobCandidate> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all VjobCandidates");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service AdventureWorks2014 for table VjobCandidate to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

