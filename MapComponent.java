package com.company;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class MapComponent extends JComponent{
	private static final int FRAMES_PER_SECOND = 30;
	private static final long REPAINT_INTERVAL_MS = 1000 / FRAMES_PER_SECOND;
	private Map map;
	private boolean hasShownNullErrorMessage = false;

	public MapComponent(Map theMap) {
		this.map = theMap;
		setPreferredSize(map.getSize());
		setMaximumSize(map.getSize());
		setFocusable(true);
		addKeyListener(new KeyControl(map));
		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(REPAINT_INTERVAL_MS);
						repaint();
					}
				} catch (InterruptedException exception) {

				}
			}
		};
		new Thread(repainter).start();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		drawDrawable(g2, this.map);

		ArrayList<Drawable> drawableParts = this.map.getDrawableParts();
		if (drawableParts.isEmpty()){
			return;
		}
		for (Drawable d : drawableParts) {
			drawDrawable(g2, d);
		}
	}

	private void drawDrawable(Graphics2D g2, Drawable d) {
		Color color = d.getColor();
		if (color == null) {
			showNullPointerMessage("color", d);
			return;
		}
		Shape shape = d.getShape();
		if (shape == null) {
			showNullPointerMessage("shape", d);
			return;
		}
		g2.setColor(color);
		g2.fill(shape);

		if (d.getImage()!=null) {
			g2.drawImage(d.getImage(), (int)d.getPosition().getX(), (int)d.getPosition().getY(), null);
		}

	}

	private void showNullPointerMessage(String nullAttribute, Drawable d) {
		if (!this.hasShownNullErrorMessage) {
			this.hasShownNullErrorMessage = true;
			String message = "I could not draw this Drawable object of type " + d.getClass().getName() + " because its "
					+ nullAttribute + " is null.\n";
			JOptionPane.showMessageDialog(null, message, "Null pointer exception", JOptionPane.ERROR_MESSAGE);
		}
	}
}