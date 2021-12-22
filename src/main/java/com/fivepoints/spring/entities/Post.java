package com.fivepoints.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"createdAt", "updatedAt", "user", "tags", "comments"})
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;

    @NonNull
    @Column(name = "title")
    private String title;
    @NonNull
    @Column(name = "description")
    @Lob
    private String description;
    @NonNull
    @Column(name = "published")
    private boolean published;

    // OneToMany Relations
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy="post")
    private List<Comment> comments;

    // ManyToMany Relations
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "post_tags",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();

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
