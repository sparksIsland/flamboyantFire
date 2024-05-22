package com.spark.springcloud.music.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicList implements Serializable {
        private BigInteger id;
        private String songName;
        private String singerName;
        private String keyValue;
        private String duration;
}

