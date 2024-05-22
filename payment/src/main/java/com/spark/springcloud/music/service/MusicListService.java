package com.spark.springcloud.music.service;

import com.spark.springcloud.music.entities.MusicListTotal;
import com.spark.springcloud.music.entities.MusicResp;
import com.spark.springcloud.music.entities.SongNameObj;
import com.spark.springcloud.music.entities.MusicList;

import java.util.List;

public interface MusicListService {
    List<MusicList> queryByName(SongNameObj SongNameObj);
    Long queryByNameTotal(SongNameObj SongNameObj);
}
