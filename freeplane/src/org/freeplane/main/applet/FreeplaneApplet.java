/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev
 *
 *  This file is modified by Dimitry Polivaev in 2008.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.main.applet;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JApplet;

import org.freeplane.core.controller.Controller;
import org.freeplane.core.filter.FilterController;
import org.freeplane.core.resources.ResourceController;
import org.freeplane.features.browsemode.BModeController;
import org.freeplane.features.common.attribute.ModelessAttributeController;
import org.freeplane.features.common.icon.IconController;
import org.freeplane.features.common.link.LinkController;
import org.freeplane.features.common.text.TextController;
import org.freeplane.features.common.time.TimeController;
import org.freeplane.features.controller.help.HelpController;
import org.freeplane.features.controller.print.PrintController;
import org.freeplane.main.browsemode.BModeControllerFactory;
import org.freeplane.view.swing.map.MapViewController;

public class FreeplaneApplet extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppletResourceController appletResourceController;
	private AppletViewController appletViewController;
	private Controller controller;

	@Override
	public void destroy() {
		controller.shutdown();
	}

	@Override
	public void init() {
		createRootPane();
		updateLookAndFeel();
		final Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		controller = new Controller();
		appletResourceController = new AppletResourceController(this, controller);
		ResourceController.setResourceController(appletResourceController);
		appletViewController = new AppletViewController(controller, this, new MapViewController());
		FilterController.install(controller);
		PrintController.install(controller);
		HelpController.install(controller);
		ModelessAttributeController.install(controller);
		TextController.install(controller);
		TimeController.install(controller);
		LinkController.install(controller);
		IconController.install(controller);
		final BModeController browseController = BModeControllerFactory.createModeController(controller,
		    "/xml/appletMenu.xml");
		controller.selectMode(browseController);
		appletViewController.init();
		controller.getViewController().setToolbarVisible(true);
		controller.getViewController().setMenubarVisible(false);
	}

	@Override
	public void start() {
		appletViewController.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	private void updateLookAndFeel() {
		String lookAndFeel = "";
		appletResourceController.setPropertyByParameter("lookandfeel");
		lookAndFeel = appletResourceController.getProperty("lookandfeel");
		Controller.setLookAndFeel(lookAndFeel);
	}
}
