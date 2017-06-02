package com.elsynergy.nigerianpostcodes.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum
{
    ADMIN("ADMIN", 1),
    USER("USER", 2);

    private String name;
    private Integer id;

    public static final Map<String, Integer> ID_LOOKUP_MAP = new HashMap<>();

    static {

        for (final RoleEnum type : RoleEnum.values()) {
            ID_LOOKUP_MAP.put(type.toString(), type.getId());
        }

    }

    private RoleEnum(final String name, final Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private Integer getId() {
        return this.id;
    }
}
