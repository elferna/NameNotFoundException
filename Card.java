/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author Zac
 */
public class Card {

    private String name;
    private BufferedImage img = null;
    private char type;//room,suspect,
    private int x;
    private int y;
    private boolean movedHere;
    
    
    /**
     * Card constructor
     * @param _name
     * @param fileName
     */
    public Card(String name, String fileName, char type){
        this.name = name;
        this.type = type;
        this.movedHere = false;
        try {
            this.img = ImageIO.read(new File(fileName));
        }   
        catch (IOException e) {
        }
    }
    public Card(String name, int x, int y, String fileName, char type){
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
        try {
            this.img = ImageIO.read(new File(fileName));
        }   
        catch (IOException e) {
        }
    }

    public char typeOfCard(){
    	return this.type;
    }
    public void setX(int x)
    {
    	this.x = x;
    }
    public int getX()
    {
    	return this.x;
    }
    public void setY(int y)
    {
    	this.y = y;
    }
    public int getY()
    {
    	return this.y;
    }
	public boolean isMovedHere() {
		return movedHere;
	}
	public void setMovedHere(boolean movedHere) {
		this.movedHere = movedHere;
	}
	public void updateLocation(Position p, boolean movedHere) {
		this.setX(p.getX());
		this.setY(p.getY());
		this.movedHere = movedHere;
	}
    @Override
    public String toString(){
    	return this.name;
    }
	@Override
	public boolean equals(Object obj) {
		boolean equivalent = false;
		try{
			Card c = (Card)obj;
			if(this.img != null){
				if(this.img.equals(c.img)){
					if(this.name.equals(c.name)){
						if(this.type==c.type){
							if(this.x ==c.x){
								if(this.y==c.y){
									equivalent = true;
								}
							}
						}
					}
				}
			}
			else{
				if(this.img == c.img){
					if(this.name.equals(c.name)){
						if(this.type==c.type){
							if(this.x ==c.x){
								if(this.y==c.y){
									equivalent = true;
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("incompatible obj types");
		}
		return equivalent;
	}
}
