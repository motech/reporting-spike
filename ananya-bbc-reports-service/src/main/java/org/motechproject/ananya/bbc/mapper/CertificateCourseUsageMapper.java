package org.motechproject.ananya.bbc.mapper;

import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.domain.Date;

import java.util.List;

public interface CertificateCourseUsageMapper {

    List<CertificateCourseUsage> getAll();

    Date getCourseStartDate(Long msisdn);

    Date getCourseEndDate(Long msisdn);

    Integer getNumChaptersCompleted(Long msisdn);

    Integer getNumLessonsCompleted(Long msisdn);

    String getCourseCompleted(Long msisdn);
}
