package ru.petprojectgroup.conscience.model.post.reaction;

import jakarta.persistence.*;
import lombok.*;
import ru.petprojectgroup.conscience.model.post.Post;
import ru.petprojectgroup.conscience.model.user.User;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Enumerated(EnumType.STRING)
    @Column(name = "reaction")
    private ReactionType reactionType;
}

