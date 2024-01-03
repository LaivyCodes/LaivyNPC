package com.laivynpc.core.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Objects;

/**
 * A final class representing a version with a vendor, major, and minor version numbers.
 */
public final class Version {

    /**
     * Creates a new Version instance.
     *
     * @param vendor The vendor of the version.
     * @param major  The major version number (should be greater than or equal to 1).
     * @param minor  The minor version number (should be greater than or equal to 0).
     * @return A new Version instance.
     */
    @Contract(pure = true)
    public static @NotNull Version of(
            @NotNull String vendor,
            @Range(from = 1, to = Integer.MAX_VALUE) int major,
            @Range(from = 0, to = Integer.MAX_VALUE) int minor
    ) {
        return new Version(vendor, major, minor);
    }

    // Object

    private final @NotNull String vendor;

    // Numbers
    private final @Range(from = 1, to = Integer.MAX_VALUE) int major;
    private final @Range(from = 0, to = Integer.MAX_VALUE) int minor;

    /**
     * Private constructor to create a Version instance.
     *
     * @param vendor The vendor of the version.
     * @param major  The major version number (should be greater than or equal to 1).
     * @param minor  The minor version number (should be greater than or equal to 0).
     */
    private Version(@NotNull String vendor, @Range(from = 1, to = Integer.MAX_VALUE) int major, @Range(from = 0, to = Integer.MAX_VALUE) int minor) {
        this.vendor = vendor;
        this.major = major;
        this.minor = minor;
    }

    /**
     * Gets the name of the version in the format "major.minor".
     *
     * @return The name of the version.
     */
    @Contract(pure = true)
    public @NotNull String getName() {
        return getMajor() + "." + getMinor();
    }

    /**
     * Gets the vendor of the version.
     *
     * @return The vendor of the version.
     */
    @Contract(pure = true)
    public @NotNull String getVendor() {
        return this.vendor;
    }

    /**
     * Gets the major version number.
     *
     * @return The major version number.
     */
    @Contract(pure = true)
    @Range(from = 1, to = Integer.MAX_VALUE)
    public int getMajor() {
        return this.major;
    }

    /**
     * Gets the minor version number.
     *
     * @return The minor version number.
     */
    @Contract(pure = true)
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getMinor() {
        return this.minor;
    }

    /**
     * Returns a string representation of the version in the format "vendor major.minor".
     *
     * @return A string representation of the version.
     */
    @Override
    @Contract(pure = true)
    public @NotNull String toString() {
        return getVendor() + " " + getName();
    }

    @Override
    @Contract(pure = true)
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (!(o instanceof Version)) return false;
        Version version = (Version) o;
        return getMajor() == version.getMajor() && getMinor() == version.getMinor() && Objects.equals(getVendor(), version.getVendor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVendor(), getMajor(), getMinor());
    }
}


