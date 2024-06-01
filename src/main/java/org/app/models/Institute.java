package org.app.models;

import java.util.ArrayList;
import java.util.List;

public class Institute {

    private String name;
    List<CoachingCenter> coachingCenters;

    public Institute(String name) {
        this.name = name;
        coachingCenters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCoachingCenter(CoachingCenter coachingCenter) {
        this.coachingCenters.add(coachingCenter);

    }

    public List<CoachingCenter> getCoachingCenters() {
        return coachingCenters;
    }


}
