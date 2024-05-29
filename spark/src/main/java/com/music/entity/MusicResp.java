package com.music.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class MusicResp implements Serializable {
    public List<MusicList> list;
//    public Integer currentPage;
//    public Integer pageSize;
    public Long total;

    public void setTotal(Long total) {
        this.total = total;
    }
    public void setList(List<MusicList> list) {
        this.list = list;
    }
}
