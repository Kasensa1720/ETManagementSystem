// package com.etms.server.customer;



// import java.io.Serializable;
// import java.util.List;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import com.etms.server.tuningjob.TuningJob;


// @Entity
// @Table(name = "customer")
// public class Customer implements Serializable {
//     private static final long serialVersionUID = 1L;

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "customer_id")
//     private int customerId;

//     @Column(name = "name", nullable = false, length = 100)
//     private String name;

//     @Column(name = "email", nullable = false, length = 100)
//     private String email;

//     @Column(name = "phone", nullable = false, length = 15)
//     private String phone;

//     @Column(name = "address", length = 255)
//     private String address;

//     @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private List<TuningJob> tuningJobs;

//     public Customer() {}

//     public int getCustomerId() {
//         return customerId;
//     }

//     public void setCustomerId(int customerId) {
//         this.customerId = customerId;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPhone() {
//         return phone;
//     }

//     public void setPhone(String phone) {
//         this.phone = phone;
//     }

//     public String getAddress() {
//         return address;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public List<TuningJob> getTuningJobs() {
//         return tuningJobs;
//     }

//     public void setTuningJobs(List<TuningJob> tuningJobs) {
//         this.tuningJobs = tuningJobs;
//     }

//     @Override
//     public String toString() {
//         return "Customer{" +
//                 "customerId=" + customerId +
//                 ", name='" + name + '\'' +
//                 ", email='" + email + '\'' +
//                 ", phone='" + phone + '\'' +
//                 ", address='" + address + '\'' +
//                 '}';
//     }
// }
