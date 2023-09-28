package ru.petrojectgroup.conscience.model.post;

public class PostMapper {
    public static Post dtoToPojo(PostDto dto) {
        Post pojo = new Post();
        pojo.setPostContent(dto.getPostContent());
        pojo.setCreationDate(dto.getCreationDate());
        pojo.setPhotoUrl(dto.getPhotoUrl());
        return pojo;
    }

    public static PostDto pojoToDto(Post pojo) {
        PostDto dto = new PostDto();
        dto.setPostContent(pojo.getPostContent());
        dto.setCreationDate(pojo.getCreationDate());
        dto.setPhotoUrl(pojo.getPhotoUrl());
        dto.setUserId(pojo.getUser().getId());
        return dto;
    }
}
