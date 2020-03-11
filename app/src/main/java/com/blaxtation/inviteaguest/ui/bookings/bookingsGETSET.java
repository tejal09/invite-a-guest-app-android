package com.blaxtation.inviteaguest.ui.bookings;

public class bookingsGETSET {
    public String getHostnamee() {
        return hostnamee;
    }

    public void setHostnamee(String hostnamee) {
        this.hostnamee = hostnamee;
    }

    public String getInvitedby() {
        return invitedby;
    }

    public void setInvitedby(String invitedby) {
        this.invitedby = invitedby;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getAudiencetype() {
        return audiencetype;
    }

    public void setAudiencetype(String audiencetype) {
        this.audiencetype = audiencetype;
    }

    public String getDateofevent() {
        return dateofevent;
    }

    public void setDateofevent(String dateofevent) {
        this.dateofevent = dateofevent;
    }

    public String getNumbofdays() {
        return numbofdays;
    }

    public void setNumbofdays(String numbofdays) {
        this.numbofdays = numbofdays;
    }

    public String getHoursofengagement() {
        return hoursofengagement;
    }

    public void setHoursofengagement(String hoursofengagement) {
        this.hoursofengagement = hoursofengagement;
    }

    public String getVenuelocation() {
        return venuelocation;
    }

    public void setVenuelocation(String venuelocation) {
        this.venuelocation = venuelocation;
    }

    public String getBudgettotal() {
        return budgettotal;
    }

    public void setBudgettotal(String budgettotal) {
        this.budgettotal = budgettotal;
    }

    public String getEventdetails() {
        return eventdetails;
    }

    public void setEventdetails(String eventdetails) {
        this.eventdetails = eventdetails;
    }

    public String getGuestexpectations() {
        return guestexpectations;
    }

    public void setGuestexpectations(String guestexpectations) {
        this.guestexpectations = guestexpectations;
    }


    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid){

        this.docid=docid;
    }


    public bookingsGETSET() {
    }

    String hostnamee;
    String invitedby;
    String emailaddress;
    String contactnumber;
    String audiencetype;
    String dateofevent;
    String numbofdays;
    String hoursofengagement;
    String venuelocation;
    String budgettotal;
    String eventdetails;
    String guestexpectations;
    String docid;


    public bookingsGETSET(String hostnamee, String invitedby, String emailaddress, String contactnumber, String audiencetype, String dateofevent, String numbofdays, String hoursofengagement, String venuelocation, String budgettotal, String eventdetails, String guestexpectations, String docid) {
        this.hostnamee = hostnamee;
        this.invitedby = invitedby;
        this.emailaddress = emailaddress;
        this.contactnumber = contactnumber;
        this.audiencetype = audiencetype;
        this.dateofevent = dateofevent;
        this.numbofdays = numbofdays;
        this.hoursofengagement = hoursofengagement;
        this.venuelocation = venuelocation;
        this.budgettotal = budgettotal;
        this.eventdetails = eventdetails;
        this.guestexpectations = guestexpectations;
        this.docid = docid;
    }
}