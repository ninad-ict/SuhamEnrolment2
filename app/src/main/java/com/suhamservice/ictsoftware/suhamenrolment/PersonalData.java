package com.suhamservice.ictsoftware.suhamenrolment;

public class PersonalData {

    private String NAME;
    private int AGE;
    private String MotherName;
    private String Fathername;
    private int DOB;
    private String Marriage;
    private int MarriageYear;


    public PersonalData()
    {}

    /*public PersonalData (String name,int age,String motherName,String fathername, int dob, String marriage,int marriageYear)
    {
        this.AGE=age;
        this.NAME=name;
        this.DOB=dob;
        this.Fathername=fathername;
        this.MotherName=motherName;
        this.MarriageYear=marriageYear;
        this.Marriage=marriage;
    }*/

    public void setNAME(String name)
    {
        this.NAME=name;
    }

    public String getNAME(){return this.NAME;}

    public void setAGE(int AGE) {
        this.AGE = AGE;
    }

    public int getAGE() {
        return AGE;
    }

    public int getDOB() {
        return DOB;
    }

    public int getMarriageYear() {
        return MarriageYear;
    }

    public String getMotherName()
    {
        return MotherName;
    }

    public String getFathername()
    {
        return Fathername;
    }

    public String getMarriage()
    {
        return Marriage;
    }

    public void setDOB(int DOB) {
        this.DOB = DOB;
    }

    public void setFathername(String fathername) {
        Fathername = fathername;
    }

    public void setMarriage(String marriage) {
        Marriage = marriage;
    }

    public void setMotherName(String motherName) {
        MotherName = motherName;
    }

    public void setMarriageYear(int marriageYear) {
        MarriageYear = marriageYear;
    }


}
