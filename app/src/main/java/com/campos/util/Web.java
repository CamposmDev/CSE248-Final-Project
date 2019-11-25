package com.campos.util;

public class Web {
    public static final String COLLEGE_SCORECARD_QUERY = "https://api.data.gov/ed/collegescorecard/v1/schools.json?&fields=" +
            "id," +
            "school.region_id," +
            "school.name," +
            "school.city," +
            "school.state," +
            "school.zip," +
            "school.school_url," +
            "latest.cost.tuition.in_state," +
            "latest.cost.tuition.out_of_state," +
            "latest.student.size," +
            "latest.admissions.admission_rate.overall," +
            "school.degrees_awarded.predominant," +
            "latest.admissions.sat_scores.25th_percentile.critical_reading," +
            "latest.admissions.sat_scores.25th_percentile.math," +
            "latest.admissions.sat_scores.25th_percentile.writing" +
            "latest.admissions.sat_scores.75th_percentile.critical_reading," +
            "latest.admissions.sat_scores.75th_percentile.math" +
            "latest.admissions.sat_scores.75th_percentile.writing" +
            "&api_key=eymRFR4vdKAgPCK3JIw9Es42ytaEelgZf43H5TKc" +
            "&_per_page=100";

}
