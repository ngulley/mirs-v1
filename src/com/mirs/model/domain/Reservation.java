package com.mirs.model.domain;

import java.util.Objects;
import java.util.Date;

public class Reservation {
    private static final long serialVersionUID = 7661657471233633935L;
    private Integer id;
    private Integer customerId;
    private String customerName;
    private Integer employeeId;
    private String employeeName;
    private Integer branchId;
    private String branchName;
    private Integer instrumentId;
    private String instrumentName;
    private Date startDate;
    private Date endDate;

    public Reservation(Integer id, Integer customerId, String customerName, Integer employeeId, String employeeName, Integer branchId, String branchName, Integer instrumentId, String instrumentName, Date startDate, Date endDate) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.branchId = branchId;
        this.branchName = branchName;
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() { return employeeName; }

    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return Objects.equals(id, reservation.id) && Objects.equals(customerId, reservation.customerId) && Objects.equals(customerName, reservation.customerName) && Objects.equals(employeeId, reservation.employeeId)  && Objects.equals(employeeName, reservation.employeeName) && Objects.equals(branchId, reservation.branchId) && Objects.equals(branchName, reservation.branchName) && Objects.equals(instrumentId, reservation.instrumentId) && Objects.equals(instrumentName, reservation.instrumentName) && Objects.equals(startDate, reservation.startDate) && Objects.equals(endDate, reservation.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, customerName, employeeId, employeeName, branchId, branchName, instrumentId, instrumentName, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", branchId=" + branchId +
                ", branchName=" + branchName +
                ", instrumentId=" + instrumentId +
                ", instrumentName=" + instrumentName +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
