package org.KViEternal.combat_maid.client.entity.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class Combat_Maid_Suit_Model extends Model {
	public final ModelPart Body;
	public final ModelPart Skirt;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;

	public Combat_Maid_Suit_Model(ModelPart root) {
        super(RenderLayer::getArmorCutoutNoCull);
		this.Body = root.getChild("Body");
		this.Skirt = root.getChild("Skirt");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = Body.addChild("cube_r1", ModelPartBuilder.create().uv(8, 0).cuboid(-3.0F, -3.0F, 0.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.0F, -4.0F, -0.6981F, 0.0F, -3.1416F));

		ModelPartData cube_r2 = Body.addChild("cube_r2", ModelPartBuilder.create().uv(8, 0).cuboid(-3.0F, -0.463F, 0.6997F, 6.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.0F, -4.0F, 0.3927F, 0.0F, -3.1416F));

		ModelPartData Skirt = modelPartData.addChild("Skirt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

		ModelPartData skirt08_r1 = Skirt.addChild("skirt08_r1", ModelPartBuilder.create().uv(16, 33).cuboid(-1.75F, 8.0F, -2.0F, 1.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		ModelPartData skirt07_r1 = Skirt.addChild("skirt07_r1", ModelPartBuilder.create().uv(18, 36).cuboid(3.8994F, 1.716F, -3.9846F, 2.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 7.0F, 0.0F, -0.2703F, -0.4075F, -0.0385F));

		ModelPartData skirt06_r1 = Skirt.addChild("skirt06_r1", ModelPartBuilder.create().uv(18, 36).cuboid(0.8F, 8.0F, -0.1F, 2.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2703F, 0.4075F, -0.0385F));

		ModelPartData skirt05_r1 = Skirt.addChild("skirt05_r1", ModelPartBuilder.create().uv(18, 36).cuboid(-2.9F, 8.0F, -0.1F, 2.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2703F, -0.4075F, 0.0385F));

		ModelPartData skirt04_r1 = Skirt.addChild("skirt04_r1", ModelPartBuilder.create().uv(18, 36).cuboid(-0.7911F, 0.6141F, -0.8716F, 2.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 7.0F, -1.0F, -0.2703F, 0.4075F, 0.0385F));

		ModelPartData skirt03_r1 = Skirt.addChild("skirt03_r1", ModelPartBuilder.create().uv(16, 33).cuboid(0.75F, 8.0F, -2.0F, 1.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		ModelPartData skirt02_r1 = Skirt.addChild("skirt02_r1", ModelPartBuilder.create().uv(1, 33).cuboid(-3.0F, 0.3381F, -0.9852F, 6.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, 2.0F, 0.48F, 0.0F, 0.0F));

		ModelPartData skirt01_r1 = Skirt.addChild("skirt01_r1", ModelPartBuilder.create().uv(1, 33).cuboid(-3.0F, 0.3381F, -0.0148F, 6.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -2.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData RightArm = modelPartData.addChild("RightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData LeftArm = modelPartData.addChild("LeftArm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	public void renderSkirt(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Skirt.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	public void renderArm(MatrixStack matrices, boolean isLeft, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		if(isLeft){
			LeftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		}
		else {
			RightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		}
	}
}