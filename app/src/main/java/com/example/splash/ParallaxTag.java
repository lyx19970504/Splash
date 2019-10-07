package com.example.splash;

public class ParallaxTag {

    protected float xIn;
    protected float xOut;
    protected float yIn;
    protected float yOut;
    protected float aIn;
    protected float aOut;

    @Override
    public String toString() {
        return "ParallaxViewTag [xIn=" + xIn + ", xOut="
                + xOut + ", yIn=" + yIn + ", yOut=" + yOut + ", alphaIn="
                + aIn + ", alphaOut=" + aOut + "]";
    }
}
