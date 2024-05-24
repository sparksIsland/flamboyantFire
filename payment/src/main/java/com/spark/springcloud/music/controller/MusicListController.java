package com.spark.springcloud.music.controller;

import com.spark.springcloud.entities.CommonResult;
import com.spark.springcloud.music.entities.MusicList;
import com.spark.springcloud.music.entities.MusicListTotal;
import com.spark.springcloud.music.entities.MusicResp;
import com.spark.springcloud.music.entities.SongNameObj;
import com.spark.springcloud.music.service.MusicListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class MusicListController {
    @Resource
    private MusicListService MusicListService;
    @PostMapping (value = "/spark/music/querybyname")
    public CommonResult<MusicResp> queryByName(@RequestBody SongNameObj songNameObj) {
        List<MusicList> musicList = MusicListService.queryByName(songNameObj);
        Long total = MusicListService.queryByNameTotal(songNameObj);
        log.info( total.toString());
        MusicResp musicResp = new MusicResp();
        musicResp.setList(musicList);
        musicResp.setTotal(total);
        log.info( total.toString());
        if (musicResp != null) {
            return new CommonResult(200, "查询成功", musicResp);
        } else {
            return new CommonResult(444, "无匹配项", null);
        }
    }
}
