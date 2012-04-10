package org.motechproject.ananya.bbc.service;

import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.mapper.CertificateCourseUsageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateCourseReportService {

    @Autowired
    private CertificateCourseUsageMapper certificateCourseUsageMapper;

    public List<CertificateCourseUsage> getUsageReport() {
        List<CertificateCourseUsage> usages = certificateCourseUsageMapper.getAll();
        for(CertificateCourseUsage usage : usages){
            usage.setCourseStartDate(certificateCourseUsageMapper.getCourseStartDate(Long.valueOf(usage.getMsisdn())).getDateTimeEquivalent());
            usage.setCourseEndDate(certificateCourseUsageMapper.getCourseEndDate(Long.valueOf(usage.getMsisdn())).getDateTimeEquivalent());
        }
        return usages;
    }
}
