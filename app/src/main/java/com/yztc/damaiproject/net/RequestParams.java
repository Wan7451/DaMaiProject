package com.yztc.damaiproject.net;

/**
 * Created by wanggang on 2017/3/14.
 */

public class RequestParams {

    private String baseUrl;
    private String path;
    private String params;

    public RequestParams(String baseUrl, String path, String params) {
        this.baseUrl = baseUrl;
        this.path = path;
        this.params = params;
    }

    public String generateUrl() {
        return new StringBuffer()
                .append(baseUrl)
                .append(path)
                .append("?")
                .append(params)
                .toString();
    }

    public static class Builder {
        private String baseUrl;
        private String path;
        private StringBuffer params;

        public Builder() {
            params=new StringBuffer();
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            if (!baseUrl.endsWith("/")) {
                throw new RuntimeException("baseurl should end with / ");
            }
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder params(String k,String v) {
            //& k=v & k=v & k=v
            this.params.append(k);
            this.params.append("=");
            this.params.append(v);
            this.params.append("&");
            return this;
        }

        public RequestParams build(){
            //去掉 &
            this.params.setLength(params.length()-1);
            return new RequestParams(baseUrl,path,params.toString());
        }
    }
}
