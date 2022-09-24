package com.tvn.api_for_1c_v2.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String hardwareHashCode;
    private String licenseKey;
    private Date keyRegistrationDate;
    private Date keyExpirationDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "client_handler", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name ="handler_id"))
    private List<Handler> availableHandlers;

    public Client(String name, String hardwareHashCode, String licenseKey, Date keyRegistrationDate, Date keyExpirationDate, List<Handler> availableHandlers) {
        this.name = name;
        this.hardwareHashCode = hardwareHashCode;
        this.licenseKey = licenseKey;
        this.keyRegistrationDate = keyRegistrationDate;
        this.keyExpirationDate = keyExpirationDate;
        this.availableHandlers = availableHandlers;
    }

    public Client(String name, String hardwareHashCode, String licenseKey, Date keyRegistrationDate, Date keyExpirationDate) {
        this.name = name;
        this.hardwareHashCode = hardwareHashCode;
        this.licenseKey = licenseKey;
        this.keyRegistrationDate = keyRegistrationDate;
        this.keyExpirationDate = keyExpirationDate;
    }
}
