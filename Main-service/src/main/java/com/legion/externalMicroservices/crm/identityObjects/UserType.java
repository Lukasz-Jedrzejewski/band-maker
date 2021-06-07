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

    public static UserType getByName(String name) {
        for(UserType type: values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
