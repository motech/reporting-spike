package org.motechproject.ananya.bbc.service;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.mapper.CertificateCourseUsageMapper;
import org.motechproject.ananya.bbc.request.UsageReportRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CertificateCourseUsageMapperIT extends SpringIntegrationTest {

    @Autowired
    private CertificateCourseUsageMapper certificateCourseUsageMapper;

    @Test
    public void shouldFetchTheReportData() {
        List<CertificateCourseUsage> all = certificateCourseUsageMapper.getAll(new UsageReportRequest(new DateTime(2010, 1, 1, 0, 0), new DateTime(2011, 1, 1, 0, 0), 5, 10, "district", "block", "village", "district", "asc", null, null));

    }
}
