package com.Ecommerce.model;

import java.util.Arrays;
import java.util.Optional;

public interface Category{

    @NotNull UUID getId();
    @NotNull String getName();
    @NotNull String getDescription();
}