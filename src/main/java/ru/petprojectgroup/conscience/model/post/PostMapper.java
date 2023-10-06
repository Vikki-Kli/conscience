package ru.petprojectgroup.conscience.model.post;

import ru.petprojectgroup.conscience.model.post.reaction.ReactionType;

public class PostMapper {
    public static Post dtoToPojo(PostDtoIn dto) {
        Post pojo = new Post();
        pojo.setPostContent(dto.getPostContent());
        pojo.setPhotoUrl(dto.getPhotoUrl());
        return pojo;
    }

    public static PostDtoOut pojoToDto(Post pojo) {
        PostDtoOut dto = new PostDtoOut();
        dto.setPostContent(pojo.getPostContent());
        dto.setUserId(pojo.getUser().getId());
        dto.setCreationDate(pojo.getCreationDate());
        dto.setPhotoUrl(pojo.getPhotoUrl());
        dto.setAmnesties(pojo.getReactions().stream().filter(s -> s.getReactionType() == ReactionType.AMNESTY).count());
        dto.setBlames(pojo.getReactions().stream().filter(s -> s.getReactionType() == ReactionType.BLAME).count());
        return dto;
    }
}
