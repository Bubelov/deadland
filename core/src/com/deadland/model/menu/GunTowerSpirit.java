package com.deadland.model.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.deadland.ControlManager;
import com.deadland.EHelper;
import com.deadland.EntityUtils;
import com.deadland.ResourcesManager;
import com.deadland.model.Entity;
import com.deadland.model.building.GunTower;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTowerSpirit extends Entity {
    public static Texture texture = new Texture("building_tower_transparent.png");

    private GunTowerButton button;

    public GunTowerSpirit(float x, float y, GunTowerButton e) {
        button = e;
        sprite = new Sprite(texture);
        sprite.setSize(32, 32);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);
    }

    @Override
    public void update() {
        Camera c = ControlManager.instance.camera;
        sprite.setPosition(
                c.position.x - c.viewportWidth / 2 + Gdx.input.getX() - 16,
                c.position.y + c.viewportHeight / 2 - Gdx.input.getY() - 16
        );
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (this.button.isButtonDown(x, y)) {
            return;
        }

        if (!EntityUtils.collidesAll(this) && ResourcesManager.instance.spendTrash(GunTower.price)) {
            Camera c = ControlManager.instance.camera;
            GunTower gt = new GunTower(
                    c.position.x - c.viewportWidth / 2 + Gdx.input.getX() - 16,
                    c.position.y + c.viewportHeight / 2 - Gdx.input.getY() - 16
            );
            EHelper.add(gt);
        }
    }
}
