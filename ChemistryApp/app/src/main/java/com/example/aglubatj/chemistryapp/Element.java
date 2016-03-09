package com.example.aglubatj.chemistryapp;

/**
 * Class that stores data for each element

 * @author JP Aglubat
 * @version 3/6/2016
 */
public class Element {
    private String name;
    private String symbol;
    private int atomicNumber;
    private float atomicWeight;
    private int group;
    private int period;
    private String electronConfig;

    /**
     * Constructor.
     *
     * @param name name of element
     * @param symbol chemical symbol of
     * @param atomicNumber atomicNumber of element
     * @param atomicWeight atomic weight of element
     * @param group elemental group of element
     * @param period elemental period of element
     */
    public Element(String name, String symbol, int atomicNumber, float atomicWeight, int group, int period) {
        this.name = name;
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
        this.group = group;
        this.period = period;
        
        int truePeriod = this.period;
        if(truePeriod > 7){
            truePeriod -= 2;
        }

        StringBuilder configBuilder = new StringBuilder();
        int electronCount = this.atomicNumber;

        switch(truePeriod){
            case 1: configBuilder.append("1s<sup>"); break;
            case 2: configBuilder.append("[He] 2s<sup>");
                electronCount -= 2; break;
            case 3: configBuilder.append("[Ne] 3s<sup>");
                electronCount -= 10; break;
            case 4: configBuilder.append("[Ar] 4s<sup>");
                electronCount -= 18; break;
            case 5: configBuilder.append("[Kr] 5s<sup>");
                electronCount -= 36; break;
            case 6: configBuilder.append("[Xe] 6s<sup>");
                electronCount -= 54; break;
            case 7: configBuilder.append("[Rn] 7s<sup>");
                electronCount -= 86; break;
        }

        if(electronCount < 3){
            configBuilder.append(electronCount);
        }
        else{
            configBuilder.append(2);
            configBuilder.append("</sup>");
            electronCount -= 2;
            if(truePeriod > 1 && truePeriod < 4){
                configBuilder.append(truePeriod);
                configBuilder.append("p<sup>");
                configBuilder.append(electronCount);
            }
            else if(truePeriod < 6){
                configBuilder.append(truePeriod - 1);
                configBuilder.append("d<sup>");
                if(electronCount < 10){
                    configBuilder.append(electronCount);
                }
                else{
                    configBuilder.append(10);
                    electronCount -= 10;
                    if(electronCount > 0){
                        configBuilder.append("</sup>");
                        configBuilder.append(truePeriod);
                        configBuilder.append("p<sup>");
                        configBuilder.append(electronCount);
                    }
                }
            }
            else{
                configBuilder.append(truePeriod - 2);
                configBuilder.append("f<sup>");
                if(electronCount < 14){
                    configBuilder.append(electronCount);
                }
                else{
                    configBuilder.append(14);
                    electronCount -= 14;
                    if(electronCount > 0){
                        configBuilder.append("</sup>");
                        configBuilder.append(truePeriod - 1);
                        configBuilder.append("d<sup>");
                        if(electronCount < 10){
                            configBuilder.append(electronCount);
                        }
                        else{
                            configBuilder.append(10);
                            electronCount -= 10;
                            if(electronCount > 0){
                                configBuilder.append("</sup>");
                                configBuilder.append(truePeriod);
                                configBuilder.append("p<sup>");
                                configBuilder.append(electronCount);
                            }
                        }
                    }
                }
            }
        }
        configBuilder.append("</sup>");

        this.electronConfig = configBuilder.toString();
    }

    /**
     * getter for element name.
     *
     * @return name of element
     */
    public String getName() { return name; }

    /**
     * getter for element sybmol.
     *
     * @return symbol of element
     */
    public String getSymbol() { return symbol; }

    /**
     * getter for element name.
     *
     * @return name of element
     */
    public int getAtomicNumber() {
        return atomicNumber;
    }

    /**
     * getter for element weight.
     *
     * @return weight of element
     */
    public float getAtomicWeight() {
        return atomicWeight;
    }

    /**
     * getter for element group.
     *
     * @return grpup of element
     */
    public int getGroup() {
        int retValue = group;
        if(group > 18)
            retValue = 0;
        return retValue;
    }

    /**
     * getter for element period.
     *
     * @return period of element
     */
    public int getPeriod() {
        int retValue = period;
        if(period > 7)
            retValue -= 2;
        return retValue;
    }

    /**
     * returns this elements electron configuration
     *
     * @return electron configuration of element
     */
    public String getElectronConfig() {
        return electronConfig;
    }

    /**
     * getter for a test element object
     *
     * @return a test element object
     */
    public static Element getTestElement() {
        String name = "Iridium";
        String symbol = "Ir";
        int atomicNumber = 77;
        float atomicWeight = 192.217f;
        int group = 9;
        int period = 6;

        return new Element(name, symbol, atomicNumber, atomicWeight, group, period);
    }
}
