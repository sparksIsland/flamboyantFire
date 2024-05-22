package com.spark.springcloud.music.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class  SongNameObj implements Serializable {
    public String songName;
    public String singerName;
}
