package com.example.salles.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreationTimestamp
    private LocalDate createDate;

    private Double price;

    private Boolean status = false;

    @ElementCollection
    private List<PurchaseItem> productIds;

    @ElementCollection
    private List<ReturnProduct> returnProducts;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "purchases")
//public class Purchase {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//    @CreationTimestamp
//    private LocalDate createDate;
//
//    private Double price;
//
//    private Boolean status = false;
//
//    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
//    private List<Products> products = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
//    private List<Products> returnProducts;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    private Company company;
//}

