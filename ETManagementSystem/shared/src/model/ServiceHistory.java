package model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "service_history")
public class ServiceHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;
    
    @Column(name = "vehicle_id", nullable = false)
    private int vehicleId;
    
    @Column(name = "service_type", nullable = false)
    private String serviceType;
    
    @Column(name = "details", columnDefinition = "TEXT")
    private String details;
    
    @Column(name = "performed_by", nullable = false)
    private String performedBy;
    
    @Column(name = "service_date", nullable = false)
    private Date serviceDate;
    
    @Column(name = "tuning_job_id")
    private Integer tuningJobId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private Vehicle vehicle;
    
    @OneToOne
    @JoinColumn(name = "tuning_job_id", insertable = false, updatable = false)
    private TuningJob tuningJob;

    // Constructors
    public ServiceHistory() {}

    public ServiceHistory(int serviceId, int vehicleId, String serviceType, 
                         String details, String performedBy, Date serviceDate, 
                         Integer tuningJobId) {
        this.serviceId = serviceId;
        this.vehicleId = vehicleId;
        this.serviceType = serviceType;
        this.details = details;
        this.performedBy = performedBy;
        this.serviceDate = serviceDate;
        this.tuningJobId = tuningJobId;
    }

    // Getters and Setters
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Integer getTuningJobId() {
        return tuningJobId;
    }

    public void setTuningJobId(Integer tuningJobId) {
        this.tuningJobId = tuningJobId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public TuningJob getTuningJob() {
        return tuningJob;
    }

    public void setTuningJob(TuningJob tuningJob) {
        this.tuningJob = tuningJob;
    }
}