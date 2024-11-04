package com.example.campusbite_admin.Domains;

public class NewMenuItem
{
    String Title, Rate,Description,Cost;

    public NewMenuItem(String title, String rate, String description, String cost) {
        Title = title;
        Rate = rate;
        Description = description;
        Cost = cost;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }
}
