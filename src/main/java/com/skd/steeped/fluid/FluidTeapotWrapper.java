package com.skd.steeped.fluid;

import com.skd.steeped.core.Config;
import com.skd.steeped.core.Registration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.common.Tags;

public class FluidTeapotWrapper implements IFluidHandlerItem {
    protected ItemStack container;
    protected FluidStack fluid = FluidStack.EMPTY;

    public FluidTeapotWrapper(ItemStack container) {
        this.container = container;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return FluidStack.EMPTY;
    }

    @Override
    public int getTankCapacity(int tank) {
        return Config.SERVER.teapot.teapotCapacity();
    }

    public static boolean isWater(Fluid fluid) {
        return fluid.isSame(Fluids.WATER) || fluid.isSame(Fluids.FLOWING_WATER);
    }

    public static boolean isMilk(Fluid fluid) {
        return fluid.is(Tags.Fluids.MILK) || fluid.getBucket() == Items.MILK_BUCKET;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack fluid) {
        return isWater(fluid.getFluid()) || isMilk(fluid.getFluid());
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (container.getCount() != 1 || !isFluidValid(0, resource) || resource.getAmount() < Config.SERVER.teapot.teapotCapacity()) {
            return 0;
        }
        if (action.execute()) {
            if (isWater(resource.getFluid())) {
                this.container = new ItemStack(Registration.TEAPOT_WATER.get());
            } else if (isMilk(resource.getFluid())) {
                this.container = new ItemStack(Registration.TEAPOT_MILK.get());
            }
        }
        return Config.SERVER.teapot.teapotCapacity();
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return FluidStack.EMPTY;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return FluidStack.EMPTY;
    }

    @Override
    public ItemStack getContainer() {
        return container;
    }
}
