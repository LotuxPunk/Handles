package com.vandendaelen.handles.object.waypoint;

import net.minecraft.util.math.BlockPos;

public class Waypoint {
    public BlockPos pos;
    public int dimension;

    public Waypoint(BlockPos pos, int dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}
