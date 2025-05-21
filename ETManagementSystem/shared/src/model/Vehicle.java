// package com.etms.server.vehicle;

// import javax.persistence.Column;
// import javax.persistence.Convert;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

// import com.etms.client.Vehicle.EngineTypeConverter;

// @Entity
// @Table(name = "vehicle")
// public class Vehicle {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "vehicle_id")
//     private int vehicleId;

//     @Column(name = "customer_id", nullable = true)
//     private Integer customerId;

//     @Column(name = "make", length = 100)
//     private String make;

//     @Column(name = "model", length = 100)
//     private String model;

//     @Column(name = "year")
//     private int year;

//     @Convert(converter = EngineTypeConverter.class)
//     @Column(name = "engine_type")
//     private EngineType engineType;

//     public Vehicle() {}

//     public Vehicle(int vehicleId, Integer customerId, String make, String model, int year, EngineType engineType) {
//         this.vehicleId = vehicleId;
//         this.customerId = customerId;
//         this.make = make;
//         this.model = model;
//         this.year = year;
//         this.engineType = engineType;
//     }

//     // Getters and setters

//     public int getVehicleId() {
//         return vehicleId;
//     }

//     public void setVehicleId(int vehicleId) {
//         this.vehicleId = vehicleId;
//     }

//     public Integer getCustomerId() {
//         return customerId;
//     }

//     public void setCustomerId(Integer customerId) {
//         this.customerId = customerId;
//     }

//     public String getMake() {
//         return make;
//     }

//     public void setMake(String make) {
//         this.make = make;
//     }

//     public String getModel() {
//         return model;
//     }

//     public void setModel(String model) {
//         this.model = model;
//     }

//     public int getYear() {
//         return year;
//     }

//     public void setYear(int year) {
//         this.year = year;
//     }

//     public EngineType getEngineType() {
//         return engineType;
//     }

//     public void setEngineType(EngineType engineType) {
//         this.engineType = engineType;
//     }

//     @Override
//     public String toString() {
//         return "Vehicle{" +
//                 "vehicleId=" + vehicleId +
//                 ", customerId=" + customerId +
//                 ", make='" + make + '\'' +
//                 ", model='" + model + '\'' +
//                 ", year=" + year +
//                 ", engineType=" + engineType +
//                 '}';
//     }

//     // Enum
//     public enum EngineType {
//         NA("NA"),
//         TURBO_PETROL("Turbo"),
//         NA_PETROL("NA"),
//         SUPERCHARGED_PETROL("Supercharged"),
//         NA_DIESEL("NA"),
//         TURBO_DIESEL("Turbo"),
//         SUPERCHARGED_DIESEL("Supercharged");

//         private final String dbValue;

//         EngineType(String dbValue) {
//             this.dbValue = dbValue;
//         }

//         public String getDbValue() {
//             return dbValue;
//         }

//         public static EngineType fromDbValue(String dbValue) {
//             for (EngineType type : values()) {
//                 if (type.dbValue.equalsIgnoreCase(dbValue)) {
//                     return type;
//                 }
//             }
//             throw new IllegalArgumentException("No enum constant with DB value: " + dbValue);
//         }
//     }
// }
