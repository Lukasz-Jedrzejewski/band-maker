package com.legion.externalMicroservices.crm.identityObjects;

public enum UserType {

    MUSICIAN("muzyk"),
    BAND("zezpół"),
    LOCAL("lokal");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }
}
