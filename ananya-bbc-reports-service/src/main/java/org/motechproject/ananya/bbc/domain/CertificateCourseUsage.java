package org.motechproject.ananya.bbc.domain;

import java.io.Serializable;

public class CertificateCourseUsage implements Serializable{

    private String msisdn;
    private String name;
    private String district;
    private String block;
    private String panchayat;

    private String courseStartDate;
    private String courseEndDate;
    private Integer numChaptersCompleted;
    private Integer numLessonsCompleted;
    private String courseCompleted;

    private String smsSent;
    private String smsReferenceNumber;

    public String getSmsSent() {
        return smsSent;
    }

    public void setSmsSent(String smsSent) {
        this.smsSent = smsSent;
    }

    public String getSmsReferenceNumber() {
        return smsReferenceNumber;
    }

    public void setSmsReferenceNumber(String smsReferenceNumber) {
        this.smsReferenceNumber = smsReferenceNumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getPanchayat() {
        return panchayat;
    }

    public void setPanchayat(String panchayat) {
        this.panchayat = panchayat;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        setBlock("Hello");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public Integer getNumChaptersCompleted() {
        return numChaptersCompleted;
    }

    public void setNumChaptersCompleted(Integer numChaptersCompleted) {
        this.numChaptersCompleted = numChaptersCompleted;
    }

    public Integer getNumLessonsCompleted() {
        return numLessonsCompleted;
    }

    public void setNumLessonsCompleted(Integer numLessonsCompleted) {
        this.numLessonsCompleted = numLessonsCompleted;
    }

    public void setCourseCompleted(String courseCompleted) {
        this.courseCompleted = courseCompleted;
    }
}
