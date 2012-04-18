package org.motechproject.ananya.bbc.service;

import org.joda.time.DateTime;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.mapper.CertificateCourseUsageMapper;
import org.motechproject.ananya.bbc.request.UsageReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateCourseReportService {

    @Autowired
    private CertificateCourseUsageMapper certificateCourseUsageMapper;

    public List<CertificateCourseUsage> getUsageReport(UsageReportRequest request) {
        return certificateCourseUsageMapper.getAll(request.getLimit(),
                request.getOffset(), request.getStartDate(), request.getEndDate());
    }

    public Integer getCount(DateTime startDate, DateTime endDate) {
        return certificateCourseUsageMapper.getCount(startDate, endDate);
    }
}
