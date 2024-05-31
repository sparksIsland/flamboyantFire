package com.music.mapper;

import com.music.entity.MusicList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicListMapper {
    List<MusicList> queryByName(@Param("songName") String songName, @Param("singerName") String singerName, @Param("currentNum") Long currentNum, @Param("pageSize") Long pageSize);

    Long queryByNameTotal(@Param("songName") String songName, @Param("singerName") String singerName);
}
