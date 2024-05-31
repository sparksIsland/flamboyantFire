package com.music.service.impl;

import com.music.entity.MusicList;
import com.music.entity.SongNameObj;
import com.music.mapper.MusicListMapper;
import com.music.service.MusicListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用来操作音乐列表
 * @author zhangdeng
 */
@Service
@Slf4j
public class MusicListServiceImpl implements MusicListService {
    @Resource
    private MusicListMapper musicListMapper;

    @Override
    public List<MusicList> queryByName(SongNameObj songNameObj) {
        String songName = songNameObj.getSongName();
        String singerName = songNameObj.getSingerName();
        Long currentPage = songNameObj.getCurrentPage();
        Long pageSize = songNameObj.getPageSize();
        Long currentNum = (currentPage - 1) * pageSize;
        return musicListMapper.queryByName(songName, singerName, currentNum, pageSize);
    }

    @Override
    public Long queryByNameTotal(SongNameObj songNameObj) {
        String songName = songNameObj.getSongName();
        String singerName = songNameObj.getSingerName();
        return musicListMapper.queryByNameTotal(songName, singerName);
    }
}
