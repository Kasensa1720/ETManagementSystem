package com.etms.server.tuningjob;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tuning_job")
public class TuningJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tuning_id")
    private int tuningId;

    @Column(name = "vehicle_id", nullable = true)
    private Integer vehicleId;

    @Column(name = "ecu_file_used", length = 255)
    private String ecuFileUsed;

    @Column(name = "before_hp")
    private int beforeHp;

    @Column(name = "after_hp")
    private int afterHp;

    @Column(name = "before_torque")
    private int beforeTorque;

    @Column(name = "after_torque")
    private int afterTorque;

    @Column(name = "fuel_efficiency_change", precision = 10, scale = 2)
    private BigDecimal fuelEfficiencyChange;

    @Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    // Default constructor
    public TuningJob() {}

    // Full constructor
    public TuningJob(int tuningId, Integer vehicleId, String ecuFileUsed, int beforeHp, int afterHp,
                     int beforeTorque, int afterTorque, BigDecimal fuelEfficiencyChange,
                     BigDecimal cost, Timestamp date) {
        this.tuningId = tuningId;
        this.vehicleId = vehicleId;
        this.ecuFileUsed = ecuFileUsed;
        this.beforeHp = beforeHp;
        this.afterHp = afterHp;
        this.beforeTorque = beforeTorque;
        this.afterTorque = afterTorque;
        this.fuelEfficiencyChange = fuelEfficiencyChange;
        this.cost = cost;
        this.date = date;
    }

    // Getters and Setters
    public int getTuningId() {
        return tuningId;
    }

    public void setTuningId(int tuningId) {
        this.tuningId = tuningId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getEcuFileUsed() {
        return ecuFileUsed;
    }

    public void setEcuFileUsed(String ecuFileUsed) {
        this.ecuFileUsed = ecuFileUsed;
    }

    public int getBeforeHp() {
        return beforeHp;
    }

    public void setBeforeHp(int beforeHp) {
        this.beforeHp = beforeHp;
    }

    public int getAfterHp() {
        return afterHp;
    }

    public void setAfterHp(int afterHp) {
        this.afterHp = afterHp;
    }

    public int getBeforeTorque() {
        return beforeTorque;
    }

    public void setBeforeTorque(int beforeTorque) {
        this.beforeTorque = beforeTorque;
    }

    public int getAfterTorque() {
        return afterTorque;
    }

    public void setAfterTorque(int afterTorque) {
        this.afterTorque = afterTorque;
    }

    public BigDecimal getFuelEfficiencyChange() {
        return fuelEfficiencyChange;
    }

    public void setFuelEfficiencyChange(BigDecimal fuelEfficiencyChange) {
        this.fuelEfficiencyChange = fuelEfficiencyChange;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TuningJob{" +
                "tuningId=" + tuningId +
                ", vehicleId=" + vehicleId +
                ", ecuFileUsed='" + ecuFileUsed + '\'' +
                ", beforeHp=" + beforeHp +
                ", afterHp=" + afterHp +
                ", beforeTorque=" + beforeTorque +
                ", afterTorque=" + afterTorque +
                ", fuelEfficiencyChange=" + fuelEfficiencyChange +
                ", cost=" + cost +
                ", date=" + date +
                '}';
    }
}
