package com.Zrips.CMI.Containers;

import org.bukkit.Axis;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CMIBlock {
    public static enum blockDirection {
	none(-1), upWest(0), upEast(1), upNorth(2), upSouth(3), downWest(4), downEast(5), downNorth(6), downSouth(7);

	private int dir;

	blockDirection(int dir) {
	    this.dir = dir;
	}

	public int getDir() {
	    return dir;
	}

	public static blockDirection getByDir(int dir) {
	    for (blockDirection one : blockDirection.values()) {
		if (one.getDir() == dir)
		    return one;
	    }
	    return null;
	}
    }

    public static enum FlipDirection {
	NORTH_SOUTH,
	WEST_EAST,
	UP_DOWN
    }

    public static enum Bisect {
	TOP,
	BOTTOM
    }

    public static enum BedPart {
	HEAD,
	FOOT

    }

    public static enum StairShape {
	INNER_LEFT,
	INNER_RIGHT,
	OUTER_LEFT,
	OUTER_RIGHT,
	STRAIGHT;

	public static StairShape getByName(String name) {
	    for (StairShape one : StairShape.values()) {
		if (one.toString().equalsIgnoreCase(name))
		    return one;
	    }
	    return null;
	}
    }

    private Block block;
    private Integer data = null;
    private Object blockd = null;

    public CMIBlock(Block block) {
	this.block = block;
    }

    public Inventory getShulkerInv() {
	return null;

    }

    @Deprecated
    public blockDirection getDirection() {

	return blockDirection.getByDir(block.getData()) == null ? blockDirection.none : blockDirection.getByDir(block.getData());
    }

    public boolean isWaterlogged() {

	return false;
    }

    public Bisect getBisect() {

	return null;
    }

    public boolean isAttached() {

	return false;
    }

    public Axis getAxis() {

	return null;
    }

    public BedPart getBedPart() {

	return null;
    }

    public StairShape getStairShape() {

	return null;
    }

    public BlockFace getFacing() {

	return null;
    }

    public CMIBlock flip(FlipDirection direction, boolean angle) {

	return this;
    }

    public CMIBlock rotate90Reverse() {

	return this;

    }

    public CMIBlock rotate90() {

	return this;
    }

    public Object getData() {
	return data.byteValue();
    }

    public CMIBlock setData(Object data) {
	return this;
    }

    public boolean hasInventory() {
	return block.getState() instanceof InventoryHolder;
    }

    public Inventory getInventory() {

	return null;
    }

    public Block getSecondaryBedBlock() {

	return null;
    }

    public Block getBedFootBlock() {

	return null;
    }
}
