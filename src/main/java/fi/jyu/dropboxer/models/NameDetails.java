package fi.jyu.dropboxer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameDetails {

    @SerializedName("familiar_name")
    @Expose
    private String familiarName;
    @SerializedName("given_name")
    @Expose
    private String givenName;
    @SerializedName("surname")
    @Expose
    private String surname;

    public String getFamiliarName() {
        return familiarName;
    }

    public void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
