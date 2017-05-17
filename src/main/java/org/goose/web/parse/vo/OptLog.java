package org.goose.web.parse.vo;

/**
 * Created by smart on 15-8-29.
 */
public class OptLog {

    private String moudal;
    private String request;
    private String reponse;

    public void setMoudal(String moudal) {
        this.moudal = moudal;
    }

    public String getMoudal() {
        return moudal;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getReponse() {
        return reponse;
    }
}
