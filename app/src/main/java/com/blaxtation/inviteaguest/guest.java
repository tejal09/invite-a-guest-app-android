package com.blaxtation.inviteaguest;

public class guest {

    public String name;
    public String image;
    public String sex;
    public String age;
    public String location;
    public String rating;
    public String guestDescription;
    public String language;
    public String eventtype;
    public String category;

    public guest(String name, String image, String sex, String age, String location, String rating, String guestDescription, String language, String eventtype, String category) {
        this.name = name;
        this.image = image;
        this.sex = sex;
        this.age = age;
        this.location = location;
        this.rating =rating;
        this.guestDescription = guestDescription;
        this.language = language;
        this.eventtype = eventtype;
        this.category = category;
    }




    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGuestDescription() {
        return guestDescription;
    }

    public void setGuestDescription(String guestDescription) {
        this.guestDescription = guestDescription;
    }








    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


