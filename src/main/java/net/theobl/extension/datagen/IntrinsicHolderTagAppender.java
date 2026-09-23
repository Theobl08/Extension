package net.theobl.extension.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jspecify.annotations.NullMarked;

import java.util.Arrays;
import java.util.List;

@NullMarked
public abstract class IntrinsicHolderTagAppender<Element> implements TagAppender<Element> {
    private final TagAppender<Element> original;

    public IntrinsicHolderTagAppender(TagAppender<Element> original) {
        this.original = original;
    }

    public abstract ResourceKey<Element> convertElement(Element element);

    @Override
    public IntrinsicHolderTagAppender<Element> add(ResourceKey<Element> element) {
        this.original.add(element);
        return this;
    }

    @SafeVarargs
    @Override
    public final IntrinsicHolderTagAppender<Element> add(ResourceKey<Element>... elements) {
        this.original.add(elements);
        return this;
    }

    @Override
    public IntrinsicHolderTagAppender<Element> addOptional(ResourceKey<Element> element) {
        this.original.addOptional(element);
        return this;
    }

    @Override
    public IntrinsicHolderTagAppender<Element> addTag(TagKey<Element> tag) {
        this.original.addTag(tag);
        return this;
    }

    @Override
    public IntrinsicHolderTagAppender<Element> addOptionalTag(TagKey<Element> tag) {
        this.original.addOptionalTag(tag);
        return this;
    }

    @Override
    public IntrinsicHolderTagAppender<Element> add(TagEntry entry) {
        original.add(entry);
        return this;
    }

    @Override
    public IntrinsicHolderTagAppender<Element> replace(boolean value) {
        original.replace(value);
        return this;
    }

    @Override
    public IntrinsicHolderTagAppender<Element> remove(ResourceKey<Element> element) {
        original.remove(element);
        return this;
    }

    @Override
    public IntrinsicHolderTagAppender<Element> remove(TagKey<Element> tag) {
        original.remove(tag);
        return this;
    }

    public IntrinsicHolderTagAppender<Element> add(DeferredHolder<Element, ? extends Element> element) {
        this.add(element.getKey());
        return this;
    }

    @SafeVarargs
    public final IntrinsicHolderTagAppender<Element> add(DeferredHolder<Element, ? extends Element>... element) {
        this.original.addAll(Arrays.stream(element).map(DeferredHolder::getKey));
        return this;
    }

    @SafeVarargs
    public final IntrinsicHolderTagAppender<Element> add(Element... element) {
        this.original.addAll(Arrays.stream(element).map(this::convertElement));
        return this;
    }

    public <T extends DeferredHolder<Element, ? extends Element>> IntrinsicHolderTagAppender<Element> addAll(ColorCollection<T> collection) {
        collection.forEach(this::add);
        return this;
    }

    public <T extends DeferredHolder<Element, ? extends Element>> IntrinsicHolderTagAppender<Element> addAll(WeatheringCopperCollection<T> collection) {
        collection.forEach(this::add);
        return this;
    }

    public <T extends DeferredHolder<Element, ? extends Element>> IntrinsicHolderTagAppender<Element> addAll(Iterable<T> collection) {
        collection.forEach(this::add);
        return this;
    }

    public IntrinsicHolderTagAppender<Element> addAll(List<Element> collection) {
        collection.forEach(this::add);
        return this;
    }
}