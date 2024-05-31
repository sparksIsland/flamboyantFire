package com.music.controller;

import com.common.entity.CommonResult;
import com.music.entity.MusicList;
import com.music.entity.MusicResp;
import com.music.entity.SongNameObj;
import com.music.service.MusicListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class MusicListController {
    @Resource
    private MusicListService musicListService;

    @PostMapping(value = "/spark/music/querybyname")
    public CommonResult<MusicResp> queryByName(@RequestBody SongNameObj songNameObj) {
        List<MusicList> musicList = musicListService.queryByName(songNameObj);
        Long total = musicListService.queryByNameTotal(songNameObj);
        log.info(total.toString());
        MusicResp musicResp = new MusicResp();
        musicResp.setList(musicList);
        musicResp.setTotal(total);
        log.info(total.toString());
        if (musicResp != null) {
            return new CommonResult(200, "查询成功", musicResp);
        } else {
            return new CommonResult(444, "无匹配项", null);
        }
    }
}
