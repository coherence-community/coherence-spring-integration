/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oracle.coherence.spring.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.tangosol.util.UUID;

/**
 * An entity for representing a {@code book}.
 */
@Entity
public class Book implements Cloneable, Serializable {
    /**
     * The unique id of this book.
     */
    @Id
    protected final UUID uuid;

    /**
     * The title of the {@code book}.
     */
    protected String title;

    /**
     * The {@link Author author} of the {@code book}.
     */
    protected Author author;

    /**
     * The number of pages the {@code book} has.
     */
    protected int pages;

    /**
     * The {@code book}'s publication date.
     */
    protected Calendar published;


    /**
     * Constructs a new {@code Book}.
     *
     * @param title the book's title
     * @param pages the number of pages the book has
     * @param author the book's {@link Author author}
     * @param published the book's publication date
     */
    public Book(String title, int pages, Author author, Calendar published) {
        this.uuid = new UUID();
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.published = published;
    }

    public Book(UUID uuid) {
        this.uuid = uuid;
        this.title = "UNTITLED";
        this.pages = 0;
        this.author = null;
        this.published = null;
    }

    public Book(Book copy) {
        this.uuid = copy.uuid;
        this.title = copy.title;
        this.pages = copy.pages;
        this.author = copy.author;
        this.published = copy.published;
    }

    public Book() {
        this.uuid = new UUID();
    }

    /**
     * Return this {@code book}'s unique {@link UUID}.
     *
     * @return this {@code book}'s unique {@link UUID}
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * Return this {@code book}'s title.
     *
     * @return this {@code book}'s title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Return this {@code book}'s {@link Author author}.
     *
     * @return this {@code book}'s {@link Author author}
     */
    public Author getAuthor() {
        return this.author;
    }

    public String getTitleSequence() {
        return this.title;
    }

    /**
     * Returns the number of pages in this {@code book}.
     *
     * @return the number of pages in this {@code book}
     */
    public int getPages() {
        return this.pages;
    }

    /**
     * Returns the number of pages in this {@code book} as a long.
     *
     * @return the number of pages in this {@code book} as a long
     */
    public long getPagesAsLong() {
        return this.pages;
    }

    /**
     * Returns the number of pages in this {@code book} as a double.
     *
     * @return the number of pages in this {@code book} as a double
     */
    public double getPagesAsDouble() {
        return this.pages;
    }

    /**
     * Returns the number of pages in this {@code book} as a BigDecimal.
     *
     * @return the number of pages in this {@code book} as a BigDecimal
     */
    public BigDecimal getPagesAsBigDecimal() {
        return BigDecimal.valueOf(getPagesAsLong());
    }

    /**
     * Set the number of pages in this {@code book}.
     *
     * @param pages the new value for the page count
     */
    public void setPages(final int pages) {
        this.pages = pages;
    }

    /**
     * Returns the year this {@code book} was published.
     *
     * @return the year this {@code book} was published
     */
    public int getPublicationYear() {
        return this.published.get(Calendar.YEAR);
    }

    /**
     * Returns a {@link Calendar} representing the publication date of the {@code book}.
     *
     * @return a {@link Calendar} representing the publication date of the {@code book}
     */
    @SuppressWarnings("unused")
    public Calendar getPublished() {
        return this.published;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Book book = (Book) o;
        return getPages() == book.getPages() && getUuid().equals(book.getUuid()) && getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getTitle(), getAuthor(), getPages());
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + this.uuid +
                ", title='" + this.title + '\'' +
                ", author=" + this.author +
                ", pages=" + this.pages +
                '}';
    }
}
