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

import com.spencerpages.CoinPageCreator;
import com.spencerpages.R;

import java.util.ArrayList;
import java.util.HashMap;

public class NationalParkQuarters extends CollectionInfo {

    private static final String COLLECTION_TYPE = "National Park Quarters";

    private static final String[] PARKS_COIN_IDENTIFIERS = {
            "Hot Springs",
            "Yellowstone",
            "Yosemite",
            "Grand Canyon",
            "Mt. Hood",
            "Gettysburg",
            "Glacier",
            "Olympic",
            "Vicksburg",
            "Chickasaw",
            "El Yunque",
            "Chaco Culture",
            "Acadia",
            "Hawaii Volcanoes",
            "Denali",
            "White Mountain",
            "Perry’s Victory",
            "Great Basin",
            "Fort McHenry",
            "Mount Rushmore",
            "Great Smoky Mountains",
            "Shenandoah",
            "Arches",
            "Great Sand Dunes",
            "Everglades",
            "Homestead",
            "Kisatchie",
            "Blue Ridge",
            "Bombay Hook",
            "Saratoga",
            "Shawnee",
            "Cumberland Gap",
            "Harper’s Ferry",
            "Theodore Roosevelt",
            "Fort Moultrie",
            "Effigy Mounds",
            "Frederick Douglass",
            "Ozark Riverways",
            "Ellis Island",
            "George Rogers Clark",
            /*
            "Pictured Rocks",
            "Apostle Islands",
            "Voyageurs",
            "Cumberland Island",
            "Block Island",
            "Lowell",
            "American Memorial",
            "War in the Pacific",
            "San Antonio Missions",
            "Frank Church River of No Return",
            "National Park of American Samoa",
            "Weir Farm",
            "Salt River Bay",
            "Marsh-Billings-Rockefeller",
            "Tallgrass Prairie",
            "Tuskegee Airmen"
            */
    };

    private static final Integer[][] PARKS_IMAGE_IDENTIFIERS = {
            { R.drawable.parks_2010_hot_springs_unc,            R.drawable.parks_2010_hot_springs_unc_25},
            { R.drawable.parks_2010_yellowstone_unc,            R.drawable.parks_2010_yellowstone_unc_25},
            { R.drawable.parks_2010_yosemite_unc,               R.drawable.parks_2010_yosemite_unc_25},
            { R.drawable.parks_2010_grand_canyon_unc,           R.drawable.parks_2010_grand_canyon_unc_25},
            { R.drawable.parks_2010_mount_hood_unc,             R.drawable.parks_2010_mount_hood_unc_25},
            { R.drawable.parks_2011_gettysburg_unc,             R.drawable.parks_2011_gettysburg_unc_25},
            { R.drawable.parks_2011_glacier_unc,                R.drawable.parks_2011_glacier_unc_25},
            { R.drawable.parks_2011_olympic_unc,                R.drawable.parks_2011_olympic_unc_25},
            { R.drawable.parks_2011_vicksburg_unc,              R.drawable.parks_2011_vicksburg_unc_25},
            { R.drawable.parks_2011_chickasaw_unc,              R.drawable.parks_2011_chickasaw_unc_25},
            { R.drawable.parks_2012_el_yunque_unc,              R.drawable.parks_2012_el_yunque_unc_25},
            { R.drawable.parks_2012_chaco_culture_unc,          R.drawable.parks_2012_chaco_culture_unc_25},
            { R.drawable.parks_2012_acadia_unc,                 R.drawable.parks_2012_acadia_unc_25},
            { R.drawable.parks_2012_hawaii_volcanoes_unc,       R.drawable.parks_2012_hawaii_volcanoes_unc_25},
            { R.drawable.parks_2012_denali_unc,                 R.drawable.parks_2012_denali_unc_25},
            { R.drawable.parks_2013_white_mountain_unc,         R.drawable.parks_2013_white_mountain_unc_25},
            { R.drawable.parks_2013_perrys_victory_unc,         R.drawable.parks_2013_perrys_victory_unc_25},
            { R.drawable.parks_2013_great_basin_unc,            R.drawable.parks_2013_great_basin_unc_25},
            { R.drawable.parks_2013_fort_mchenry_unc,           R.drawable.parks_2013_fort_mchenry_unc_25},
            { R.drawable.parks_2013_mount_rushmore_unc,         R.drawable.parks_2013_mount_rushmore_unc_25},
            { R.drawable.parks_2014_great_smoky_mountains_unc,  R.drawable.parks_2014_great_smoky_mountains_unc_25},
            { R.drawable.parks_2014_shenandoah_unc,             R.drawable.parks_2014_shenandoah_unc_25},
            { R.drawable.parks_2014_arches_unc,                 R.drawable.parks_2014_arches_unc_25},
            { R.drawable.parks_2014_great_sand_dunes_unc,       R.drawable.parks_2014_great_sand_dunes_unc_25},
            { R.drawable.parks_2014_everglades_unc,             R.drawable.parks_2014_everglades_unc_25},
            { R.drawable.parks_2015_homestead_unc,              R.drawable.parks_2015_homestead_unc_25},
            { R.drawable.parks_2015_kisatchie_unc,              R.drawable.parks_2015_kisatchie_unc_25},
            { R.drawable.parks_2015_blue_ridge_unc,             R.drawable.parks_2015_blue_ridge_unc_25},
            { R.drawable.parks_2015_bombay_hook_unc,            R.drawable.parks_2015_bombay_hook_unc_25},
            { R.drawable.parks_2015_saratoga_unc,               R.drawable.parks_2015_saratoga_unc_25},
            { R.drawable.parks_2016_shawnee_unc,                R.drawable.parks_2016_shawnee_unc_25},
            { R.drawable.parks_2016_cumberland_gap_unc,         R.drawable.parks_2016_cumberland_gap_unc_25},
            { R.drawable.parks_2016_harpers_ferry_unc,          R.drawable.parks_2016_harpers_ferry_unc_25},
            { R.drawable.parks_2016_theodore_roosevelt_unc,     R.drawable.parks_2016_theodore_roosevelt_unc_25},
            { R.drawable.parks_2016_fort_moultrie_unc,          R.drawable.parks_2016_fort_moultrie_unc_25},
            { R.drawable.parks_2017_effigy_mounds_proof,        R.drawable.parks_2017_effigy_mounds_proof_25},
            { R.drawable.parks_2017_frederick_douglass_proof,   R.drawable.parks_2017_frederick_douglass_proof_25},
            { R.drawable.parks_2017_ozark_riverways_proof,      R.drawable.parks_2017_ozark_riverways_proof_25},
            { R.drawable.parks_2017_ellis_island_proof,         R.drawable.parks_2017_ellis_island_proof_25},
            { R.drawable.parks_2017_george_rogers_clark_proof,  R.drawable.parks_2017_george_rogers_clark_proof_25},
    };

    private static final HashMap<String, Integer[]> PARKS_INFO = new HashMap<>();

    static {
        // Populate the PARKS_INFO HashMap for quick image ID lookups later
        for (int i = 0; i < PARKS_COIN_IDENTIFIERS.length; i++){
            PARKS_INFO.put(PARKS_COIN_IDENTIFIERS[i], PARKS_IMAGE_IDENTIFIERS[i]);
        }
    }

    private static final int REVERSE_IMAGE = R.drawable.parks_2010_grand_canyon_unc;

    public String getCoinType() { return COLLECTION_TYPE; }

    public int getCoinImageIdentifier() { return REVERSE_IMAGE; }

    public int getCoinSlotImage(String identifier, String mint, Boolean inCollection){
        return PARKS_INFO.get(identifier)[inCollection ? 0 : 1];
    }

    public void getCreationParameters(HashMap<String, Object> parameters) {

        parameters.put(CoinPageCreator.SHOW_MINT_MARKS, Boolean.FALSE);
        parameters.put(CoinPageCreator.SHOW_P, Boolean.TRUE);
        parameters.put(CoinPageCreator.SHOW_D, Boolean.FALSE);
    }

    // TODO Perform validation and throw exception
    public void populateCollectionArrays(HashMap<String, Object> parameters,
                                         ArrayList<String> identifierList,
                                         ArrayList<String> mintList) {

        Boolean showMintMarks   = (Boolean) parameters.get(CoinPageCreator.SHOW_MINT_MARKS);
        Boolean showP           = (Boolean) parameters.get(CoinPageCreator.SHOW_P);
        Boolean showD           = (Boolean) parameters.get(CoinPageCreator.SHOW_D);

        for(int i = 0; i < PARKS_COIN_IDENTIFIERS.length; i++){

            String identifier = PARKS_COIN_IDENTIFIERS[i];

            if(showMintMarks){
                if(showP){
                    identifierList.add(identifier);
                    mintList.add(" P");
                }
                if(showD){
                    identifierList.add(identifier);
                    mintList.add(" D");
                }
            } else {
                identifierList.add(identifier);
                mintList.add("");
            }
        }
    }
}