package com.example.companyrecycle;

public class ItemModel {
    public int img;
    public String ComapnyName;
    public String Skill;
    public String JobType;
    public String Salary;
    public String Currency;

    public ItemModel(int img, String comapnyName, String description, String skill, String jobType, String salary, String currency) {
        this.img = img;
        this.ComapnyName = comapnyName;
        this.Skill = skill;
        this.JobType = jobType;
        this.Salary = salary;
        this.Currency = currency;
    }

    public int getImg() { return img; }

    public void setImg(int img) { this.img = img; }

    public String getComapnyName() { return ComapnyName; }

    public void setComapnyName(String comapnyName) { ComapnyName = comapnyName; }

    public String getSkill() { return Skill; }

    public void setSkill(String skill) { Skill = skill; }

    public String getJobType() { return JobType; }

    public void setJobType(String jobType) { JobType = jobType; }

    public String getSalary() { return Salary; }

    public void setSalary(String salary) { Salary = salary; }

    public String getCurrency() { return Currency; }

    public void setCurrency(String currency) {
        Currency = currency;
    }

}
