package com.music.service;

import com.music.entity.MusicList;
import com.music.entity.SongNameObj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MusicListService {
    List<MusicList> queryByName(SongNameObj SongNameObj);

    Long queryByNameTotal(SongNameObj SongNameObj);
}
