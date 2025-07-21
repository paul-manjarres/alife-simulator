package org.yagamipaul.alife.screen.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.yagamipaul.alife.entities.BaseEntity;
import org.yagamipaul.alife.entities.Organism;

public class FloatingEntityInfo {
    private final BaseEntity entity;

    public FloatingEntityInfo(BaseEntity entity) {
        this.entity = entity;
    }

    public void renderShape(ShapeRenderer sr, BitmapFont font, OrthographicCamera camera) {
        String labelText = getText();

        // Measure text size
        GlyphLayout layout = new GlyphLayout(font, labelText);
        float padding = 6;
        float rectWidth = layout.width + padding * 2;
        float rectHeight = layout.height + padding * 2;

        // Draw rectangle (UI overlay)
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.1f, 0.1f, 0.1f, 0.7f); // Dark translucent background
        sr.rect(
                entity.getRect().getX(),
                entity.getRect().getY() + entity.getRect().getHeight() + 10,
                rectWidth,
                rectHeight);
        sr.end();
    }

    public void renderText(SpriteBatch sb, BitmapFont font) {

        String labelText = getText();
        // Measure text size
        GlyphLayout layout = new GlyphLayout(font, labelText);
        float padding = 6;
        //        float rectWidth = layout.width + padding * 2;
        float rectHeight = layout.height + padding * 2;

        font.draw(
                sb,
                labelText,
                entity.getRect().getX() + padding,
                entity.getRect().getY() + entity.getRect().getHeight() + 10 + rectHeight - padding);
    }

    private String getText() {
        String health = (entity instanceof Organism org) ? " - Life: " + org.getHealth() : "";

        return "Id: " + entity.getId() + health + "\nx: " + (int) entity.getPosition().x + "\ny: "
                + (int) entity.getPosition().y;
    }
}
