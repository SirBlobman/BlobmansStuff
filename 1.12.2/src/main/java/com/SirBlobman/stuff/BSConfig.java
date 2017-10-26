package com.SirBlobman.stuff;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public final class BSConfig {
    private static final File FOLDER = BlobmansStuff.FOLDER;
    private static final File FILE = new File(FOLDER, "Blobman's Stuff.cfg");
    private static Configuration config = new Configuration(FILE, false);
    
    public static int BLUE_SLIME_AMOUNT;
    public static int BLUE_SLIME_SIZE;
    public static float BLUE_SLIME_JOCKEY_CHANCE;
    public static void load() {
        config.load();
        BLUE_SLIME_AMOUNT           = config.getInt("blue slime amount", "entity.creeper slime.explosion", 5, 1, 20, "How many blue slimes will the creeper slime spawn when it explodes? Will be doubles if the creeper slime is powered.");
        BLUE_SLIME_SIZE             = config.getInt("blue slime size", "entity.creeper slime.explosion", 1, 1, 10, "What size will the blue slimes be?");
        BLUE_SLIME_JOCKEY_CHANCE    = config.getFloat("jockey chance", "entity.blue slime", 10.0F, 1.0F, 100.0F, "What is the chance (out of 100) that a blue slime jocket will spawn? (blue slime riding a spider)");
        config.save();
    }
}