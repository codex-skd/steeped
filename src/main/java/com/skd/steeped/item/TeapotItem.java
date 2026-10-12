package com.skd.steeped.item;

import com.skd.steeped.core.Config;
import com.skd.steeped.core.Registration;
import com.skd.steeped.fluid.FluidTeapotWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import java.util.Optional;

public class TeapotItem extends TooltipItem {
    public TeapotItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        var rayTrace = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
        if (rayTrace.getType() != net.minecraft.world.phys.HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(stack);
        }

        BlockPos pos = rayTrace.getBlockPos();
        Direction side = rayTrace.getDirection();
        BlockState state = world.getBlockState(pos);

        if (!world.mayInteract(player, pos) || !player.mayUseItemAt(pos.relative(side), side, stack)) {
            return InteractionResultHolder.fail(stack);
        }

        ItemStack filledStack = ItemStack.EMPTY;
        if (state.getBlock() instanceof BucketPickup bucketPickup) {
            if (FluidTeapotWrapper.isWater(state.getFluidState().getType()) && Config.SERVER.teapot.infiniteWater()) {
                filledStack = new ItemStack(Registration.TEAPOT_WATER.get());
                Optional<SoundEvent> sound = bucketPickup.getPickupSound(state);
                if (sound.isPresent()) {
                    player.playSound(sound.get(), 1.0f, 1.0f);
                }
            }

            if (filledStack.isEmpty()) {
                var actionResult = FluidUtil.tryPickUpFluid(stack, player, world, pos, side);
                if (actionResult.isSuccess()) {
                    filledStack = actionResult.getResult();
                }
            }
        }

        if (!filledStack.isEmpty()) {
            ItemStack filledResult = ItemUtils.createFilledResult(stack, player, filledStack);
            return InteractionResultHolder.sidedSuccess(filledResult, world.isClientSide());
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (Config.SERVER.teapot.canMilkCows() && target instanceof Cow) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack.copy(), player, new ItemStack(Registration.TEAPOT_MILK.get())));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
