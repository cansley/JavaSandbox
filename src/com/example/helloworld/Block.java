package com.example.helloworld;

/**
 * Created by Charles on 12/23/2015.
 */
public class Block {
    private int width;
    private int length;
    private int height;

    public Block(int width, int length, int height){
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getLength(){
        return this.length;
    }

    public int getHeight(){
        return this.height;
    }

    public int getVolume(){
        return this.length * this.width * this.height;
    }

    public int getSurfaceArea(){
        return ((length * height) * 2) + ((width*height)*2) + ((length*width)*2);
    }
}
