package com.silverhetch.athena.ui.vocabularylist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.silverhetch.athena.R;

/**
 * Created by mikes on 1/12/2018.
 */

abstract class SwipToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private final Drawable background;
    private final Drawable trashCan;
    private final int padding;
    private final int iconSize;

    public SwipToDeleteCallback(Context context, int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
        background = new ColorDrawable(Color.RED);
        trashCan = context.getDrawable(R.drawable.icon_trash_can);
        trashCan.setTint(Color.WHITE);
        padding = (int) (context.getResources().getDisplayMetrics().density * 16);
        iconSize = (int) (context.getResources().getDisplayMetrics().density * 24);
    }

    @Override
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        final int maxRight = itemView.getLeft() + ((int) dX);

        background.setBounds(itemView.getLeft(), itemView.getTop(), maxRight, itemView.getBottom());
        background.draw(canvas);

        final int itemHeight = itemView.getBottom() - itemView.getTop();
        final int middleY = itemView.getTop() + itemHeight / 2;
        final int haftIconSize = iconSize / 2;
        final int iconRight = itemView.getLeft() + padding + iconSize;

        trashCan.setBounds(itemView.getLeft() + padding, middleY - haftIconSize, iconRight, middleY + haftIconSize);
        trashCan.draw(canvas);

    }
}
