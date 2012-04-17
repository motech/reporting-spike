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

    private Integer timeIdForStartDate;
    private Integer timeIdForEndDate ;

    public List<CertificateCourseUsage> getUsageReport(DateTime startDate, DateTime endDate, int from, int to) {
        setDateRangeForUsageReport(startDate, endDate);
        return certificateCourseUsageMapper.getAll(to - from, from, timeIdForStartDate, timeIdForEndDate);
    }

    public Integer getCount(DateTime startDate, DateTime endDate) {
        setDateRangeForUsageReport(startDate,endDate);
        return certificateCourseUsageMapper.getCount(timeIdForStartDate,timeIdForEndDate);
    }

    private void setDateRangeForUsageReport(DateTime startDate, DateTime endDate) {
        int startDay = startDate.getDayOfYear();
        int startMonth = startDate.getMonthOfYear();
        int startYear = startDate.getYear();
        int endDay = endDate.getDayOfYear();
        int endMonth = endDate.getMonthOfYear();
        int endYear = endDate.getYear();

        timeIdForStartDate = certificateCourseUsageMapper.getTimeIdFor(startDay, startMonth, startYear);
        timeIdForEndDate = certificateCourseUsageMapper.getTimeIdFor(endDay, endMonth, endYear);
    }
}
