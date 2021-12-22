package com.fivepoints.spring.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_details")
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"createdAt", "updatedAt", "user"})
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;

    @NonNull
    @Column(name = "age")
    private int age;
    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;
    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @NonNull
    @Column(name = "address1")
    private String address1;
    @NonNull
    @Column(name = "address2")
    private String address2;
    @NonNull
    @Column(name = "street")
    private String street;
    @NonNull
    @Column(name = "city")
    private String city;
    @NonNull
    @Column(name = "state")
    private String state;
    @NonNull
    @Column(name = "country")
    private String country;
    @NonNull
    @Column(name = "zip_code")
    private String zipCode;
    @NonNull
    @Column(name = "birth_date")
    private Date birthDate;
    @NonNull
    @Column(name = "github_profile_link")
    private String githubProfileLink;
    @NonNull
    @Column(name = "linkedin_profile_link")
    private String linkedinProfileLink;

    // OneToOne Relations
    @OneToOne(mappedBy = "details")
    private User user;

    @Setter(value = AccessLevel.NONE)
    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Setter(value = AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
