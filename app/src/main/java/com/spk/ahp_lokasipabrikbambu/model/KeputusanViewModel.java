package com.spk.ahp_lokasipabrikbambu.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by wisnu on 12/23/17.
 */

public class KeputusanViewModel implements Serializable {
    public static final String DATA_KEPUTUSAN_KEY = "data-keputusan-key";

    public Map<String, Float> kriteriaToBobotMap;
    public Map<String, Float> alternatifToBobotMap;

}