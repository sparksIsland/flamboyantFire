package com.music.service.impl;

import com.music.entity.MusicList;
import com.music.entity.SongNameObj;
import com.music.mapper.MusicListDao;
import com.music.service.MusicListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Service
@Slf4j
public class MusicListServiceImpl implements MusicListService {
    @Resource
    private MusicListDao musicListDao;

    @Override
    public List<MusicList> queryByName(SongNameObj songNameObj) {
        String songName = songNameObj.getSongName();
        String singerName = songNameObj.getSingerName();
        Long currentPage = songNameObj.getCurrentPage();
        Long pageSize = songNameObj.getPageSize();
        Long currentNum = (currentPage - 1) * pageSize;
        return musicListDao.queryByName(songName, singerName, currentNum, pageSize);
    }

    @Override
    public Long queryByNameTotal(SongNameObj songNameObj) {
        String songName = songNameObj.getSongName();
        String singerName = songNameObj.getSingerName();
        return musicListDao.queryByNameTotal(songName, singerName);
    }
}
