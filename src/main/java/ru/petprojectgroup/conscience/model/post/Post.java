package ru.petprojectgroup.conscience.model.post;

import jakarta.persistence.*;
import lombok.*;
import ru.petprojectgroup.conscience.model.post.reaction.Reaction;
import ru.petprojectgroup.conscience.model.user.User;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="post")
    private String postContent;
    @Column(name="creation_date")
    private Instant creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="photo")
    private String photoUrl;
    @OneToMany
    @JoinColumn(name = "post_id")
    private Set<Reaction> reactions = new HashSet<>();
}
