package com.tvn.api_for_1c_v2.persistence.model;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Function {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private Handler handler;

    public Function(String name, String code, Handler handler) {
        this.name = name;
        this.code = code;
        this.handler = handler;
    }
}
