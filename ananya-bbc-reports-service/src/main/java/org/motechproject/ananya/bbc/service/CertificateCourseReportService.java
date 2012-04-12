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

    public List<CertificateCourseUsage> getUsageReport(int from, int to) {
        return certificateCourseUsageMapper.getAll(to - from, from);
    }

    public Integer getCount() {
        return certificateCourseUsageMapper.getCount();
    }
}
