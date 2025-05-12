package com.Ecommerce.model;

import java.util.UUID;

public interface Category{

    @NotNull UUID getId();
    @NotNull String getName();
    @NotNull String getDescription();
}