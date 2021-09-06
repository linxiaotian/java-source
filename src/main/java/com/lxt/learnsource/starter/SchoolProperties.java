package com.lxt.learnsource.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "school")
@Data
class SchoolProperties {

    private SchoolMessage one = new SchoolMessage();

    @Data
    public static class SchoolMessage {

        private String totalKlass;

        private String everyKlassStudents;

    }

}
