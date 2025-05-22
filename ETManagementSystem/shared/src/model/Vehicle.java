package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {
    
    public enum EngineType {
        TURBO_PETROL, NA_PETROL, SUPERCHARGED_PETROL,
        NA_DIESEL, TURBO_DIESEL, SUPERCHARGED_DIESEL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicleId;
    
    @Column(name = "customer_id", nullable = false)
    private int customerId;
    
    @Column(name = "make", nullable = false)
    private String make;
    
    @Column(name = "model", nullable = false)
    private String model;
    
    @Column(name = "year", nullable = false)
    private int year;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type", nullable = false)
    private EngineType engineType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
    
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceHistory> serviceHistory = new ArrayList<>();
    
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TuningJob> tuningJobs = new ArrayList<>();

    // Constructors
    public Vehicle() {}

    public Vehicle(int vehicleId, int customerId, String make, 
                  String model, int year, EngineType engineType) {
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
    }

    // Getters and Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ServiceHistory> getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(List<ServiceHistory> serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public List<TuningJob> getTuningJobs() {
        return tuningJobs;
    }

    public void setTuningJobs(List<TuningJob> tuningJobs) {
        this.tuningJobs = tuningJobs;
    }

    public void addServiceHistory(ServiceHistory history) {
        serviceHistory.add(history);
        history.setVehicle(this);
    }

    public void addTuningJob(TuningJob tuningJob) {
        tuningJobs.add(tuningJob);
        tuningJob.setTuningId(this.getVehicleId());
    }
}