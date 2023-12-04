package com.mirs.view;

import javax.swing.*;

public class ViewUtil {
    public static void centerDialogOverFrame(JDialog dialog, JFrame frame) {
        int frameX = frame.getX();
        int frameY = frame.getY();
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

        int dialogWidth = dialog.getWidth();
        int dialogHeight = dialog.getHeight();

        int dialogX = frameX + (frameWidth - dialogWidth) / 2;
        int dialogY = frameY + (frameHeight - dialogHeight) / 2;

        dialog.setLocation(dialogX, dialogY);
    }
}
