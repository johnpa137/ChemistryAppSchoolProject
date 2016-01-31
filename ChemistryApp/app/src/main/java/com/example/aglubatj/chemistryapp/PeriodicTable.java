package com.example.aglubatj.chemistryapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by John on 1/23/2016.
 */
public class PeriodicTable {
    // private Context mContext
    private static PeriodicTable pTable;
    private static ArrayList<Element> Elements;
    private static final int NUMBER_OF_ELEMENTS = 118;

    private PeriodicTable() { //Context context){
        // mContext = context;
        generateElements();
    }

    private void generateElements(){
        // String elementNames[] = mContext.getResources().getStringArray(R.array.elementNamesArray);
        // String elementSymbols[] = mContext.getResources().getStringArray(R.array.elementSymbolsArr);
        // int elementNumbers[] = mContext.getResources().getIntArray(R.array.elementNumberArray);
        // String elementWeights[] = mContext.getResources().getStringArray(R.array.elementWeightArray);
        // int elementGroups[] = mContext.getResources().getIntArray(R.array.elementGroupArray);
        // int elementPeriods[] = mContext.getResources().getIntArray(R.array.elementPeriodArray);

        Elements = new ArrayList<>(NUMBER_OF_ELEMENTS);

        for(int i = 0; i < NUMBER_OF_ELEMENTS; ++i){
            Element element = new Element(elementNames[i], elementSymbols[i], i+1, elementWeights[i], elementGroups[i], elementPeriods[i]);
            Elements.add(element);
        }
    }

    public Element getElement(int atomicNumber){
        return Elements.get(atomicNumber - 1);
    }

    public static PeriodicTable getPeriodicTable(){
            if(pTable == null)
                pTable = new PeriodicTable();
            return pTable;
    }

    private String elementNames[] = {
            "Hydrogen",
            "Helium",
            "Lithium",
            "Beryllium",
            "Boron",
            "Carbon",
            "Nitrogen",
            "Oxygen",
            "Fluorine",
            "Neon",
            "Sodium",
            "Magnesium",
            "Aluminium",
            "Silicon",
            "Phosphorus",
            "Sulfur",
            "Chlorine",
            "Argon",
            "Potassium",
            "Calcium",
            "Scandium",
            "Titanium",
            "Vanadium",
            "Chromium",
            "Manganese",
            "Iron",
            "Cobalt",
            "Nickel",
            "Copper",
            "Zinc",
            "Gallium",
            "Germanium",
            "Arsenic",
            "Selenium",
            "Bromine",
            "Krypton",
            "Rubidium",
            "Strontium",
            "Yttrium",
            "Zirconium",
            "Niobium",
            "Molybdenum",
            "Technetium",
            "Ruthenium",
            "Rhodium",
            "Palladium",
            "Silver",
            "Cadmium",
            "Indium",
            "Tin",
            "Antimony",
            "Tellurium",
            "Iodine",
            "Xenon",
            "Caesium",
            "Barium",
            "Lanthanum",
            "Cerium",
            "Praseodymium",
            "Neodymium",
            "Promethium",
            "Samarium",
            "Europium",
            "Gadolinium",
            "Terbium",
            "Dysprosium",
            "Holmium",
            "Erbium",
            "Thulium",
            "Ytterbium",
            "Lutetium",
            "Hafnium",
            "Tantalum",
            "Tungsten",
            "Rhenium",
            "Osmium",
            "Iridium",
            "Platinum",
            "Gold",
            "Mercury",
            "Thallium",
            "Lead",
            "Bismuth",
            "Polonium",
            "Astatine",
            "Radon",
            "Francium",
            "Radium",
            "Actinium",
            "Thorium",
            "Protactinium",
            "Uranium",
            "Neptunium",
            "Plutonium",
            "Americium",
            "Curium",
            "Berkelium",
            "Californium",
            "Einsteinium",
            "Fermium",
            "Mendelevium",
            "Nobelium",
            "Lawrencium",
            "Rutherfordium",
            "Dubnium",
            "Seaborgium",
            "Bohrium",
            "Hassium",
            "Meitnerium",
            "Darmstadtium",
            "Roentgenium",
            "Copernicium",
            "Ununtrium",
            "Ununquadium",
            "Ununpentium",
            "Ununhexium",
            "Ununseptium",
            "Ununoctium"
    };

    private String elementSymbols[] = {
            "H",
            "He",
            "Li",
            "Be",
            "B",
            "C",
            "N",
            "O",
            "F",
            "Ne",
            "Na",
            "Mg",
            "Al",
            "Si",
            "P",
            "S",
            "Cl",
            "Ar",
            "K",
            "Ca",
            "Sc",
            "Ti",
            "V",
            "Cr",
            "Mn",
            "Fe",
            "Co",
            "Ni",
            "Cu",
            "Zn",
            "Ga",
            "Ge",
            "As",
            "Se",
            "Br",
            "Kr",
            "Rb",
            "Sr",
            "Y",
            "Zr",
            "Nb",
            "Mo",
            "Tc",
            "Ru",
            "Rh",
            "Pd",
            "Ag",
            "Cd",
            "In",
            "Sn",
            "Sb",
            "Te",
            "I",
            "Xe",
            "Cs",
            "Ba",
            "La",
            "Ce",
            "Pr",
            "Nd",
            "Pm",
            "Sm",
            "Eu",
            "Gd",
            "Tb",
            "Dy",
            "Ho",
            "Er",
            "Tm",
            "Yb",
            "Lu",
            "Hf",
            "Ta",
            "W",
            "Re",
            "Os",
            "Ir",
            "Pt",
            "Au",
            "Hg",
            "Tl",
            "Pb",
            "Bi",
            "Po",
            "At",
            "Rn",
            "Fr",
            "Ra",
            "Ac",
            "Th",
            "Pa",
            "U",
            "Np",
            "Pu",
            "Am",
            "Cm",
            "Bk",
            "Cf",
            "Es",
            "Fm",
            "Md",
            "No",
            "Lr",
            "Rf",
            "Db",
            "Sg",
            "Bh",
            "Hs",
            "Mt",
            "Ds",
            "Rg",
            "Cp",
            "Uut",
            "Uuq",
            "Uup",
            "Uuh",
            "Uus",
            "Uuo"
    };

    private float elementWeights[] = {
            1.0079f,
            4.0026f,
            6.941f,
            9.0122f,
            10.811f,
            12.0107f,
            14.0067f,
            15.9994f,
            18.9984f,
            20.1797f,
            22.9897f,
            24.305f,
            26.9815f,
            28.0855f,
            30.9738f,
            32.065f,
            35.453f,
            39.948f,
            39.0983f,
            40.078f,
            44.9559f,
            47.867f,
            50.9415f,
            51.9961f,
            54.938f,
            55.845f,
            58.9332f,
            58.6934f,
            63.546f,
            65.39f,
            69.723f,
            72.64f,
            74.9216f,
            78.96f,
            79.904f,
            83.8f,
            85.4678f,
            87.62f,
            88.9059f,
            91.224f,
            92.9064f,
            95.94f,
            98f,
            101.07f,
            102.9055f,
            106.42f,
            107.8682f,
            112.411f,
            114.818f,
            118.71f,
            121.76f,
            127.6f,
            126.9045f,
            131.293f,
            132.9055f,
            137.327f,
            138.9055f,
            140.116f,
            140.9077f,
            144.24f,
            145f,
            150.36f,
            151.964f,
            157.25f,
            158.9253f,
            162.5f,
            164.9303f,
            167.259f,
            168.9342f,
            173.04f,
            174.967f,
            178.49f,
            180.9479f,
            183.84f,
            186.207f,
            190.23f,
            192.217f,
            195.078f,
            196.9665f,
            200.59f,
            204.3833f,
            207.2f,
            208.9804f,
            209f,
            210f,
            222f,
            223f,
            226f,
            227f,
            232.0381f,
            231.0359f,
            238.0289f,
            237f,
            244f,
            243f,
            247f,
            247f,
            251f,
            252f,
            257f,
            258f,
            259f,
            262f,
            267f,
            268f,
            271f,
            272f,
            270f,
            276f,
            281f,
            280f,
            285f,
            284f,
            289f,
            288f,
            293f,
            294f,
            294f,
    };

    private int elementGroups[] = {
            1,
            18,
            1,
            2,
            13,
            14,
            15,
            16,
            17,
            18,
            1,
            2,
            13,
            14,
            15,
            16,
            17,
            18,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17,
            18,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17,
            18,
            1,
            2,
            19,
            20,
            21,
            22,
            23,
            24,
            25,
            26,
            27,
            28,
            29,
            30,
            31,
            32,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17,
            18,
            1,
            2,
            19,
            20,
            21,
            22,
            23,
            24,
            25,
            26,
            27,
            28,
            29,
            30,
            31,
            32,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17,
            18,
    };

    private int elementPeriods[] = {
            1,
            1,

            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,

            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,

            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,

            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,

            6,
            6,

            8,
            8,
            8,
            8,
            8,
            8,
            8,
            8,
            8,
            8,
            8,
            8,
            8,
            8,

            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,

            7,
            7,

            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9,

            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
    };
}
