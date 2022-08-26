package com.tvn.api_for_1c_v2.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Handler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String version;

    @OneToMany(mappedBy = "handler", fetch = FetchType.LAZY)
    private List<Function> functions;

    public Handler(String name, String version) {
        this.name = name;
        this.version = version;
    }
}
