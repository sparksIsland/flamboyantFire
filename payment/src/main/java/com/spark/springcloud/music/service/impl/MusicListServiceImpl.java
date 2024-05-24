package com.spark.springcloud.music.service.impl;
import com.spark.springcloud.music.dao.MusicListDao;
import com.spark.springcloud.music.entities.MusicList;
import com.spark.springcloud.music.entities.MusicListTotal;
import com.spark.springcloud.music.entities.MusicResp;
import com.spark.springcloud.music.entities.SongNameObj;

import com.spark.springcloud.music.service.MusicListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MusicListServiceImpl implements MusicListService {
    @Resource
//    @Autowired
    private MusicListDao MusicListDao;
    @Override
    public List<MusicList> queryByName(SongNameObj songNameObj) {
        String songName = songNameObj.songName;
        String singerName = songNameObj.singerName;
        Long currentPage = songNameObj.currentPage;
        Long pageSize = songNameObj.pageSize;
        Long currentNum = (currentPage - 1) * pageSize;
        return MusicListDao.queryByName(songName, singerName, currentNum, pageSize);
    }
    @Override
    public Long queryByNameTotal(SongNameObj songNameObj) {
        String songName = songNameObj.songName;
        String singerName = songNameObj.singerName;
        return MusicListDao.queryByNameTotal(songName, singerName);
    }
}
