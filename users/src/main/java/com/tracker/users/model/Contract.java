package com.tracker.users.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date startDate;
    private Date endDate;
    private Long salary;
    private Currency salaryCurrency;
    private JobType jobType;
    private int paidPersonalDays;
    private long reviewRecurrence; //in millis

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contract_benefit", joinColumns = @JoinColumn(name = "contract_id"), inverseJoinColumns = @JoinColumn(name = "benefit_id"))
    @Fetch(FetchMode.JOIN)
    private Set<Benefit> benefits = new HashSet<>();
}
