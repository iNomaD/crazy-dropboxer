package fi.jyu.dropboxer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountInfo {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("name_details")
    @Expose
    private NameDetails nameDetails;
    @SerializedName("referral_link")
    @Expose
    private String referralLink;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified")
    @Expose
    private Boolean emailVerified;
    @SerializedName("is_paired")
    @Expose
    private Boolean isPaired;
    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("quota_info")
    @Expose
    private QuotaInfo quotaInfo;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public NameDetails getNameDetails() {
        return nameDetails;
    }

    public void setNameDetails(NameDetails nameDetails) {
        this.nameDetails = nameDetails;
    }

    public String getReferralLink() {
        return referralLink;
    }

    public void setReferralLink(String referralLink) {
        this.referralLink = referralLink;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Boolean getIsPaired() {
        return isPaired;
    }

    public void setIsPaired(Boolean isPaired) {
        this.isPaired = isPaired;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public QuotaInfo getQuotaInfo() {
        return quotaInfo;
    }

    public void setQuotaInfo(QuotaInfo quotaInfo) {
        this.quotaInfo = quotaInfo;
    }

}
