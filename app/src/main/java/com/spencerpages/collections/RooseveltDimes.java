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

package com.spencerpages.collections;

import android.database.sqlite.SQLiteDatabase;

import com.spencerpages.CoinPageCreator;
import com.spencerpages.DatabaseHelper;
import com.spencerpages.MainApplication;
import com.spencerpages.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RooseveltDimes extends CollectionInfo {

    private static final String COLLECTION_TYPE = "Dimes";

    private static final Integer START_YEAR = 1946;
    private static final Integer STOP_YEAR = CoinPageCreator.OPTVAL_STILL_IN_PRODUCTION;

    private static final int OBVERSE_IMAGE_COLLECTED = R.drawable.obv_roosevelt_dime_unc;
    private static final int OBVERSE_IMAGE_MISSING = R.drawable.openslot;

    private static final int REVERSE_IMAGE = R.drawable.rev_roosevelt_dime_unc;

    public String getCoinType() { return COLLECTION_TYPE; }

    public int getCoinImageIdentifier() { return REVERSE_IMAGE; }

    public int getCoinSlotImage(String identifier, String mint, Boolean inCollection){
        return inCollection ? OBVERSE_IMAGE_COLLECTED : OBVERSE_IMAGE_MISSING;
    }

    public void getCreationParameters(HashMap<String, Object> parameters) {

        parameters.put(CoinPageCreator.OPT_EDIT_DATE_RANGE, Boolean.FALSE);
        parameters.put(CoinPageCreator.OPT_START_YEAR, START_YEAR);
        parameters.put(CoinPageCreator.OPT_STOP_YEAR, STOP_YEAR);
        parameters.put(CoinPageCreator.OPT_SHOW_MINT_MARKS, Boolean.FALSE);
        parameters.put(CoinPageCreator.OPT_SHOW_P, Boolean.TRUE);
        parameters.put(CoinPageCreator.OPT_SHOW_D, Boolean.FALSE);
        parameters.put(CoinPageCreator.OPT_SHOW_S, Boolean.FALSE);
    }

    // TODO Perform validation and throw exception
    public void populateCollectionLists(HashMap<String, Object> parameters,
                                        ArrayList<String> identifierList,
                                        ArrayList<String> mintList) {

        Integer startYear       = (Integer) parameters.get(CoinPageCreator.OPT_START_YEAR);
        Integer stopYear        = (Integer) parameters.get(CoinPageCreator.OPT_STOP_YEAR);
        Boolean showMintMarks   = (Boolean) parameters.get(CoinPageCreator.OPT_SHOW_MINT_MARKS);
        Boolean showP           = (Boolean) parameters.get(CoinPageCreator.OPT_SHOW_P);
        Boolean showD           = (Boolean) parameters.get(CoinPageCreator.OPT_SHOW_D);
        Boolean showS           = (Boolean) parameters.get(CoinPageCreator.OPT_SHOW_S);

        for(int i = startYear; i <= stopYear; i++){

            if(showMintMarks){
                if(showP && i >= 1980){
                    identifierList.add(Integer.toString(i));
                    mintList.add("P");
                } else if(showP){
                    identifierList.add(Integer.toString(i));
                    mintList.add("");
                }
            } else {
                identifierList.add(Integer.toString(i));
                mintList.add("");
            }

            if(i != 1965 && i != 1966 && i != 1967){
                if(showMintMarks && showD){
                    identifierList.add(Integer.toString(i));
                    mintList.add("D");
                }
            }
            // if(i < 1975 && (i < 1956 || i > 1967)){
            // Greater than 1967 were only in proof sets
            if(i < 1975 && (i < 1956)){
                if(showMintMarks && showS){
                    identifierList.add(Integer.toString(i));
                    mintList.add("S");
                }
            }
        }
    }
    public String getAttributionString(){
        return MainApplication.DEFAULT_ATTRIBUTION;
    }

    public int onCollectionDatabaseUpgrade(SQLiteDatabase db, String tableName,
                                           int oldVersion, int newVersion) {

        int total = 0;

        if(oldVersion <= 3) {
            // Add in new 2013 coins if applicable
            int value = DatabaseHelper.addFromYear(db, tableName, "2013");
            total += value;
        }

        if (oldVersion <= 4) {
            // Add in new 2014 coins if applicable
            int value = DatabaseHelper.addFromYear(db, tableName, "2014");
            total += value;
        }

        if (oldVersion <= 6) {
            // Add in new 2015 coins if applicable
            int value = DatabaseHelper.addFromYear(db, tableName, "2015");
            total += value;
        }

        if (oldVersion <= 7) {
            // Add in new 2016 coins if applicable
            int value = DatabaseHelper.addFromYear(db, tableName, "2016");
            total += value;
        }

        if (oldVersion <= 8) {
            // Add in new 2017 coins if applicable
            int value = DatabaseHelper.addFromYear(db, tableName, "2017");
            total += value;
        }

        return total;
    }
}
