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
		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(16, 26).cuboid(-4.0F, 9.7F, -2.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.06F))
				.uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 9.95F, 4.0F, new Dilation(0.01F))
				.uv(2, 44).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 9.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = Body.addChild("cube_r1", ModelPartBuilder.create().uv(8, 0).cuboid(-2.999F, -1.3579F, -1.0943F, 6.0F, 3.0F, 2.0F, new Dilation(0.049F)), ModelTransform.of(0.001F, 4.2886F, -1.8598F, -0.829F, 0.0F, -3.1416F));

		ModelPartData cube_r2 = Body.addChild("cube_r2", ModelPartBuilder.create().uv(8, 0).cuboid(-3.0F, -2.5F, -0.9998F, 6.0F, 5.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(0.0F, 2.212F, -1.7702F, 0.48F, 0.0F, -3.1416F));

		ModelPartData Skirt = modelPartData.addChild("Skirt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

		ModelPartData cube_r3 = Skirt.addChild("cube_r3", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -0.95F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.3668F, 9.9905F, -0.2558F, 0.0175F, 0.9539F, 0.3801F));

		ModelPartData cube_r4 = Skirt.addChild("cube_r4", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -0.95F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.1897F, 10.1082F, -0.1082F, -0.0262F, -0.9539F, -0.3801F));

		ModelPartData cube_r5 = Skirt.addChild("cube_r5", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.1062F, 10.0224F, -1.1135F, -1.7628F, -1.3614F, 1.4735F));

		ModelPartData cube_r6 = Skirt.addChild("cube_r6", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.0733F, 10.0F, -1.2142F, -0.2124F, -0.1571F, -0.2474F));

		ModelPartData cube_r7 = Skirt.addChild("cube_r7", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.043F, 10.0F, -1.9355F, -0.6591F, -1.0091F, 0.4713F));

		ModelPartData cube_r8 = Skirt.addChild("cube_r8", ModelPartBuilder.create().uv(3, 24).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.043F, 10.0F, -1.9355F, -0.3679F, 0.4346F, -0.2699F));

		ModelPartData cube_r9 = Skirt.addChild("cube_r9", ModelPartBuilder.create().uv(3, 24).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 10.0F, -2.25F, -0.4754F, 0.7268F, -0.3295F));

		ModelPartData cube_r10 = Skirt.addChild("cube_r10", ModelPartBuilder.create().uv(3, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.043F, 10.0F, -1.9355F, -0.3679F, -0.4346F, 0.2699F));

		ModelPartData cube_r11 = Skirt.addChild("cube_r11", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.043F, 10.0F, -1.9355F, -0.6591F, 1.0091F, -0.4713F));

		ModelPartData cube_r12 = Skirt.addChild("cube_r12", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0733F, 10.0F, -1.2142F, -0.2124F, 0.1571F, 0.2474F));

		ModelPartData cube_r13 = Skirt.addChild("cube_r13", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.1062F, 10.0224F, -1.1135F, -1.7628F, 1.3614F, -1.4735F));

		ModelPartData cube_r14 = Skirt.addChild("cube_r14", ModelPartBuilder.create().uv(37, 48).cuboid(-1.5278F, -4.1928F, -1.5278F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.6662F, 0.7268F, 2.812F));

		ModelPartData cube_r15 = Skirt.addChild("cube_r15", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-0.4722F, -4.1928F, -1.5278F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.6662F, -0.7268F, -2.812F));

		ModelPartData cube_r16 = Skirt.addChild("cube_r16", ModelPartBuilder.create().uv(37, 48).cuboid(-3.1932F, -4.0845F, -0.2833F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.7737F, 0.4346F, 2.8717F));

		ModelPartData cube_r17 = Skirt.addChild("cube_r17", ModelPartBuilder.create().uv(37, 48).cuboid(-1.7167F, -4.0845F, -3.1932F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.4825F, -1.0091F, -2.6703F));

		ModelPartData cube_r18 = Skirt.addChild("cube_r18", ModelPartBuilder.create().uv(37, 48).cuboid(-4.6728F, -3.5681F, -0.8103F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.9292F, -0.1571F, 2.8942F));

		ModelPartData cube_r19 = Skirt.addChild("cube_r19", ModelPartBuilder.create().uv(37, 48).cuboid(-1.7472F, -3.5115F, -4.6701F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 1.3788F, -1.3614F, -1.6681F));

		ModelPartData cube_r20 = Skirt.addChild("cube_r20", ModelPartBuilder.create().uv(37, 48).cuboid(-4.8889F, -2.8424F, -2.9032F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 3.1242F, -0.9539F, 2.7615F));

		ModelPartData cube_r21 = Skirt.addChild("cube_r21", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(1.1932F, -4.0845F, -0.2833F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.7737F, -0.4346F, -2.8717F));

		ModelPartData cube_r22 = Skirt.addChild("cube_r22", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-0.2833F, -4.0845F, -3.1932F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.4825F, 1.0091F, 2.6703F));

		ModelPartData cube_r23 = Skirt.addChild("cube_r23", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(2.6728F, -3.5681F, -0.8103F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 2.9292F, 0.1571F, -2.8942F));

		ModelPartData cube_r24 = Skirt.addChild("cube_r24", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-0.2528F, -3.5115F, -4.6701F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 12.3159F, 2.2985F, 1.3788F, 1.3614F, 1.6681F));

		ModelPartData cube_r25 = Skirt.addChild("cube_r25", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -5.0F, 0.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.098F, 12.5477F, 0.8275F, 3.1242F, 0.9539F, -2.7615F));

		ModelPartData cube_r26 = Skirt.addChild("cube_r26", ModelPartBuilder.create().uv(3, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.0F, -2.25F, -0.4754F, -0.7268F, 0.3295F));

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