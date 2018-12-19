package com.vandendaelen.handles.object.tileentity;

import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileTardisInterfaceOC extends TileTardisInterfaceBase implements Environment, ITickable {
    private boolean initialized = false;
    protected Node node = null;

    public TileTardisInterfaceOC() {
        this.node = Network.newNode(this, Visibility.Network).withComponent(peripheralName).create();
    }

    protected void initialize() {
        Network.joinOrCreateNetwork(this);
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        // Make sure to remove the node from its network when its environment,
        // meaning this tile entity, gets unloaded.
        if (node != null) node.remove();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        // Make sure to remove the node from its network when its environment,
        // meaning this tile entity, gets unloaded.
        if (node != null) node.remove();
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        // The host check may be superfluous for you. It's just there to allow
        // some special cases, where getNode() returns some node managed by
        // some other instance (for example when you have multiple internal
        // nodes in this tile entity).
        if (node != null && node.host() == this) {
            // This restores the node's address, which is required for networks
            // to continue working without interruption across loads. If the
            // node is a power connector this is also required to restore the
            // internal energy buffer of the node.
            node.load(nbt.getCompoundTag("oc:node"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        // See readFromNBT() regarding host check.
        if (node != null && node.host() == this) {
            final NBTTagCompound nodeNbt = new NBTTagCompound();
            node.save(nodeNbt);
            nbt.setTag("oc:node", nodeNbt);
        }

        return nbt;
    }

    @Override
    public Node node() {
        return node;
    }

    @Override
    public void onConnect(Node node) {

    }

    @Override
    public void onDisconnect(Node node) {

    }

    @Override
    public void onMessage(Message message) {

    }

    @Override
    public void update() {
        if(!this.initialized && !this.isInvalid()) {
            initialize();
            this.initialized = true;
        }
    }

    //LUA Methods
    @Callback
    public Object[] getTardisPos(Context context, Arguments args) {
        return getTardisPos(getTardis());
    }

    @Callback
    public Object[] setTardisDestination(Context context, Arguments args) {
        return setTardisDestination(args.toArray(),getTardis());
    }

    @Callback
    public Object[] getTardisDestination(Context context, Arguments args) {
        return getTardisDestination(getTardis());
    }

    @Callback
    public Object[] startFlight(Context context, Arguments args) {
        return startFlight(getTardis());
    }

    @Callback
    public Object[] setDoors(Context context, Arguments args) {
        return setDoors(args.toArray(),getTardis());
    }

    @Callback
    public Object[] isInFlight(Context context, Arguments args) {
        return isInFlight(getTardis());
    }

    @Callback
    public Object[] getFuel(Context context, Arguments args) {
        return getFuel(getTardis());
    }

    @Callback
    public Object[] setFueling(Context context, Arguments args) {
        return setFueling(args.toArray(),getTardis());
    }

    @Callback
    public Object[] isDoorsOpened(Context context, Arguments args) {
        return isDoorsOpened(getTardis());
    }

    @Callback
    public Object[] canFly(Context context, Arguments args) {
        return canFly(getTardis());
    }

    @Callback
    public Object[] getTravelTime(Context context, Arguments args) {
        return getTravelTime(getTardis());
    }

    @Callback
    public Object[] getWaypoint(Context context, Arguments args) {
        return getWaypoint(args.toArray(),getTardis());
    }

    @Callback
    public Object[] setWaypoint(Context context, Arguments args) {
        return setWaypoint(args.toArray(),getTardis());
    }

    @Callback
    public Object[] getHealthComponent(Context context, Arguments args) {
        return getHealthComponent(args.toArray(),getTardis());
    }

    @Callback
    public Object[] getDimensionsID(Context context, Arguments args) {
        return getDimensionsID();
    }

    @Callback
    public Object[] getDimensionName(Context context, Arguments args) {
        return getDimensionName(args.toArray());
    }

    @Callback
    public Object[] setRelativePos(Context context, Arguments args) {
        return setRelativePos(args.toArray(),getTardis());
    }

    @Callback
    public Object[] setDimensionPos(Context context, Arguments args) {
        return setDimensionPos(args.toArray(),getTardis());
    }
}
