package com.spark.springcloud.music.entities;


import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class MusicResp implements Serializable {
//    @NotNull
    public List<MusicList> list;
//    public Integer currentPage;
//    public Integer pageSize;
//    @NotNull
    public Long total;

//    @NotNull
    public void setTotal(Long total) {
        this.total = total;
    }
//    @NotNull
    public void setList(List<MusicList> list) {
        this.list = list;
    }
}
