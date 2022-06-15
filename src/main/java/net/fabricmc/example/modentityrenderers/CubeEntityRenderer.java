package net.fabricmc.example.modentityrenderers;

import net.fabricmc.example.EntityTestingClient;
import net.fabricmc.example.entities.CubeEntity;
import net.fabricmc.example.models.CubeEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityModel> {

    public CubeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CubeEntityModel(context.getPart(EntityTestingClient.MODEL_CUBE_LAYER)), 0.5F);

    }

    @Override
    public Identifier getTexture(CubeEntity entity){
        return new Identifier("base_mod", "textures/entity/cube/cube.png");
    }
}
