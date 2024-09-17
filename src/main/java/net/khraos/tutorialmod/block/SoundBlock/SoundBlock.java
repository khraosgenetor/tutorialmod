package net.khraos.tutorialmod.block.SoundBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SoundBlock extends Block {
    public SoundBlock(Properties pProperties) {
        super(pProperties);
    }

    public static final String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        int randomNoteIndex = (int) (Math.random() * notes.length); // Pick a random note index
        String randomNote = notes[randomNoteIndex];

        // Calculate pitch based on the note (replace with your desired mapping)
        float rPitch = calculatePitch(randomNote);

        float rVol = 1f + (float) (Math.random() * (10f - 1f));
        pLevel.playSound(pPlayer, pPos, SoundEvents.NOTE_BLOCK_BASEDRUM.get(), SoundSource.BLOCKS, rVol, rPitch);
        return InteractionResult.SUCCESS;
    }

    private float calculatePitch(String note) {
        switch (note) {
            case "C":
                return 60f;
            case "C#":
                return 61f;
            case "D":
                return 62f;
            case "D#":
                return 63f;
            case "E":
                return 64f;
            case "F":
                return 65f;
            case "F#":
                return 66f;
            case "G":
                return 67f;
            case "G#":
                return 68f;
            case "A":
                return 69f;
            case "A#":
                return 70f;
            case "B":
                return 71f;
            default:
                return 60f; // Default to C if note not found
        }
    }
}
