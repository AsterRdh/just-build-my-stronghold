package com.aster.justbuildmystronghold.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

public class FlameParticle extends TextureSheetParticle {


    protected FlameParticle(ClientLevel level, double xC, double yC, double zC, double xD, double yD, double zD) {
        super(level, xC, yC, zC, xD, yD, zD);


    }

    @Override
    public ParticleRenderType getRenderType() {
        return null;
    }
}
