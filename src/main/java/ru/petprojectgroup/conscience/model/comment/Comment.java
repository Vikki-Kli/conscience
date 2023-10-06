package ru.petprojectgroup.conscience.model.comment;

import jakarta.persistence.*;
import lombok.*;
import ru.petprojectgroup.conscience.model.post.Post;
import ru.petprojectgroup.conscience.model.user.User;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="comment")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Column(name="creation_date")
    private Instant creationDate;
    @Column(name="photo")
    private String photoUrl;
}
