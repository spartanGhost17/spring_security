package com.learning.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name="native", strategy = "native")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)//.AUTO)
    //private int id;
    //private String email;
    //private String pwd;
    //private String role;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;

    private String name;

    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;

    /**
     *  the value of the password field will be set when JSON is deserialized, but the field won't be included in the JSON output when serializing the Java object.
     *  stops you from stupidly sharing your password with thieves
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//dive more into this
    private String pwd;

    private String role;

    @Column(name = "create_dt")
    private Date create_date;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch=FetchType.EAGER)
    private Set<Authority> authorities;
}
