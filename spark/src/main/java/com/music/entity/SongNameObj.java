package com.music.entity;


import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
public class SongNameObj implements Serializable {
    private String songName;
    private String singerName;
    private Long currentPage = 1L;
    private Long pageSize = 1L;
}
