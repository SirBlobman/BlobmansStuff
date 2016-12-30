package com.SirBlobman.stuff.world.dimension.slime;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraftforge.event.terraingen.TerrainGen;

public class SlimeCG implements IChunkGenerator
{
	private final World worldObj;
	private Random random;
	private Biome[] biomes;
	
	private List<SpawnListEntry> mobs = Lists.newArrayList();
	private MapGenBase caveGen = new MapGenCaves();
	private NormalTerrainGenerator terrainGen = new NormalTerrainGenerator();
	
	public SlimeCG(World worldObj)
	{
		this.worldObj = worldObj;
		long seed = worldObj.getSeed();
		this.random = new Random((seed + 516) * 314);
		terrainGen.setup(worldObj, random);
		caveGen = TerrainGen.getModdedMapGen(caveGen, CAVE);
	}
	
	@Override
	public Chunk provideChunk(int x, int z)
	{
		ChunkPrimer cp = new ChunkPrimer();
		
	}
}