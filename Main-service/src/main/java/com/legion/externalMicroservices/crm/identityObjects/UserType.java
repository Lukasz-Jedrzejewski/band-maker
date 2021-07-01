package com.legion.externalMicroservices.crm.identityObjects;

public enum UserType {

    MUSICIAN("musician"),
    BAND("band"),
    LOCAL("local");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }

    public static UserType getByName(String name) {
        for(UserType t: values()) {
            if (t.type.equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}
