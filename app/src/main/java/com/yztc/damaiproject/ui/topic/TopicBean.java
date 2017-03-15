package com.yztc.damaiproject.ui.topic;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wanggang on 2017/3/13.
 */

@Entity
public class TopicBean {

    @Id
    private Long id;

    private String key;//url
    private Long time;//时间

    private int i;
    private String n;
    private String p;
    private String priceName;
    private String t;
    private int s;
    private String v;
    private int VenId;
    private String VenName;
    private int b;
    private String d;
    private boolean IsOnlyXuanZuo;
    private boolean IsToBeAboutTo;
    private int CategoryID;
    private int IsXuanZuo;
    private int CityId;
    private int BuySum;
    private String StartTicketTime;
    private int PrivilegeType;
    private int openSum;
    private int IsGeneralAgent;
    private boolean IsPreregistration;
    private boolean SupportedDeductionIntegral;
    private boolean isSellOut;



    @Generated(hash = 1654964541)
    public TopicBean(Long id, String key, Long time, int i, String n, String p,
            String priceName, String t, int s, String v, int VenId, String VenName,
            int b, String d, boolean IsOnlyXuanZuo, boolean IsToBeAboutTo,
            int CategoryID, int IsXuanZuo, int CityId, int BuySum,
            String StartTicketTime, int PrivilegeType, int openSum,
            int IsGeneralAgent, boolean IsPreregistration,
            boolean SupportedDeductionIntegral, boolean isSellOut) {
        this.id = id;
        this.key = key;
        this.time = time;
        this.i = i;
        this.n = n;
        this.p = p;
        this.priceName = priceName;
        this.t = t;
        this.s = s;
        this.v = v;
        this.VenId = VenId;
        this.VenName = VenName;
        this.b = b;
        this.d = d;
        this.IsOnlyXuanZuo = IsOnlyXuanZuo;
        this.IsToBeAboutTo = IsToBeAboutTo;
        this.CategoryID = CategoryID;
        this.IsXuanZuo = IsXuanZuo;
        this.CityId = CityId;
        this.BuySum = BuySum;
        this.StartTicketTime = StartTicketTime;
        this.PrivilegeType = PrivilegeType;
        this.openSum = openSum;
        this.IsGeneralAgent = IsGeneralAgent;
        this.IsPreregistration = IsPreregistration;
        this.SupportedDeductionIntegral = SupportedDeductionIntegral;
        this.isSellOut = isSellOut;
    }

    @Generated(hash = 1961217991)
    public TopicBean() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public int getVenId() {
        return VenId;
    }

    public void setVenId(int VenId) {
        this.VenId = VenId;
    }

    public String getVenName() {
        return VenName;
    }

    public void setVenName(String VenName) {
        this.VenName = VenName;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public boolean isIsOnlyXuanZuo() {
        return IsOnlyXuanZuo;
    }

    public void setIsOnlyXuanZuo(boolean IsOnlyXuanZuo) {
        this.IsOnlyXuanZuo = IsOnlyXuanZuo;
    }

    public boolean isIsToBeAboutTo() {
        return IsToBeAboutTo;
    }

    public void setIsToBeAboutTo(boolean IsToBeAboutTo) {
        this.IsToBeAboutTo = IsToBeAboutTo;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getIsXuanZuo() {
        return IsXuanZuo;
    }

    public void setIsXuanZuo(int IsXuanZuo) {
        this.IsXuanZuo = IsXuanZuo;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int CityId) {
        this.CityId = CityId;
    }

    public int getBuySum() {
        return BuySum;
    }

    public void setBuySum(int BuySum) {
        this.BuySum = BuySum;
    }

    public String getStartTicketTime() {
        return StartTicketTime;
    }

    public void setStartTicketTime(String StartTicketTime) {
        this.StartTicketTime = StartTicketTime;
    }

    public int getPrivilegeType() {
        return PrivilegeType;
    }

    public void setPrivilegeType(int PrivilegeType) {
        this.PrivilegeType = PrivilegeType;
    }

    public int getOpenSum() {
        return openSum;
    }

    public void setOpenSum(int openSum) {
        this.openSum = openSum;
    }

    public int getIsGeneralAgent() {
        return IsGeneralAgent;
    }

    public void setIsGeneralAgent(int IsGeneralAgent) {
        this.IsGeneralAgent = IsGeneralAgent;
    }

    public boolean isIsPreregistration() {
        return IsPreregistration;
    }

    public void setIsPreregistration(boolean IsPreregistration) {
        this.IsPreregistration = IsPreregistration;
    }

    public boolean isSupportedDeductionIntegral() {
        return SupportedDeductionIntegral;
    }

    public void setSupportedDeductionIntegral(boolean SupportedDeductionIntegral) {
        this.SupportedDeductionIntegral = SupportedDeductionIntegral;
    }

    public boolean isIsSellOut() {
        return isSellOut;
    }

    public void setIsSellOut(boolean isSellOut) {
        this.isSellOut = isSellOut;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsOnlyXuanZuo() {
        return this.IsOnlyXuanZuo;
    }

    public boolean getIsToBeAboutTo() {
        return this.IsToBeAboutTo;
    }

    public boolean getIsPreregistration() {
        return this.IsPreregistration;
    }

    public boolean getSupportedDeductionIntegral() {
        return this.SupportedDeductionIntegral;
    }

    public boolean getIsSellOut() {
        return this.isSellOut;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
