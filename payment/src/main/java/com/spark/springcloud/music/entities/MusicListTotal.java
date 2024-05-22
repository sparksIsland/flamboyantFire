package com.spark.springcloud.music.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class MusicListTotal implements Serializable {
    public BigInteger total;
}
