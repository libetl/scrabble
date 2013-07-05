package org.toilelibre.libe.scrabble.j3d.model;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import org.toilelibre.libe.scrabble.s3d.model.AbstractLetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;

public final class J3DLetterBranchGroup extends AbstractLetterBranchGroup {
    private static Font3D     fontLetter3d;

    private final BranchGroup bg;

    private final BranchGroup shape;

    static {
        final Map<java.awt.font.TextAttribute, Object> map = new HashMap<java.awt.font.TextAttribute, Object>();
        map.put(java.awt.font.TextAttribute.FOREGROUND, new java.awt.Color(0,
                0, 0));
        final Font fontTmp = new Font("Helvetica", Font.PLAIN, 1);
        AbstractLetterBranchGroup.setFontLetter(fontTmp.deriveFont(map));
        J3DLetterBranchGroup.fontLetter3d = new Font3D(
                (Font) AbstractLetterBranchGroup.getFontLetter(),
                new FontExtrusion());
    }

    public J3DLetterBranchGroup(final Object branchgroup) {
        super();
        final int trois = 3;
        TransformGroup tg;
        this.shape = (BranchGroup) branchgroup;
        this.shape.setName(this.getClass().getSimpleName().substring(trois));
        this.bg = new BranchGroup();
        this.bg.setName(this.getClass().getSimpleName());
        this.bg.setCapability(Group.ALLOW_CHILDREN_READ);
        this.bg.setCapability(Group.ALLOW_CHILDREN_WRITE);
        this.bg.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        this.bg.setCapability(BranchGroup.ALLOW_DETACH);
        this.setImpl(this.bg);
        this.setTransformGroup(new J3DTransformGroup());
        tg = (TransformGroup) ((J3DTransformGroup) this.getTransformGroup())
                .getImpl();
        tg.setCapability(Group.ALLOW_CHILDREN_READ);
        tg.setCapability(Group.ALLOW_CHILDREN_WRITE);
        tg.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ);
        this.bg.addChild(tg);
        ((BranchGroup) branchgroup).setCapability(BranchGroup.ALLOW_DETACH);
        tg.addChild((BranchGroup) branchgroup);
        tg.setName(this.getClass().getSimpleName());
        AbstractLetterBranchGroup.setLbg(this);
    }

    @Override
    public void affectLetter (final char letter1) {
        this.setLetter(letter1);
        final double const1 = -0.35;
        final Text3D textGeom = new Text3D(J3DLetterBranchGroup.fontLetter3d,
                "" + letter1, new Point3f((float) -0.65, (float) 0.5,
                        (float) 0.025));
        final BranchGroup bg2 = new BranchGroup();
        bg2.setCapability(Group.ALLOW_CHILDREN_READ);
        bg2.setCapability(Group.ALLOW_CHILDREN_WRITE);
        bg2.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        bg2.setCapability(BranchGroup.ALLOW_DETACH);

        final TransformGroup trSc = new TransformGroup();
        final Transform3D scale3D = new Transform3D();
        scale3D.rotX(const1);
        final Shape3D text = new Shape3D();
        final Appearance appearance = new Appearance();
        final Material material = new Material();
        appearance.setMaterial(material);
        material.setEmissiveColor(new Color3f(0, 0, 0));
        material.setDiffuseColor(new Color3f(0, 0, 0));
        text.setGeometry(textGeom);
        text.setAppearance(appearance);
        trSc.setTransform(scale3D);
        trSc.addChild(text);
        bg2.addChild(trSc);
        ((TransformGroup) ((J3DTransformGroup) this.getTransformGroup())
                .getImpl()).addChild(bg2);
    }

    @Override
    public ILetterBranchGroup cloneImpl () {
        final Object bg2 = ((((J3DLetterBranchGroup) AbstractLetterBranchGroup
                .getLbg()).shape)).cloneTree();
        return new J3DLetterBranchGroup(bg2);
    }

    @Override
    public void setOld () {
        final int trois = 3;
        final String subclass = this.getClass().getSimpleName()
                .substring(trois);
        final String old = "Old";
        this.bg.setName(old + subclass);
        this.shape.setName(old + subclass);
    }

    @Override
    public void setPoints (final int points) {
        final double const1 = -0.35;
        final double const2 = 0.6f;
        final BranchGroup bg2 = new BranchGroup();
        bg2.setCapability(Group.ALLOW_CHILDREN_READ);
        bg2.setCapability(Group.ALLOW_CHILDREN_WRITE);
        bg2.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        bg2.setCapability(BranchGroup.ALLOW_DETACH);

        final TransformGroup trSc = new TransformGroup();
        final Transform3D scale3D = new Transform3D();
        final Transform3D rot = new Transform3D();
        scale3D.setScale(const2);
        rot.rotX(const1);
        scale3D.mul(rot);
        final Text3D textGeom = new Text3D(J3DLetterBranchGroup.fontLetter3d,
                "" + points, new Point3f((float) -0.25, (float) 0.2,
                        (float) 0.18));
        final Shape3D text = new Shape3D();
        final Appearance appearance = new Appearance();
        final Material material = new Material();
        appearance.setMaterial(material);
        material.setEmissiveColor(new Color3f(0, 0, 0));
        material.setDiffuseColor(new Color3f(0, 0, 0));
        text.setGeometry(textGeom);
        text.setAppearance(appearance);
        trSc.setTransform(scale3D);
        trSc.addChild(text);
        bg2.addChild(trSc);
        ((TransformGroup) ((J3DTransformGroup) this.getTransformGroup())
                .getImpl()).addChild(bg2);
    }

}
