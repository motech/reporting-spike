package org.motechproject.ananya.bbc.mapper;

import org.apache.ibatis.annotations.Param;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;

import java.util.List;

public interface CertificateCourseUsageMapper {

    Integer getCount(@Param("startTimeId") int startTimeId,
                     @Param("endTimeId") int endTimeId);

    List<CertificateCourseUsage> getAll(@Param("limit") int limit,
                                        @Param("offset") int offset,
                                        @Param("startTimeId") int startTimeId,
                                        @Param("endTimeId") int endTimeId);

    Integer getTimeIdFor(@Param("day") int Day,
                         @Param("month") int month,
                         @Param("year") int year);
}
