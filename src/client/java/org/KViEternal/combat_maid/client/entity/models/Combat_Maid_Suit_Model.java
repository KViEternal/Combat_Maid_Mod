package org.KViEternal.combat_maid.client.entity.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class Combat_Maid_Suit_Model extends Model {
	public final ModelPart Body;
	public final ModelPart Chest;
	public final ModelPart Skirt;
	public final ModelPart Sides;
	public final ModelPart Back;
	public final ModelPart Front;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;

	public Combat_Maid_Suit_Model(ModelPart root) {
		super(RenderLayer::getArmorCutoutNoCull);
		this.Body = root.getChild("Body");
		this.Chest = this.Body.getChild("Chest");
		this.Skirt = root.getChild("Skirt");
		this.Sides = this.Skirt.getChild("Sides");
		this.Back = this.Skirt.getChild("Back");
		this.Front = this.Skirt.getChild("Front");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(16, 26).cuboid(-4.0F, 9.7F, -2.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.06F))
				.uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 9.95F, 4.0F, new Dilation(0.01F))
				.uv(2, 44).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 9.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Chest = Body.addChild("Chest", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.5F, -1.0F));

		ModelPartData cube_r1 = Chest.addChild("cube_r1", ModelPartBuilder.create().uv(11, 0).cuboid(0.0F, -1.75F, -3.0F, 4.0F, 3.0F, 2.0F, new Dilation(0.049F)), ModelTransform.of(0.25F, 1.25F, 0.0F, -0.7854F, -0.1745F, -3.1416F));
		ModelPartData cube_r2 = Chest.addChild("cube_r2", ModelPartBuilder.create().uv(11, 0).cuboid(0.0F, -1.462F, -2.75F, 4.0F, 4.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(0.25F, 2.962F, 0.0F, 0.6109F, -0.1745F, -3.1416F));
		ModelPartData cube_r3 = Chest.addChild("cube_r3", ModelPartBuilder.create().uv(7, 0).cuboid(-4.0F, -1.75F, -3.0F, 4.0F, 3.0F, 2.0F, new Dilation(0.049F)), ModelTransform.of(-0.25F, 1.25F, 0.0F, -0.7854F, 0.1745F, -3.1416F));
		ModelPartData cube_r4 = Chest.addChild("cube_r4", ModelPartBuilder.create().uv(7, 0).cuboid(-4.0F, -1.462F, -2.75F, 4.0F, 4.0F, 2.0F, new Dilation(0.05F)), ModelTransform.of(-0.25F, 2.962F, 0.0F, 0.6109F, 0.1745F, -3.1416F));

		ModelPartData Skirt = modelPartData.addChild("Skirt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

		ModelPartData Sides = Skirt.addChild("Sides", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

		// FIXED: Changed Dilation from -0.01F to 0.0F to stop inside-out texture glitching
		ModelPartData cube_r5 = Sides.addChild("cube_r5", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -0.95F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(-3.3668F, 1.9905F, -0.2558F, 0.0175F, 0.9539F, 0.3801F));
		ModelPartData cube_r6 = Sides.addChild("cube_r6", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -5.0F, 0.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.098F, 4.5477F, 0.8275F, 3.1242F, 0.9539F, -2.7615F));
		ModelPartData cube_r7 = Sides.addChild("cube_r7", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -0.95F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.1897F, 2.1082F, -0.1082F, -0.0262F, -0.9539F, -0.3801F));
		ModelPartData cube_r8 = Sides.addChild("cube_r8", ModelPartBuilder.create().uv(37, 48).cuboid(-4.8889F, -2.8424F, -2.9032F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.3159F, 2.2985F, 3.1242F, -0.9539F, 2.7615F));

		ModelPartData Back = Skirt.addChild("Back", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 8.0F, 1.75F));

		ModelPartData cube_r9 = Back.addChild("cube_r9", ModelPartBuilder.create().uv(37, 48).cuboid(-1.7472F, -3.5115F, -4.6701F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 1.3788F, -1.3614F, -1.6681F));
		ModelPartData cube_r10 = Back.addChild("cube_r10", ModelPartBuilder.create().uv(37, 48).cuboid(-4.6728F, -3.5681F, -0.8103F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.9292F, -0.1571F, 2.8942F));
		ModelPartData cube_r11 = Back.addChild("cube_r11", ModelPartBuilder.create().uv(37, 48).cuboid(-1.7167F, -4.0845F, -3.1932F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.4825F, -1.0091F, -2.6703F));
		ModelPartData cube_r12 = Back.addChild("cube_r12", ModelPartBuilder.create().uv(37, 48).cuboid(-3.1932F, -4.0845F, -0.2833F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.7737F, 0.4346F, 2.8717F));
		ModelPartData cube_r13 = Back.addChild("cube_r13", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-0.4722F, -4.1928F, -1.5278F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.6662F, -0.7268F, -2.812F));
		ModelPartData cube_r14 = Back.addChild("cube_r14", ModelPartBuilder.create().uv(37, 48).cuboid(-1.5278F, -4.1928F, -1.5278F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.6662F, 0.7268F, 2.812F));
		ModelPartData cube_r15 = Back.addChild("cube_r15", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(1.1932F, -4.0845F, -0.2833F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.7737F, -0.4346F, -2.8717F));
		ModelPartData cube_r16 = Back.addChild("cube_r16", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-0.2833F, -4.0845F, -3.1932F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.4825F, 1.0091F, 2.6703F));
		ModelPartData cube_r17 = Back.addChild("cube_r17", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(2.6728F, -3.5681F, -0.8103F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 2.9292F, 0.1571F, -2.8942F));
		ModelPartData cube_r18 = Back.addChild("cube_r18", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-0.2528F, -3.5115F, -4.6701F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 4.3159F, 0.5485F, 1.3788F, 1.3614F, 1.6681F));

		ModelPartData Front = Skirt.addChild("Front", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, -1.75F));

		ModelPartData cube_r19 = Front.addChild("cube_r19", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.1062F, 2.0224F, 0.6365F, -1.7628F, -1.3614F, 1.4735F));
		ModelPartData cube_r20 = Front.addChild("cube_r20", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.0733F, 2.0F, 0.5358F, -0.2124F, -0.1571F, -0.2474F));
		ModelPartData cube_r21 = Front.addChild("cube_r21", ModelPartBuilder.create().uv(37, 48).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.043F, 2.0F, -0.1855F, -0.6591F, -1.0091F, 0.4713F));
		ModelPartData cube_r22 = Front.addChild("cube_r22", ModelPartBuilder.create().uv(3, 24).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.043F, 2.0F, -0.1855F, -0.3679F, 0.4346F, -0.2699F));
		ModelPartData cube_r23 = Front.addChild("cube_r23", ModelPartBuilder.create().uv(3, 24).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 2.0F, -0.5F, -0.4754F, 0.7268F, -0.3295F));
		ModelPartData cube_r24 = Front.addChild("cube_r24", ModelPartBuilder.create().uv(3, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(-2.043F, 2.0F, -0.1855F, -0.3679F, -0.4346F, 0.2699F));
		ModelPartData cube_r25 = Front.addChild("cube_r25", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(-2.043F, 2.0F, -0.1855F, -0.6591F, 1.0091F, -0.4713F));
		ModelPartData cube_r26 = Front.addChild("cube_r26", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(-3.0733F, 2.0F, 0.5358F, -0.2124F, 0.1571F, 0.2474F));
		ModelPartData cube_r27 = Front.addChild("cube_r27", ModelPartBuilder.create().uv(37, 48).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(-3.1062F, 2.0224F, 0.6365F, -1.7628F, 1.3614F, -1.4735F));
		ModelPartData cube_r28 = Front.addChild("cube_r28", ModelPartBuilder.create().uv(3, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 0.005F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -0.5F, -0.4754F, -0.7268F, 0.3295F));

		ModelPartData RightArm = modelPartData.addChild("RightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData LeftArm = modelPartData.addChild("LeftArm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		Body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	public void renderSkirt(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		Skirt.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	public void renderArm(MatrixStack matrices, boolean isLeft, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		if(isLeft){
			LeftArm.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		}
		else {
			RightArm.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		}
	}
}