package com.Ecommerce.model;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;

public interface Category{

    @NotNull UUID getId();
    @NotNull String getName();
    @NotNull String getDescription();
}