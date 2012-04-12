package org.motechproject.ananya.bbc.service;


import org.junit.Test;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CertificateCourseReportServiceIT extends SpringIntegrationTest {

    @Autowired
    private CertificateCourseReportService courseReportService;

    @Test
    public void shouldFetchFromReportingDBViaMyBatis(){
        List<CertificateCourseUsage> usageReports = courseReportService.getUsageReport();
        
    }

}
