package net.theobl.extension.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.theobl.extension.util.ModUtil;

public class HeldItemNameCommand {
    private static final DynamicCommandExceptionType ERROR_NOT_LIVING_ENTITY = new DynamicCommandExceptionType(
            p_304205_ -> Component.translatableEscape("commands.enchant.failed.entity", p_304205_)
    );
    private static final DynamicCommandExceptionType ERROR_NO_ITEM = new DynamicCommandExceptionType(
            p_304207_ -> Component.translatableEscape("commands.enchant.failed.itemless", p_304207_)
    );
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> setHeldItemName = dispatcher.register(
                Commands.literal("helditemname")
                        .then(
                                Commands.literal("set")
                                        .then(
                                                Commands.argument("name", MessageArgument.message()).executes(
                                                        c -> setItemName(
                                                                c.getSource(),
                                                                c.getSource().getEntityOrException(),
                                                                MessageArgument.getMessage(c, "name")
                                                        )
                                                )
                                        )
                        )
                        .then(
                                Commands.literal("reset").executes(
                                        c -> resetItemName(
                                                c.getSource(),
                                                c.getSource().getEntityOrException()
                                        )
                                )
                        )
        );
        dispatcher.register(Commands.literal("itemname").redirect(setHeldItemName));
    }

    public static int setItemName(CommandSourceStack source, Entity entity, Component name) throws CommandSyntaxException {
        if(entity instanceof LivingEntity livingEntity) {
            ItemStack itemstack = livingEntity.getMainHandItem();
            if(!itemstack.isEmpty()) {
                itemstack.set(DataComponents.CUSTOM_NAME, ModUtil.applyChatFormatting(name, itemstack.getRarity()));
            }
            else {
                throw ERROR_NO_ITEM.create(entity.getName().getString());
            }
        }
        else {
            throw ERROR_NOT_LIVING_ENTITY.create(entity.getName().getString());
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int resetItemName(CommandSourceStack source, Entity entity) throws CommandSyntaxException {
        if(entity instanceof LivingEntity livingEntity) {
            ItemStack itemstack = livingEntity.getMainHandItem();
            if(!itemstack.isEmpty()) {
                    itemstack.remove(DataComponents.CUSTOM_NAME);
            }
            else {
                throw ERROR_NO_ITEM.create(entity.getName().getString());
            }
        }
        else {
            throw ERROR_NOT_LIVING_ENTITY.create(entity.getName().getString());
        }
        return Command.SINGLE_SUCCESS;
    }
}
