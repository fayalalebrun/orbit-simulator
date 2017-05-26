package com.orbit.data.UI;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisTable;
import com.orbit.data.GameScreen;

import java.util.ArrayList;

/**
 * Created by Fran on 5/24/2017.
 */
public class ToolAdapter extends ArrayListAdapter<Tool, VisTable> {
    AssetManager manager;

    private final Drawable bg = VisUI.getSkin().getDrawable("window-bg");
    private final Drawable selection = VisUI.getSkin().getDrawable("list-selection");

    public ToolAdapter(ArrayList<Tool> array, AssetManager manager) {
        super(array);
        setSelectionMode(SelectionMode.SINGLE);
        this.manager = manager;
    }

    @Override
    protected VisTable createView(Tool item) {

        Texture texture = null;

        VisTable table = new VisTable();
        switch (item){
            case POINTER:
                texture = manager.get("pointer.png", Texture.class);
                break;
            case MOVE:
                texture = manager.get("move.png", Texture.class);
                break;
            case ZOOM_IN:
                texture = manager.get("zoom-in.png", Texture.class);
                break;
            case ZOOM_OUT:
                texture = manager.get("zoom-out.png", Texture.class);
                break;
        }



        Image image = new Image(texture);

        table.add(image);
        return table;
    }

    @Override
    protected void selectView(VisTable view) {
        view.setBackground(selection);
    }

    @Override
    protected void deselectView(VisTable view) {
        view.setBackground(bg);
    }
}
