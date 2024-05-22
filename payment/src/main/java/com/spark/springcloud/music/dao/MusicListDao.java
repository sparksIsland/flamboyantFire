package com.spark.springcloud.music.dao;
import com.spark.springcloud.music.entities.MusicList;

import com.spark.springcloud.music.entities.MusicListTotal;
import com.spark.springcloud.music.entities.MusicResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicListDao {
    List<MusicList> queryByName(@Param("songName") String songName, @Param("singerName") String singerName);
    Long queryByNameTotal(@Param("songName") String songName, @Param("singerName") String singerName);
}
