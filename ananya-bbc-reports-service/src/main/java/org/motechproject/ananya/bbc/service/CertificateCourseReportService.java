package org.motechproject.ananya.bbc.service;

import org.joda.time.DateTime;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.mapper.CertificateCourseUsageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateCourseReportService {

    @Autowired
    private CertificateCourseUsageMapper certificateCourseUsageMapper;

    public List<CertificateCourseUsage> getUsageReport(DateTime startDate, DateTime endDate, int from, int to) {
        int startDay = startDate.getDayOfYear();
        int startMonth = startDate.getMonthOfYear();
        int startYear = startDate.getYear();
        int endDay = endDate.getDayOfYear();
        int endMonth = endDate.getMonthOfYear();
        int endYear = endDate.getYear();

        Integer timeIdForStartDate = certificateCourseUsageMapper.getTimeIdFor(startDay, startMonth, startYear);
        Integer timeIdForEndDate = certificateCourseUsageMapper.getTimeIdFor(endDay, endMonth, endYear);

        return certificateCourseUsageMapper.getAll(to - from, from, timeIdForStartDate, timeIdForEndDate);
    }

    public Integer getCount() {
        return certificateCourseUsageMapper.getCount();
    }
}
