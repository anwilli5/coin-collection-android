/*
 * Coin Collection, an Android app that helps users track the coins that they've collected
 * Copyright (C) 2010-2016 Andrew Williams
 *
 * This file is part of Coin Collection.
 *
 * Coin Collection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Coin Collection is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Coin Collection.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.coincollection;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;

import com.spencerpages.R;

import java.io.IOException;

/**
 * Coin contained in a collection
 */
public class CoinSlot implements Parcelable {

    /** mIdentifierList Contains a list of the main coin identifiers (Ex: "2009", or "Kentucky") */
    private final String mIdentifier;

    /** mMintList Contains a list of the mint marks associated with each coin, if any */
    private final String mMint;

    // These are public so that we can reach in and grab these values to save them off
    // We will keep track of which items need to be pushed back

    // List holding whether each coin is in the collection
    private boolean mInCollection = false;

    // Lists needed to support the advanced view
    private boolean mIndexHasChanged = false;

    // In the database, we store the index into the grade and quantity arrays
    // so we can use these values efficiently.  For notes, we have to store the
    // - Note: these default values must match the DB defaults
    private Integer mAdvancedGrades = 0;
    private Integer mAdvancedQuantities = 0;
    private String mAdvancedNotes = "";

    // Database keys
    public final static String COL_COIN_IDENTIFIER = "coinIdentifier";
    public final static String COL_COIN_MINT = "coinMint";
    public final static String COL_IN_COLLECTION = "inCollection";
    public final static String COL_ADV_GRADE_INDEX = "advGradeIndex";
    public final static String COL_ADV_QUANTITY_INDEX = "advQuantityIndex";
    public final static String COL_ADV_NOTES = "advNotes";

    // Database helpers
    public final static String COIN_SLOT_WHERE_CLAUSE = COL_COIN_IDENTIFIER + "=? AND " + COL_COIN_MINT + "=?";

    public CoinSlot (String identifier, String mint, boolean inCollection, Integer advancedGrades,
                     Integer advancedQuantities, String advancedNotes) {
        mIdentifier = identifier;
        mMint = mint;
        mInCollection = inCollection;
        mAdvancedGrades = advancedGrades;
        mAdvancedQuantities = advancedQuantities;
        mAdvancedNotes = advancedNotes;
    }

    public CoinSlot (String identifier, String mint, boolean inCollection) {
        mIdentifier = identifier;
        mMint = mint;
        mInCollection = inCollection;
    }

    public CoinSlot (String identifier, String mint) {
        mIdentifier = identifier;
        mMint = mint;
    }

    public void setInCollection (boolean inCollection) {
        mInCollection = inCollection;
    }

    public String getIdentifier() {
        return mIdentifier;
    }

    public String getMint() {
        return mMint;
    }

    void setIndexChanged(boolean changed) {
        mIndexHasChanged = changed;
    }

    public boolean isInCollection() {
        return mInCollection;
    }

    public Integer isInCollectionInt() {
        return this.isInCollection() ? 1 : 0;
    }

    public Integer isInCollectionStringRes() {
        return this.isInCollection() ? R.string.collected : R.string.missing;
    }

    boolean hasIndexChanged() {
        return mIndexHasChanged;
    }

    public Integer getAdvancedGrades() {
        return mAdvancedGrades;
    }

    public Integer getAdvancedQuantities() {
        return mAdvancedQuantities;
    }

    public String getAdvancedNotes() {
        return mAdvancedNotes;
    }

    public void setAdvancedGrades(Integer advancedGrades) {
        this.mAdvancedGrades = advancedGrades;
    }

    public void setAdvancedQuantities(Integer advancedQuantities) {
        this.mAdvancedQuantities = advancedQuantities;
    }

    public void setAdvancedNotes(String advancedNotes) {
        this.mAdvancedNotes = advancedNotes;
    }

    /**
     * Get the coin slot parameters to export to CSV
     * @return string array with coin slot data
     */
    public String[] getLegacyCsvExportProperties() {
        return new String[] {
                mIdentifier,
                mMint,
                String.valueOf(isInCollectionInt()),
                String.valueOf(mAdvancedGrades),
                String.valueOf(mAdvancedQuantities),
                mAdvancedNotes};
    }

    /**
     * Write out the JSON representation (for exporting)
     * @param writer JsonWriter to write to
     * @throws IOException if an error occurred
     */
    public void writeToJson(JsonWriter writer) throws IOException {

        writer.beginObject();
        writer.name(COL_COIN_IDENTIFIER).value(mIdentifier);
        writer.name(COL_COIN_MINT).value(mMint);
        writer.name(COL_IN_COLLECTION).value(mInCollection);
        writer.name(COL_ADV_GRADE_INDEX).value(mAdvancedGrades);
        writer.name(COL_ADV_QUANTITY_INDEX).value(mAdvancedQuantities);
        writer.name(COL_ADV_NOTES).value(mAdvancedNotes);
        writer.endObject();
    }

    /**
     * Create a CoinSlot from imported JSON file
     * @param reader JsonReader to read from
     * @throws IOException if an error occurred
     */
    public CoinSlot(JsonReader reader) throws IOException {

        String identifier = "";
        String mint = "";
        boolean inCollection = false;
        int advancedGrades = 0;
        int advancedQuantities = 0;
        String advancedNotes = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case COL_COIN_IDENTIFIER:
                    identifier = reader.nextString();
                    break;
                case COL_COIN_MINT:
                    mint = reader.nextString();
                    break;
                case COL_IN_COLLECTION:
                    inCollection = reader.nextBoolean();
                    break;
                case COL_ADV_GRADE_INDEX:
                    advancedGrades = reader.nextInt();
                    break;
                case COL_ADV_QUANTITY_INDEX:
                    advancedQuantities = reader.nextInt();
                    break;
                case COL_ADV_NOTES:
                    advancedNotes = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        mIdentifier = identifier;
        mMint = mint;
        mInCollection = inCollection;
        mAdvancedGrades = advancedGrades;
        mAdvancedQuantities = advancedQuantities;
        mAdvancedNotes = advancedNotes;
    }

    /**
     * Create a CoinSlot from imported string array
     * @param in input String[]
     */
    public CoinSlot(String[] in) {
        mIdentifier = (in.length > 0 ? in[0] : "");
        mMint = (in.length > 1 ? in[1] : "");
        mInCollection = (in.length > 2 && (Integer.parseInt(in[2]) != 0));
        mAdvancedGrades = (in.length > 3 ? Integer.parseInt(in[3]) : 0);
        mAdvancedQuantities = (in.length > 4 ? Integer.parseInt(in[4]) : 0);
        mAdvancedNotes = (in.length > 5 ? in[5] : "");
    }

    /**
     * Creates a copy of the current coin with a different name and mint mark
     * @param newIdentifier new coin identifier
     * @param newMint new mint mark
     * @return the new CoinSlot object
     */
    public CoinSlot copy(String newIdentifier, String newMint) {
        return new CoinSlot(
                newIdentifier,
                newMint,
                mInCollection,
                mAdvancedGrades,
                mAdvancedQuantities,
                mAdvancedNotes);
    }

    /* We make this object Parcelable so that the list can be passed between Activities in the case
     * where a screen orientation change occurs.
     */
    private CoinSlot(Parcel in) {
        mIdentifier = in.readString();
        mMint = in.readString();
        mInCollection = in.readByte() != 0;
        mIndexHasChanged = in.readByte() != 0;
        if (in.readByte() == 0) {
            mAdvancedGrades = null;
        } else {
            mAdvancedGrades = in.readInt();
        }
        if (in.readByte() == 0) {
            mAdvancedQuantities = null;
        } else {
            mAdvancedQuantities = in.readInt();
        }
        mAdvancedNotes = in.readString();
    }

    public static final Creator<CoinSlot> CREATOR = new Creator<CoinSlot>() {
        @Override
        public CoinSlot createFromParcel(Parcel in) {
            return new CoinSlot(in);
        }

        @Override
        public CoinSlot[] newArray(int size) {
            return new CoinSlot[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mIdentifier);
        dest.writeString(mMint);
        dest.writeByte((byte) (mInCollection ? 1 : 0));
        dest.writeByte((byte) (mIndexHasChanged ? 1 : 0));
        if (mAdvancedGrades == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mAdvancedGrades);
        }
        if (mAdvancedQuantities == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mAdvancedQuantities);
        }
        dest.writeString(mAdvancedNotes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinSlot coinSlot = (CoinSlot) o;
        return mIdentifier.equals(coinSlot.mIdentifier) &&
                mMint.equals(coinSlot.mMint);
    }
}