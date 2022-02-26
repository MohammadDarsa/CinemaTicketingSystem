package com.cinematicketsystem.models.category;

public final class CategoryBuilder {
    private Category category;

    private CategoryBuilder() {
        category = new Category();
    }

    public static CategoryBuilder aCategory() {
        return new CategoryBuilder();
    }

    public CategoryBuilder setId(Integer id) {
        category.setId(id);
        return this;
    }

    public CategoryBuilder setName(String name) {
        category.setName(name);
        return this;
    }

    public Category build() {
        return category;
    }
}
