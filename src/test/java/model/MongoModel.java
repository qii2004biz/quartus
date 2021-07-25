package model;

import facades.database.MongoDBFacade;
import net.serenitybdd.core.Serenity;

import java.util.List;

public class MongoModel {
    private String collectionName;
    private String fileName;

    public MongoModel(String collectionName, String fileName) {
        this.collectionName = collectionName;
        this.fileName = fileName;
    }

    public MongoModel(String collectionName) {
        this.collectionName = collectionName;
    }


    public MongoModel() {
    }

    private String getFileName() {
        return fileName;
    }

    public static void initalizeAllCollections(List<MongoModel> mongoList) {
        mongoList.stream()
                .forEach(x -> {
                            MongoDBFacade mongoDBFacade = new MongoDBFacade(x.collectionName, x.fileName);
                            Serenity.setSessionVariable(x.collectionName).to(mongoDBFacade);
                        }
                );
    }}
