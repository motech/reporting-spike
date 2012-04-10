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
            Long msisdn = Long.valueOf(usage.getMsisdn());
            usage.setCourseStartDate(certificateCourseUsageMapper.getCourseStartDate(msisdn).getDateTimeEquivalent());
            usage.setCourseEndDate(certificateCourseUsageMapper.getCourseEndDate(msisdn).getDateTimeEquivalent());
            usage.setNumChaptersCompleted(certificateCourseUsageMapper.getNumChaptersCompleted(msisdn));
            usage.setNumLessonsCompleted(certificateCourseUsageMapper.getNumLessonsCompleted(msisdn));
            usage.setCourseCompleted(certificateCourseUsageMapper.getCourseCompleted(msisdn));
        }
        return usages;
    }
}
