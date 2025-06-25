package com.mvks.block.custom;

import com.mvks.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagicBlock extends Block {

    public MagicBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        world.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1f, 1f);
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (!world.isClient && itemEntity.getStack().getItem() == ModItems.RAW_PINK_GARNET) {

                int count = itemEntity.getStack().getCount();

                itemEntity.setStack(new ItemStack(Items.DIAMOND, count));

                for (int i = 0; i < 20; i++) {
                    double offsetX = (world.random.nextDouble() - 0.5) * 0.5;
                    double offsetY = world.random.nextDouble() * 0.5;
                    double offsetZ = (world.random.nextDouble() - 0.5) * 0.5;

                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.END_ROD,
                            itemEntity.getX(),
                            itemEntity.getY() + 0.1,
                            itemEntity.getZ(),
                            1,
                            offsetX,
                            offsetY,
                            offsetZ,
                            0.01
                    );
                }

                // Відтворення звуку
                world.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
            }
        }
    }
}