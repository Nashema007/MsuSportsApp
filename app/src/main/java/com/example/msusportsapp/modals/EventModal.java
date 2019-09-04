package com.example.msusportsapp.modals;

public class EventModal implements Comparable{

    private String eventDescription;
    private String eventSummary;
    private String eventTitle;
    private int eventImage;

    public EventModal( String eventTitle, String eventDescription) {
        this.eventDescription = eventDescription;
        this.eventTitle = eventTitle;
        this.eventSummary = getEventDescription().substring(0, 30)+ "...";
    }

    public EventModal(String eventTitle, String eventDescription,  int eventImage) {
        this.eventDescription = eventDescription;
        this.eventTitle = eventTitle;
        this.eventImage = eventImage;
        this.eventSummary = getEventDescription().substring(0, 20);
    }


    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public int getEventImage() {
        return eventImage;
    }

    public String getEventSummary() {
        return eventSummary;
    }




    @Override
    public int compareTo(Object o) {
        EventModal compare = (EventModal) o;

        if (compare.getEventTitle().equals(this.eventTitle) && compare.getEventSummary().equals(this.eventSummary)){
            return 0;
        }
        return 1;
    }


}
