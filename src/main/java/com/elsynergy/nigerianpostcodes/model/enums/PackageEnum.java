package com.elsynergy.nigerianpostcodes.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum PackageEnum
{
    BASIC("BASIC", 1),
    PLUS("PLUS", 2),
    PREMIUM("PREMIUM", 3);

    private String name;
    private Integer id;

    public static final Map<String, Integer> ID_LOOKUP_MAP = new HashMap<>();

    static {

        for (final PackageEnum type : PackageEnum.values()) {
            ID_LOOKUP_MAP.put(type.toString(), type.getId());
        }

    }

    private PackageEnum(final String name, final Integer id) {
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
