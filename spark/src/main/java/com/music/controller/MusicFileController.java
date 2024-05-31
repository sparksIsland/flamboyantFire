package com.music.controller;

import com.common.entity.CommonResult;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

/**
 * @author liupeng
 * @version 1.0
 * @description: TODO
 * @date 2024/5/31 13:42
 */
@RestController
public class MusicFileController {


    @GetMapping(value = "/spark/cos/getcoskey")
    public CommonResult<Object> getCosKey() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {
            // 云 api 密钥 SecretId
            config.put("secretId", "AKID2tZ2FCtKLDqEMFuJ0kQsIVTYS8auymKj");
            // 云 api 密钥 SecretKey
            config.put("secretKey", "FDvQhUQLOM7c4HLAj393i8IjuqYowsb6");

            // 临时密钥有效时长，单位是秒
            config.put("durationSeconds", 1800);

            // 换成你的 bucket
            config.put("bucket", "sparksisland-1316644977");
            // 换成 bucket 所在地区
            config.put("region", "ap-nanjing");

            // 可以通过 allowPrefixes 指定前缀数组, 例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
            config.put("allowPrefixes", new String[]{
                    "music/*"
//                    "exampleobject2"
            });

            // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[]{
                    // 简单上传
                    "name/cos:PutObject",
                    "name/cos:PostObject",
                    "name/cos:GetObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);
            Response response = CosStsClient.getCredential(config);
            return new CommonResult(200, "查询成功", response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
