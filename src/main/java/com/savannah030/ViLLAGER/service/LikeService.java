package com.savannah030.ViLLAGER.service;

public class LikeService {
    interface LikeDto{

    }

    /*
    public Boolean pushLikeButton(LikeDto LikeDto) {
        postLikeRepository.exist(postLikeDto.getStudent().getId(), postLikeDto.getBoardId())
                .ifPresentOrElse(
                        postLike -> postLikeRepository.deleteById(postLike.getId()),
                        () -> {
                            Board board = getBoard(postLikeDto);
                            postLikeRepository.save(new PostLike(postLikeDto.getStudent(), board, LocalDateTime.now()));
                        });

        return true;
    }

     */
}
