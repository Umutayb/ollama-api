package models;

import java.util.List;

public class Pet {
    Long id;
    DataModel category;
    String name;
    List<String> photoUrls;
    List<DataModel> tags;
    String status;

    public static class DataModel {
        Long id;
        String name;
    }
}
