package com.kaishengit.qiniu;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QIniuUtils {

    private String accessKey = "cQyoWKttp03t7MisCnZVfG_Bm-2KVdsUNLgyLRRH";
    private String secretKey = "o0pwMrKWOJdXFKzxdz-6IevMLcImCVSpBxj9kr9U";
    private String bucket = "kaishengit-tms";

    public String getUploadToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }

}
