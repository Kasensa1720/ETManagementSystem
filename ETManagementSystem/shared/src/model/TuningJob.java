package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "tuning_jobs")
public class TuningJob implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tuning_id")
    private int tuningId;
    
    @Column(name = "vehicle_id", nullable = false)
    private int vehicleId;
    
    @Column(name = "ecu_file", nullable = false)
    private String ecuFileUsed;
    
    @Column(name = "before_hp", nullable = false)
    private int beforeHp;
    
    @Column(name = "after_hp", nullable = false)
    private int afterHp;
    
    @Column(name = "before_torque", nullable = false)
    private int beforeTorque;
    
    @Column(name = "after_torque", nullable = false)
    private int afterTorque;
    
    @Column(name = "fuel_efficiency_change", precision = 5, scale = 2)
    private BigDecimal fuelEfficiencyChange;
    
    @Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;
    
    @Column(name = "tuning_date")
    private Date tuningDate;
    
    @OneToOne(mappedBy = "tuningJob")
    private ServiceHistory serviceHistory;

    // Add this field to TuningJob class:
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
private Vehicle vehicle;

// Add getter and setter for vehicle:
public Vehicle getVehicle() {
    return vehicle;
}

public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
}

    // Constructors
    public TuningJob() {}

    public TuningJob(int tuningId, int vehicleId, String ecuFileUsed, 
                    int beforeHp, int afterHp, int beforeTorque, 
                    int afterTorque, BigDecimal fuelEfficiencyChange, 
                    BigDecimal cost, Date tuningDate) {
        this.tuningId = tuningId;
        this.vehicleId = vehicleId;
        this.ecuFileUsed = ecuFileUsed;
        this.beforeHp = beforeHp;
        this.afterHp = afterHp;
        this.beforeTorque = beforeTorque;
        this.afterTorque = afterTorque;
        this.fuelEfficiencyChange = fuelEfficiencyChange;
        this.cost = cost;
        this.tuningDate = tuningDate;
    }

    // Getters and Setters
    public int getTuningId() {
        return tuningId;
    }

    public void setTuningId(int tuningId) {
        this.tuningId = tuningId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
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

    public Date getTuningDate() {
        return tuningDate;
    }

    public void setTuningDate(Date tuningDate) {
        this.tuningDate = tuningDate;
    }

    public ServiceHistory getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
    }
}