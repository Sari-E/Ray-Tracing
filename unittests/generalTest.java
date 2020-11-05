/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * @author Sari
 *
 */
public class generalTest {
@Test
public void test3() {
	Scene scene = new Scene("Test scene");
	scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
	scene.set_screenDistance(1000);
	scene.set_background(Color.BLACK);
	scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

	scene.addGeometry( //
			new Triangle(new Material(0.5, 0.5, 60), Color.BLACK, //
					new Point3D(-150, 150, 165), new Point3D(150, 150, 185), new Point3D(75, -75, 200)), //
			new Triangle(new Material(0.5, 0.5, 60), Color.BLACK, //
					new Point3D(-150, 150, 165), new Point3D(-70, -70, 190), new Point3D(75, -75, 200)), //
			new Sphere(new Material(0.2, 0.2, 30, 0.6, 0), new Color(java.awt.Color.BLUE), // )
					30, new Point3D(60, -50, 50)),
			new Sphere(new Material(0.2, 0.2, 30, 0.6, 0), new Color(java.awt.Color.BLUE), // )
							15, new Point3D(20, 0, 50)));

	scene.addLights(new SpotLight(new Color(700, 400, 400), //
			new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7),
			new PointLight(new Color(255, 300, 255), new Point3D(100, -100, 0), 1, 0.00001, 0.000001),
			new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));

	ImageWriter imageWriter = new ImageWriter("General Test", 200, 200, 600, 600);
	Render render = new Render(imageWriter, scene);

	render.renderImage();
	render.writeToImage();
}
/*

	@Test
	public void test2() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -2000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_screenDistance(1000);
		scene.set_background(new Color(0,0,0));
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometry(new Sphere(new Material(0.2, 0.2, 30,0.6,0), new Color(java.awt.Color.CYAN), 100, new Point3D(150, -20, 50)),
				new Sphere(new Material(0.2, 0.2, 30,0.6,0), new Color(java.awt.Color.CYAN), 50, new Point3D(0, -20, 50)),
				new Sphere(new Material(0.2, 0.2, 30,0.6,0), new Color(java.awt.Color.CYAN), 25, new Point3D(-75, -20, 50)),
				new Triangle(new Material(0.5, 0.5, 60),new Color(0,0,255) ,new Point3D(-400, 0, 200), new Point3D(400, 0, 200), new Point3D(0, 700, 200)));	
				
		new Triangle(new Material(0.5, 0.5, 60), Color.BLACK, //
				new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
		new Triangle(new Material(0.5, 0.5, 60), Color.BLACK, //
				new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
		new Sphere(new Material(0.2, 0.2, 30, 0.6, 0), new Color(java.awt.Color.BLUE), // )
				30, new Point3D(60, -50, 50))

    	scene.addLights(new SpotLight(new Color(255, 255, 255), new Point3D(300, -150, 100),   new Vector(-300, 150, -100), 1, 0.00001, 0.00000001));

		ImageWriter imageWriter = new ImageWriter("General Test", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}*/
	/*
	@Test
	public void test2() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -2000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_screenDistance(1000);
		scene.set_background(new Color(51,204,255));
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometry(new Sphere(new Material(0.5, 0.5, 100,0,0), new Color(java.awt.Color.yellow), 25, new Point3D(100, -150, 50)),
				new Triangle(new Material(0.5, 0.5, 60),new Color(255,230,153) ,new Point3D(-50, -25, 50), new Point3D(50, -25, 50), new Point3D(-50, 75, 50)),	
				new Triangle(new Material(0.5, 0.5, 60),new Color(255,230,153) ,new Point3D(50, 75, 50), new Point3D(50, -25, 50), new Point3D(-50, 75, 50)),
				new Triangle(new Material(0.5, 0.5, 60),new Color(0,255,0),new Point3D(-75, -25, 50), new Point3D(75, -25, 50), new Point3D(0, -100, 50)),
				
				new Triangle(new Material(0.5, 0.5, 60),new Color(java.awt.Color.GREEN) ,new Point3D(50, 75, 72.5), new Point3D(55, 75, 50), new Point3D(52.5, 75, 50)),	
				new Triangle(new Material(0.5, 0.5, 60),new Color(java.awt.Color.GREEN) ,new Point3D(60, 73, 60), new Point3D(65, 73, 60), new Point3D(62.5, 55, 60)),
				new Triangle(new Material(0.5, 0.5, 60),new Color(java.awt.Color.GREEN),new Point3D(70, 74, 55), new Point3D(75, 74, 55), new Point3D(72.5, 71.5, 55)));
				
				//new Triangle(new Material(0.5, 0.5, 60),new Color(255,230,153) ,new Point3D(-50, -25, 50), new Point3D(50, -25, 50), new Point3D(-50, 75, 50)),	
				//new Triangle(new Material(0.5, 0.5, 60),new Color(255,230,153) ,new Point3D(50, 75, 50), new Point3D(50, -25, 50), new Point3D(-50, 75, 50)),
				//new Triangle(new Material(0.5, 0.5, 60),new Color(255,0,0),new Point3D(-75, -25, 50), new Point3D(75, -25, 50), new Point3D(0, -100, 50)));


		scene.addLights(new SpotLight(new Color(500, 300, 0), new Point3D(-50, 50, -50),new Vector(1, -1, 2), 1, 0.00001, 0.00000001));

    	scene.addLights(new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, -50),
                new Vector(1, -1, 2), 1, 0.00001, 0.00000001));

		ImageWriter imageWriter = new ImageWriter("General Test", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}*/
	

}
