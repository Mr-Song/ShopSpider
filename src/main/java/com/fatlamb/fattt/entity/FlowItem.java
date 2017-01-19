package com.fatlamb.fattt.entity;


/**
 * Created by hasee on 2017/1/8.
 */
public class FlowItem {
    public long id ;
    public String title ;
    public String headpic ;
    public String summary ;
    public String pricedesc ;
    public String addtime ;
    public String source ;
    public String mall ;
    public String targeturl ;
    public String directbuyurl ;
    public long detailid ;
    public int readcount ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPricedesc() {
        return pricedesc;
    }

    public void setPricedesc(String pricedesc) {
        this.pricedesc = pricedesc;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getTargeturl() {
        return targeturl;
    }

    public void setTargeturl(String targeturl) {
        this.targeturl = targeturl;
    }

    public String getDirectbuyurl() {
        return directbuyurl;
    }

    public void setDirectbuyurl(String directbuyurl) {
        this.directbuyurl = directbuyurl;
    }

    public long getDetailid() {
        return detailid;
    }

    public void setDetailid(long detailid) {
        this.detailid = detailid;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }
}
